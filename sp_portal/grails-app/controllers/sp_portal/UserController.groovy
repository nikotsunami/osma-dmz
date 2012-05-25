package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;

class UserController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";


    def beforeInterceptor = [action:this.&isLoggedIn, except:["login", "authenticate", "index"]]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        log("IN ACTION LIST");
        params.max = Math.min(params.max ? params.int('max') : 10, 100)


        [userInstanceList: User.list(params), userInstanceTotal: User.count()]

    }

    def create() {
          params.isActive=true

          def standardizedPatient = new local.StandardizedPatient();


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
        def messages = [];
        log("Import Data Pressed");

        def needsTitleChecks = [];

         def scars =  remote.Scar.list();
         for (remote.Scar scar : scars ){
          if(!(local.Scar.findByOrigId(scar.id))){


             local.Scar newScar = new local.Scar();
             newScar.origId = scar.id;
             newScar.bodypart = scar.bodypart;
             newScar.traitType = scar.traitType;

             newScar.save();
             importMessage(messages,"scar", ""+scar.id);
          } else {
            existsMessage(messages,"scar", ""+scar.id);
          }
         }


        def anamnesisCheckList =  remote.AnamnesisCheck.list();
        // add AnamnesisCheck data which type is title


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

             newCheck.save();
             importMessage(messages,"AnamnesisCheck", ""+titleCheck.id);
          }else {
            existsMessage(messages,"AnamnesisCheck", ""+titleCheck.id);
          }

        }


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
                 newCheck.save();
                 importMessage(messages,"AnamnesisCheck", ""+check.id);
              }
          }else {
            existsMessage(messages,"AnamnesisCheck", ""+check.id);
          }

        }


        def anamnesisForm  =  remote.AnamnesisForm .list();
        for (remote.AnamnesisForm anamnesisFormValue : anamnesisForm ){
          if(!(local.AnamnesisForm.findByOrigId(anamnesisFormValue.id))){

             local.AnamnesisForm newAnamnesisForm = new local.AnamnesisForm();
             newAnamnesisForm.origId = anamnesisFormValue.id;
             newAnamnesisForm.createDate = anamnesisFormValue.createDate;
             newAnamnesisForm.save();
                         importMessage(messages,"AnamnesisForm", ""+anamnesisFormValue.id);
                    }else {
            existsMessage(messages,"AnamnesisForm", ""+anamnesisFormValue.id);
          }
        }

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
              newBankaccount.save();

                            importMessage(messages,"Bankaccount", ""+bankAccount.id);
                    }else {
            existsMessage(messages,"Bankaccount", ""+bankAccount.id);
          }
        }




        def descriptions   =  remote.Description.list();
        for (remote.Description description : descriptions ){

          if(!(local.Description.findByOrigId(description.id))){

             local.Description newDescription = new local.Description();
             newDescription.origId = description.id;
             newDescription.description = description.description;
             newDescription.save();

             importMessage(messages,"Description", ""+description.id);
                    }else {
            existsMessage(messages,"Description", ""+description.id);
          }
        }



        def languages =  remote.SpokenLanguage.list();
        for (remote.SpokenLanguage language : languages ){
          if(!(local.SpokenLanguage.findByOrigId(language.id))){

             local.SpokenLanguage newLanguage = new local.SpokenLanguage();
             newLanguage.origId = language.id;
             newLanguage.languageName = language.languageName;
             newLanguage.save();

            importMessage(messages,"SpokenLanguage", ""+language.id);
          }else {
            existsMessage(messages,"SpokenLanguage", ""+language.id);
          }

        }

        def nationalitys =  remote.Nationality.list();
        for (remote.Nationality nationality : nationalitys ){
          if(!(local.Nationality.findByOrigId(nationality.id))){

             local.Nationality newNationality = new local.Nationality();
             newNationality.origId = nationality.id;
             newNationality.nationality = nationality.nationality;
             newNationality.save();

             importMessage(messages,"Nationality", ""+nationality.id);
                    }else {
            existsMessage(messages,"Nationality", ""+nationality.id);
          }
        }

        def professions =  remote.Profession.list();
        for (remote.Profession profession : professions ){
          if(!(local.Profession.findByOrigId(profession.id))){

             local.Profession newProfession = new local.Profession();
             newProfession.origId = profession.id;
             newProfession.profession = profession.profession;
             newProfession.save();

             importMessage(messages,"Profession", ""+profession.id);
                    }else {
            existsMessage(messages,"Profession", ""+profession.id);
          }
        }



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
             newCheckValue.save();

             importMessage(messages,"AnamnesisChecksValue", ""+checkValue.id);
          }else {
            existsMessage(messages,"AnamnesisChecksValue", ""+checkValue.id);
          }
        }



         def patients = remote.StandardizedPatient.list();
         for (remote.StandardizedPatient patient : patients ){
           if(!(local.StandardizedPatient.findByOrigId(patient.id))){

             local.StandardizedPatient  newPatient = new local.StandardizedPatient();
             newPatient.origId = patient.id;

             newPatient.birthday = patient.birthday;
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
             newPatient.save();



             importMessage(messages,"StandardizedPatient", ""+patient.id);

              User user = new User();

              log("1 " + user);

              user.userName = newPatient.email;
              user.userEmail = newPatient.email;
              user.passwordHash = hashPassword(""+newPatient.socialInsuranceNo,user.userName);
              user.isActive = true;

              log("2 " + user);
              log("2 " + user.userEmail);

              def roles = [];
              roles.add(Role.findByRoleName(USER_ROLE));

              user.roles = roles;

              user.standardizedPatient = newPatient;
              user.save();

                createUserMessage(messages,user.userName,user.passwordHash)

           }else {
              existsMessage(messages,"StandardizedPatient", ""+patient.id);
          }

         }


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
             newLangSkill.save();

             importMessage(messages,"LangSkill", ""+langSkill.id);
          }else {
            existsMessage(messages,"LangSkill", ""+langSkill.id);
          }
         }

        [messages: messages]

   }


    def save() {

				println("*******save the new user params: " + params);

        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);


        if(passwordsMatch){
               
             def userInstance = new User(params)
             
             handleInboundPassword(userInstance);

                def standardizedPatient = new local.StandardizedPatient();

                standardizedPatient.email = userInstance.userEmail;


                userInstance.standardizedPatient = standardizedPatient.save();

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
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        handleOutboundPassword(userInstance);
        [userInstance: userInstance]
    }

    def edit() {


        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }



        [userInstance: userInstance]
    }

    def update() {


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
            if (Role.list().size() == 0){

                Role adminRole = new Role();
                adminRole.roleName = ADMIN_ROLE;
                adminRole.roleDescription = "Administrate Users";
                adminRole.save();

                Role userRole = new Role();
                userRole.roleName = USER_ROLE;
                userRole.roleDescription = "Normal Users";
                userRole.save();

                User admin = new User();
                User user1 = new User();

                log("1 " + admin);

                admin.userName = grailsApplication.config.sp_portal.admin.username;
                admin.userEmail = grailsApplication.config.sp_portal.admin.email;
                admin.passwordHash = hashPassword(""+grailsApplication.config.sp_portal.admin.password,admin.userName);
                admin.isActive = true;




                log("2 " + admin);
                log("2 " + admin.userEmail);

                def roles = [];
                roles.add(Role.findByRoleName(ADMIN_ROLE));


                admin.roles = roles;

                admin.save();


                // test data
                user1.userName = "user1";
                user1.userEmail = "a@b";
                user1.passwordHash = hashPassword("user1");
                user1.isActive = true;


                def roles2 = [];
                roles2.add(Role.findByRoleName(USER_ROLE));

                user1.roles = roles2;

                user1.save();

            }
    }





    private boolean comparePasswords(String p1,String p2){
        log("Comparing passwords " + p1 + "  and " + p2  );
        if ( p1 != p2) {
             flash.message = message(code: 'default.password.match.message');
             log(" returning false");
             return false;
        }
        log(" returning true");
        return true;
    }


 }
