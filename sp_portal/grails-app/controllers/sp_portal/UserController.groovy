package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;

import org.joda.time.LocalDate;

class UserController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";


    def beforeInterceptor = [action:this.&isLoggedInAsAdmin, except:["login", "authenticate", "index"]]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    def index() {
		log.info("index of user")
        redirect(action: "welcome", params: params)
    }

    def list() {
        log.info("list of user")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)


        [userInstanceList: User.list(params), userInstanceTotal: User.count()]

    }

    def create() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method create() with params : "+params)
		}
          params.isActive=true
          //def standardizedPatient = new local.StandardizedPatient();


          User newUser = new User(params);
         // newUser.standardizedPatient  = standardizedPatient;

        [userInstance: newUser]

    }

        def createUserMessage(def list,String username, String password){
                list.add(" created user with username " + username + " " + password);
        }

        def importMessage(def list,String type, String ident){
                list.add(" imported " + type + " " + ident);
        }

        def existsMessage(def list,String type, String ident){
                list.add(" " + type + " " + ident + " Already Exists ");
        }


    def importData() {
		log.info("import Data Pressed")
        def messages = [];

        def needsTitleChecks = [];

         def scars =  remote.Scar.list();
         for (remote.Scar scar : scars ){
          if(!(local.Scar.findByOrigId(scar.id))){


             local.Scar newScar = new local.Scar();
             newScar.origId = scar.id;
             newScar.bodypart = scar.bodypart;
             newScar.traitType = scar.traitType;
			 if(log.isDebugEnabled()){
				log.debug("save newScar : "+newScar)
			 }
             newScar.save();
             importMessage(messages,"${message(code: 'default.scar.message')}",""+scar.id);
          } else {
            existsMessage(messages,"${message(code: 'default.scar.message')}", ""+scar.id);
          }
         }


        def anamnesisCheckList =  remote.AnamnesisCheck.list();
        // add AnamnesisCheck data which type is title
		log.info("add anamnesisCheck titles")

        def titleCheckList = remote.AnamnesisCheck.findAllByType(AnamnesisCheckTypes.QUESTION_TITLE.getTypeId());

        for (remote.AnamnesisCheck titleCheck : titleCheckList ){
          if(!(local.AnamnesisCheck.findByOrigId(titleCheck.id))){
             local.AnamnesisCheck newCheck = new local.AnamnesisCheck();
             newCheck.origId = titleCheck.id;
             newCheck.sortOrder = titleCheck.sortOrder;
             newCheck.text = titleCheck.text;
             newCheck.type = titleCheck.type;
             newCheck.value = titleCheck.value;
             newCheck.userSpecifiedOrder = titleCheck.userSpecifiedOrder;

             if(titleCheck.title){
                newCheck.title = local.AnamnesisForm.findByOrigId(titleCheck.id);
             }
			 if(log.isDebugEnabled()){
				StringBuffer sb = new StringBuffer();
				sb.append( "\n newCheck.origId: ");
				sb.append(newCheck?.origId);
				sb.append( "\n newCheck.sortOrder: ");
				sb.append(newCheck?.sortOrder);
				sb.append( "\n newCheck.text: ");
				sb.append(newCheck?.text);
				sb.append( "\n newCheck.type: ");
				sb.append(newCheck?.type);
				sb.append( "\n newCheck.value: ");
				sb.append(newCheck?.value);
				sb.append( "\n newCheck.userSpecifiedOrder: ");
				sb.append(newCheck?.userSpecifiedOrder);
				sb.append( "\n newCheck.title: ");
				sb.append(newCheck?.title);
				sb.append( "\n newCheck.anamnesisCheckTitle: ");
				sb.append(newCheck?.anamnesisCheckTitle);
				log.debug( "save titleCheck: " + sb.toString());
			 }
             newCheck.save();
             importMessage(messages,"${message(code: 'default.AnamnesisCheck.message')}", ""+titleCheck.id);
          }else {
            existsMessage(messages,"${message(code: 'default.AnamnesisCheck.message')}", ""+titleCheck.id);
          }

        }

        def anamnesisCheckTitleList = remote.AnamnesisCheckTitle.list();
        for (remote.AnamnesisCheckTitle anamnesisCheckTitle : anamnesisCheckTitleList ){
          if(!(local.AnamnesisCheckTitle.findByOrigId(anamnesisCheckTitle.id))){

             local.AnamnesisCheckTitle newAnamnesisCheckTitle= new local.AnamnesisCheckTitle();
             newAnamnesisCheckTitle.origId = anamnesisCheckTitle.id;
             newAnamnesisCheckTitle.sortOrder = anamnesisCheckTitle.sortOrder;
             newAnamnesisCheckTitle.text = anamnesisCheckTitle.text;
			 if(log.isDebugEnabled()){
				StringBuffer sb = new StringBuffer();
				sb.append( "\n newAnamnesisCheckTitle.origId: ");
				sb.append(newAnamnesisCheckTitle?.origId);
				sb.append( "\n newAnamnesisCheckTitle.sortOrder: ");
				sb.append(newAnamnesisCheckTitle?.sortOrder);
				sb.append( "\n newAnamnesisCheckTitle.text: ");
				sb.append(newAnamnesisCheckTitle?.text);
				log.debug( "save anamnesisCheckTitle: " + sb.toString());
			 }
             newAnamnesisCheckTitle.save();

             importMessage(messages,"${message(code: 'default.AnamnesisCheckTitle.message')}", ""+anamnesisCheckTitle.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.AnamnesisCheckTitle.message')}", ""+anamnesisCheckTitle.id);
          }
        }
		log.info("add anamnesisChecks")
        for (remote.AnamnesisCheck check : anamnesisCheckList ){
          if(!(local.AnamnesisCheck.findByOrigId(check.id))){

             if (check.type != AnamnesisCheckTypes.QUESTION_TITLE.getTypeId()){
                 local.AnamnesisCheck newCheck = new local.AnamnesisCheck();

                 newCheck.origId = check.id;
                 newCheck.sortOrder = check.sortOrder;
                 newCheck.text = check.text;
                 newCheck.type = check.type;
                 newCheck.value = check.value;
                 newCheck.userSpecifiedOrder = check.userSpecifiedOrder;

                   if(check.title){
                     newCheck.title = local.AnamnesisCheck.findByOrigId(check.title.id);
                     log(" title = " + newCheck.title.text);
                   }
                   if(check.anamnesisCheckTitle){
                    newCheck.anamnesisCheckTitle=local.AnamnesisCheckTitle.findByOrigId(check.anamnesisCheckTitle.id)
                      log(" title = " + newCheck.anamnesisCheckTitle.text);
                   }

				if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newCheck.origId: ");
					sb.append(newCheck?.origId);
					sb.append( "\n newCheck.sortOrder: ");
					sb.append(newCheck?.sortOrder);
					sb.append( "\n newCheck.text: ");
					sb.append(newCheck?.text);
					sb.append( "\n newCheck.type: ");
					sb.append(newCheck?.type);
					sb.append( "\n newCheck.value: ");
					sb.append(newCheck?.value);
					sb.append( "\n newCheck.userSpecifiedOrder: ");
					sb.append(newCheck?.userSpecifiedOrder);
					sb.append( "\n newCheck.title: ");
					sb.append(newCheck?.title);
					sb.append( "\n newCheck.anamnesisCheckTitle: ");
					sb.append(newCheck?.anamnesisCheckTitle);
					log.debug( "save newCheck: " + sb.toString());
				}

                 newCheck.save();



                 importMessage(messages,"${message(code: 'default.AnamnesisCheck.message')}", ""+check.id);
              }
          }else {
            existsMessage(messages,"${message(code: 'default.AnamnesisCheck.message')}", ""+check.id);
          }

        }

		log.info("add anamnesisForm")
        def anamnesisForm  =  remote.AnamnesisForm .list();
        for (remote.AnamnesisForm anamnesisFormValue : anamnesisForm ){
          if(!(local.AnamnesisForm.findByOrigId(anamnesisFormValue.id))){

             local.AnamnesisForm newAnamnesisForm = new local.AnamnesisForm();
             newAnamnesisForm.origId = anamnesisFormValue.id;
             newAnamnesisForm.createDate = anamnesisFormValue.createDate;
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newAnamnesisForm.origId: ");
					sb.append(newAnamnesisForm?.origId);
					sb.append( "\n newAnamnesisForm.createDate: ");
					sb.append(newAnamnesisForm?.createDate);
					log.debug( "save newAnamnesisForm: " + sb.toString());
			 }
             newAnamnesisForm.save();
                         importMessage(messages,"${message(code: 'default.AnamnesisForm.message')}", ""+anamnesisFormValue.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.AnamnesisForm.message')}", ""+anamnesisFormValue.id);
          }
        }
		
		log.info("add bankaccount")
        def bankaccountList =  remote.Bankaccount.list();
        for (remote.Bankaccount bankAccount : bankaccountList ){

          if(!(local.Bankaccount.findByOrigId(bankAccount.id))){

              local.Bankaccount newBankaccount= new local.Bankaccount();
              newBankaccount.origId = bankAccount.id;
                            newBankaccount.bic = bankAccount.bic;
                            newBankaccount.iban = bankAccount.iban;
                            newBankaccount.bankName = bankAccount.bankName;
                            newBankaccount.city = bankAccount.city;
                            newBankaccount.ownerName = bankAccount.ownerName;
                            newBankaccount.postalCode = bankAccount.postalCode;
		       if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newBankaccount.origId: ");
					sb.append(newBankaccount?.origId);
					sb.append( "\n newBankaccount.bic: ");
					sb.append(newBankaccount?.bic);
					sb.append( "\n newBankaccount.iban: ");
					sb.append(newBankaccount?.iban);
					sb.append( "\n newBankaccount.bankName: ");
					sb.append(newBankaccount?.bankName);
					sb.append( "\n newBankaccount.city: ");
					sb.append(newBankaccount?.city);
					sb.append( "\n newBankaccount.ownerName: ");
					sb.append(newBankaccount?.ownerName);
					sb.append( "\n newBankaccount.postalCode: ");
					sb.append(newBankaccount?.postalCode);
					log.debug( "save newBankaccount: " + sb.toString());
			 }
              newBankaccount.save();

                            importMessage(messages,"${message(code: 'default.Bankaccount.message')}", ""+bankAccount.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.Bankaccount.message')}", ""+bankAccount.id);
          }
        }



		log.info("add description")
        def descriptions   =  remote.Description.list();
        for (remote.Description description : descriptions ){

          if(!(local.Description.findByOrigId(description.id))){

             local.Description newDescription = new local.Description();
             newDescription.origId = description.id;
             newDescription.description = description.description;
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newDescription.origId: ");
					sb.append(newDescription?.origId);
					sb.append( "\n newDescription.description: ");
					sb.append(newDescription?.description);
					log.debug( "save newDescription: " + sb.toString());
			 }
             newDescription.save();


             importMessage(messages,"${message(code: 'default.Description.message')}", ""+description.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.Description.message')}", ""+description.id);
          }
        }


		log.info("add spokenLanguage")
        def languages =  remote.SpokenLanguage.list();
        for (remote.SpokenLanguage language : languages ){
          if(!(local.SpokenLanguage.findByOrigId(language.id))){

             local.SpokenLanguage newLanguage = new local.SpokenLanguage();
             newLanguage.origId = language.id;
             newLanguage.languageName = language.languageName;
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newLanguage.origId: ");
					sb.append(newLanguage?.origId);
					sb.append( "\n newLanguage.languageName: ");
					sb.append(newLanguage?.languageName);
					log.debug( "save newLanguage: " + sb.toString());
			 }
             newLanguage.save();

            importMessage(messages,"${message(code: 'default.SpokenLanguage.message')}", ""+language.id);
          }else {
            existsMessage(messages,"${message(code: 'default.SpokenLanguage.message')}", ""+language.id);
          }

        }
		
		log.info("add nationalitys")
        def nationalitys =  remote.Nationality.list();
        for (remote.Nationality nationality : nationalitys ){
          if(!(local.Nationality.findByOrigId(nationality.id))){

             local.Nationality newNationality = new local.Nationality();
             newNationality.origId = nationality.id;
             newNationality.nationality = nationality.nationality;
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newNationality.origId: ");
					sb.append(newNationality?.origId);
					sb.append( "\n newNationality.nationality: ");
					sb.append(newNationality?.nationality);
					log.debug( "save newNationality: " + sb.toString());
			 }
             newNationality.save();

             importMessage(messages,"${message(code: 'default.Nationality.message')}", ""+nationality.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.Nationality.message')}", ""+nationality.id);
          }
        }

		log.info("add professions")
        def professions =  remote.Profession.list();
        for (remote.Profession profession : professions ){
          if(!(local.Profession.findByOrigId(profession.id))){

             local.Profession newProfession = new local.Profession();
             newProfession.origId = profession.id;
             newProfession.profession = profession.profession;
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newProfession.origId: ");
					sb.append(newProfession?.origId);
					sb.append( "\n newProfession.profession: ");
					sb.append(newProfession?.profession);
					log.debug( "save newProfession: " + sb.toString());
			 }
             newProfession.save();
             importMessage(messages,"${message(code: 'default.Profession.message')}", ""+profession.id);
                    }else {
            existsMessage(messages,"${message(code: 'default.Profession.message')}", ""+profession.id);
          }
        }


		log.info("add professions")
        def checkValues =  remote.AnamnesisChecksValue .list();
        for (remote.AnamnesisChecksValue  checkValue : checkValues ){
          if(!(local.AnamnesisChecksValue.findByOrigId(checkValue.id))){

             local.AnamnesisChecksValue  newCheckValue = new local.AnamnesisChecksValue ();
             newCheckValue.origId = checkValue.id;
             newCheckValue.anamnesisChecksValue = checkValue.anamnesisChecksValue;
             newCheckValue.comment = checkValue.comment;
             newCheckValue.truth = checkValue.truth;
             log("Anamnesis form " + checkValue.anamnesisForm);
             if(checkValue.anamnesisForm){
               newCheckValue.anamnesisForm = local.AnamnesisForm.findByOrigId(checkValue.anamnesisForm.id);//problem
             }
             if(checkValue.anamnesisCheck){
               newCheckValue.anamnesisCheck = local.AnamnesisCheck.findByOrigId(checkValue.anamnesisCheck.id);
             }
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n newCheckValue.origId: ");
					sb.append(newCheckValue?.origId);
					sb.append( "\n newCheckValue.anamnesisChecksValue: ");
					sb.append(newCheckValue?.anamnesisChecksValue);
					sb.append( "\n newCheckValue.comment: ");
					sb.append(newCheckValue?.comment);
					sb.append( "\n newCheckValue.truth: ");
					sb.append(newCheckValue?.truth);
					log.debug( "save newCheckValue: " + sb.toString());
			 }
             newCheckValue.save();
             importMessage(messages,"${message(code: 'default.AnamnesisChecksValue.message')}", ""+checkValue.id);
          }else {
            existsMessage(messages,"${message(code: 'default.AnamnesisChecksValue.message')}", ""+checkValue.id);
          }
        }


		log.info("add standardizedPatient")
         def patients = remote.StandardizedPatient.list();
         for (remote.StandardizedPatient patient : patients ){
           if(!(local.StandardizedPatient.findByOrigId(patient.id))){

             local.StandardizedPatient  newPatient = new local.StandardizedPatient();
             newPatient.origId = patient.id;

             newPatient.birthday = new LocalDate(patient.birthday.getTime());
             newPatient.city = patient.city;
             newPatient.email = patient.email;
             newPatient.gender = patient.gender;
             newPatient.height = patient.height;
             newPatient.immagePath = patient.immagePath;
             newPatient.maritalStatus = patient.maritalStatus;
             newPatient.mobile = patient.mobile;
             newPatient.name = patient.name;
             newPatient.postalCode = patient.postalCode;

             newPatient.preName = patient.preName;
             newPatient.socialInsuranceNo = patient.socialInsuranceNo;
             newPatient.street = patient.street;
             newPatient.telephone = patient.telephone;
             newPatient.telephone2 = patient.telephone2;
             newPatient.videoPath = patient.videoPath;
             newPatient.weight = patient.weight;
             newPatient.workPermission = patient.workPermission;
             if (patient.anamnesisForm){
                newPatient.anamnesisForm = local.AnamnesisForm.findByOrigId(patient.anamnesisForm.id);
             }
             if (patient.description){
                    newPatient.description = local.Description.findByOrigId(patient.description.id);
             }
             if (patient.profession){
                    newPatient.profession = local.Profession.findByOrigId(patient.profession.id);
             }
             if (patient.nationality){
                    newPatient.nationality = local.Nationality.findByOrigId(patient.nationality.id);
             }

             if (patient.bankaccount){
                 newPatient.bankaccount = local.Bankaccount.findByOrigId(patient.bankaccount.id);// TOdo
                  }
		     if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n origId: ");
					sb.append(newPatient?.origId);
					sb.append( "\n birthday: ");
					sb.append(newPatient?.birthday);
					sb.append( "\n city: ");
					sb.append(newPatient?.city);
					sb.append( "\n email: ");
					sb.append(newPatient?.email);
					sb.append( "\n gender: ");
					sb.append(newPatient?.gender);
					sb.append( "\n height: ");
					sb.append(newPatient?.height);
					sb.append( "\n immagePath: ");
					sb.append(newPatient?.immagePath);
					sb.append( "\n maritalStatus: ");
					sb.append(newPatient?.maritalStatus);
					sb.append( "\n mobile: ");
					sb.append(newPatient?.mobile);
					sb.append( "\n name: ");
					sb.append(newPatient?.name);
					sb.append( "\n postalCode: ");
					sb.append(newPatient?.postalCode);
					sb.append( "\n preName: ");
					sb.append(newPatient?.preName);			
					sb.append( "\n socialInsuranceNo: ");
					sb.append(newPatient?.socialInsuranceNo);
					sb.append( "\n street ");
					sb.append(newPatient?.street);
					sb.append( "\n telephone: ");
					sb.append(newPatient?.telephone);
					sb.append( "\n telephone2: ");
					sb.append(newPatient?.telephone2);
					sb.append( "\n videoPath: ");
					sb.append(newPatient?.videoPath);
					sb.append( "\n weight: ");
					sb.append(newPatient?.weight);
					sb.append( "\n workPermission: ");
					sb.append(newPatient?.workPermission);
					sb.append( "\n anamnesisForm: ");
					sb.append(newPatient?.anamnesisForm);
					sb.append( "\n description: ");
					sb.append(newPatient?.description);
					sb.append( "\n profession: ");
					sb.append(newPatient?.profession);
					sb.append( "\n nationality: ");
					sb.append(newPatient?.nationality);
					sb.append( "\n bankaccount: ");
					sb.append(newPatient?.bankaccount);
					log.debug( "save newPatient: " + sb.toString());
			 }
             newPatient.save();



             importMessage(messages,"${message(code: 'default.StandardizedPatient.message')}", ""+patient.id);
			  log.info("add user")
              User user = new User();

              log("1 " + user);

              user.userName = newPatient.name;
              user.userEmail = newPatient.email;
              user.passwordHash = hashPassword(""+newPatient.socialInsuranceNo,user.userName);
              user.isActive = true;

              log("2 " + user);
              log("2 " + user.userEmail);

              def roles = [];
              roles.add(Role.findByRoleName(USER_ROLE));

              user.roles = roles;

              user.standardizedPatient = newPatient;
			   if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n userName: ");
					sb.append(user?.userName);
					sb.append( "\n userEmail: ");
					sb.append(user?.userEmail);
					sb.append( "\n passwordHash: ");
					sb.append(user?.passwordHash);
					sb.append( "\n isActive: ");
					sb.append(user?.isActive);
					log.debug( "save user: " + sb.toString());
				}
              user.save();

                createUserMessage(messages,user.userName,user.passwordHash)

           }else {
              existsMessage(messages,"${message(code: 'default.StandardizedPatient.message')}", ""+patient.id);
          }

         }

		log.info("add langSkills")
       def langSkills =  remote.LangSkill.list();
        for (remote.LangSkill langSkill : langSkills ){
          if(!(local.LangSkill.findByOrigId(langSkill.id))){
             local.LangSkill newLangSkill = new local.LangSkill();
             newLangSkill.origId = langSkill.id;
                         newLangSkill.skill = langSkill.skill;
                         if(langSkill.standardizedPatient){
                                newLangSkill.standardizedPatient = local.StandardizedPatient.findByOrigId(langSkill.standardizedPatient.id);
                         }
                         if(langSkill.spokenLanguage){
                                newLangSkill.spokenLanguage = local.SpokenLanguage.findByOrigId(langSkill.spokenLanguage.id);
             }
			 if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n origId: ");
					sb.append(newLangSkill?.origId);
					sb.append( "\n skill: ");
					sb.append(newLangSkill?.skill);
					sb.append( "\n standardizedPatient: ");
					sb.append(newLangSkill?.standardizedPatient);
					sb.append( "\n spokenLanguage: ");
					sb.append(newLangSkill?.spokenLanguage);
					log.debug( "save newLangSkill: " + sb.toString());
				}
             newLangSkill.save();

             importMessage(messages,"${message(code: 'default.LangSkill.message')}", ""+langSkill.id);
          }else {
            existsMessage(messages,"${message(code: 'default.LangSkill.message')}", ""+langSkill.id);
          }
         }

        [messages: messages]

   }


    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method save() with params : "+params)
		}
        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);


        if(passwordsMatch){

             def userInstance = new User(params)

             handleInboundPassword(userInstance);

                    if (!userInstance.save(flush: true)) {
                        render(view: "create", model: [userInstance: userInstance])
                        return
                    }
                    flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect(action: "show", id: userInstance.id)
        }else{
            flash.message = message(code: 'default.password.message')
            redirect(action: "create")
        }
    }


    def show() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method show() with params : "+params)
		}
        def userInstance = User.get(params.id)
        if (!userInstance) {
           // flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

          handleOutboundPassword(userInstance);
          [userInstance: userInstance]

    }

    def edit() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method edit() with params : "+params)
		}

        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }



        [userInstance: userInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method update() with params : "+params)
		}


        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);
        def userInstance = User.get(params.id)
        if (!passwordsMatch){
            render(view: "edit", model: [userInstance: userInstance])
            return;
        }


        handleInboundPassword(params);



        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()



            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete() {
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method delete() with params : "+params)
		}
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def clearData(){
		log.info("clearData in UserController")
       // User.deleteAll(User.list());
       // Role.deleteAll(Role.list());
        log("" +local.Scar.list());
        local.StandardizedPatient.deleteAll(local.StandardizedPatient.list());
         local.AnamnesisForm.deleteAll(local.AnamnesisForm.list());
         local.AnamnesisCheck.deleteAll(local.AnamnesisCheck.list());
         local.AnamnesisChecksValue.deleteAll(local.AnamnesisChecksValue.list());
         local.Scar.deleteAll(local.Scar.list());

         local.Bankaccount.deleteAll(local.Bankaccount.list());
         local.Description.deleteAll(local.Description.list());
         local.SpokenLanguage.deleteAll(local.SpokenLanguage.list());
         local.Nationality.deleteAll(local.Nationality.list());
                 local.Profession.deleteAll(local.Profession.list());


                 local.LangSkill.deleteAll(local.LangSkill.list());

        render "ok"
    }

    private void setupDefaultData(){
		 log.info("setupDefaultData in UserController")
            if (Role.list().size() == 0){

                Role adminRole = new Role();
                adminRole.roleName = ADMIN_ROLE;
                adminRole.roleDescription = "Administrate Users";
				log.info("add adminRole")
                adminRole.save();

                Role userRole = new Role();
                userRole.roleName = USER_ROLE;
                userRole.roleDescription = "Normal Users";
				log.info("add userRole")
                userRole.save();

                User admin = new User();
                User user1 = new User();

                log("1 " + admin);

                admin.userName = grailsApplication.config.sp_portal.admin.username;
                admin.userEmail = grailsApplication.config.sp_portal.admin.email;
                admin.passwordHash = hashPassword(""+grailsApplication.config.sp_portal.admin.password,admin.userName);
                admin.isActive = true;




                log("2 " + admin);
                log("2 " + admin?.userEmail);

                def roles = [];
                roles.add(Role.findByRoleName(ADMIN_ROLE));


                admin.roles = roles;
				log.info("add admin")
                admin.save();


                // test data
                user1.userName = "user1";
                user1.userEmail = "a@b";
                user1.passwordHash = hashPassword("user1");
                user1.isActive = true;


                def roles2 = [];
                roles2.add(Role.findByRoleName(USER_ROLE));

                user1.roles = roles2;
				log.info("add user1")
                user1.save();

            }
    }





    private boolean comparePasswords(String p1,String p2){
		if(log.isTraceEnabled()){
			log.trace(">> In class UserController Method comparePasswords with p1 : "+p1+" and "+p2)
		}
        log("Comparing passwords " + p1 + "  and " + p2  );
        if ( p1 != p2) {
             flash.message = message(code: 'default.password.match.message');
             log.info("comparePasswords returning false");
             return false;
        }
        log.info("comparePasswords returning true");
        return true;
    }

    def welcome(){


    }


 }
