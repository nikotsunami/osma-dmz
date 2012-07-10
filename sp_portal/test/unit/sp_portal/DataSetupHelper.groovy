package sp_portal;
import sp_portal.*;
import sp_portal.local.*;

class DataSetupHelper {

    def adminRole
    def userRole

    def adminUser
    def normalUser

    def standardizedPatient1
	def standardizedPatient2
    def bankaccount1
	
	def anamnesisForm1	
	def anamnesisCheckTitle1	
	def anamnesisCheck1
	def anamnesisCheck2
	def anamnesisChecksValue1
	def anamnesisCheck3
	
	def training1
	def osceDay1

    def getDataSetA(){
        setupRoles()
        setupUsers()
        standardizedPatient1 =  setupStandardizedPatients("")
		setupAnamnesisCheckTitle()
		setupAnamnesisCheck1()
		setupAnamnesisCheck2()
		setupAnamnesisCheck3()
		setUpTraining1()
		setUpOsceDay1()
		anamnesisCheck1.anamnesisCheckTitle = anamnesisCheckTitle1
		anamnesisCheck2.anamnesisCheckTitle = anamnesisCheckTitle1
		setupAnamnesisForm()

        assertNotNull normalUser

        normalUser.standardizedPatient = standardizedPatient1

        normalUser.save()

        assertNotNull normalUser.standardizedPatient

        setupBankAccounts()

        standardizedPatient1.bankaccount = bankaccount1
		standardizedPatient1.anamnesisForm = anamnesisForm1
        standardizedPatient1.save()

		
		setupAnamnesisCheckValue1()

        assertNotNull normalUser.standardizedPatient.bankaccount



    }



	/**
	 * Data set B has 2 setupStandardizedPatients, they currently both share a bank account and 
	 * anamnesisForm but you can change this if you need to.
	 */
   def getDataSetB(){
        setupRoles()
        setupUsers()
        standardizedPatient1 =  setupStandardizedPatients("")
		standardizedPatient2 =  setupStandardizedPatients("B")
		setupAnamnesisCheckTitle()
		setupAnamnesisCheck1()
		setupAnamnesisCheck2()
		setupAnamnesisCheck3()
		anamnesisCheck1.anamnesisCheckTitle = anamnesisCheckTitle1
		anamnesisCheck2.anamnesisCheckTitle = anamnesisCheckTitle1
		setupAnamnesisForm()

        assertNotNull normalUser

        normalUser.standardizedPatient = standardizedPatient1

        normalUser.save()

        assertNotNull normalUser.standardizedPatient

        setupBankAccounts()

        standardizedPatient1.bankaccount = bankaccount1
		standardizedPatient1.anamnesisForm = anamnesisForm1
        standardizedPatient1.save()

        standardizedPatient2.bankaccount = bankaccount1
		standardizedPatient2.anamnesisForm = anamnesisForm1
        standardizedPatient2.save()

		
		setupAnamnesisCheckValue1()

        assertNotNull normalUser.standardizedPatient.bankaccount



    }




//////////////////////////////////////////////////////////////////////////////////
    def setupRoles(){

       if (Role.list().size() == 0){

            adminRole = new Role();
            adminRole.roleName = "ADMIN_ROLE";
            adminRole.roleDescription = "Administrate Users";
            adminRole.save();

            userRole = new Role();
            userRole.roleName = "USER_ROLE";
            userRole.roleDescription = "Normal Users";

            userRole.save();
       }
    }




    def setupUsers(){

        User admin = new User();


        admin.userName = "AdminUser";
        admin.userEmail = "Admin@user";
        admin.passwordHash = "not hashed";
        admin.isActive = true;

        def roles0 = [];
        roles0.add(Role.findByRoleName("ADMIN_ROLE"));

		admin.roles = roles0;

		admin.save();

        adminUser = admin

//////////////////////////////////////////////////////////////////
        User user1 = new User();

        user1.userName = "NormalUser";
        user1.userEmail = "Normal@user";
        user1.passwordHash = "not hashed";
        user1.isActive = true;

        def roles1 = [];
        roles1.add(Role.findByRoleName("USER_ROLE"));

        user1.roles = roles1;

        user1.save();

        normalUser = user1
    }

    def setupStandardizedPatients(def prefix){
        StandardizedPatient standardizedPatient = new StandardizedPatient();
        standardizedPatient.origId = 1;
        standardizedPatient.birthday = new Date();
        standardizedPatient.city = "${prefix}Wuhu"
        standardizedPatient.email = "${prefix}sp1@test.com"
        standardizedPatient.gender = 1
        standardizedPatient.height = 81
        standardizedPatient.immagePath = "${prefix}/aa/bb"
        standardizedPatient.maritalStatus = 1
        standardizedPatient.mobile = "123454567"
        standardizedPatient.name = "smith"
        standardizedPatient.postalCode = 123456789
        standardizedPatient.preName = "bob"
        standardizedPatient.socialInsuranceNo = "1234567890123"
        standardizedPatient.street = "${prefix}a street"
        standardizedPatient.telephone = "123454567"
        standardizedPatient.telephone2 = "123454567"
        standardizedPatient.videoPath = "123454567"
        standardizedPatient.weight = 79
        standardizedPatient.workPermission = null
        standardizedPatient.anamnesisForm  = null
        standardizedPatient.description  = null
        standardizedPatient.profession  = null
        standardizedPatient.nationality  = null
        standardizedPatient.bankaccount  = null

        standardizedPatient.save();
		
		return standardizedPatient
        

    }

    def setupBankAccounts(){
        def bankaccount = new Bankaccount()
        bankaccount.bic = 'jfskhfsdhj'
        bankaccount.iban = '132654987454654'
        bankaccount.bankName = 'ICBC'
        bankaccount.city = 'Wuhu'
        bankaccount.ownerName = 'owner'
        bankaccount.postalCode = 234567891
        bankaccount.origId = 5

        bankaccount.save();

        bankaccount1 = bankaccount;

    }

	def setupAnamnesisForm(){
		def anamnesisForm = new AnamnesisForm()
		anamnesisForm.createDate = new Date()
		anamnesisForm.origId = 2
		
		anamnesisForm.save();
		
		anamnesisForm1 = anamnesisForm;
	}
	
	def setupAnamnesisCheckTitle(){
		def anamnesisCheckTitle = new AnamnesisCheckTitle();
		anamnesisCheckTitle.text = 'title1'
		anamnesisCheckTitle.sortOrder = 1
		
		anamnesisCheckTitle.save();
		
		anamnesisCheckTitle1 = anamnesisCheckTitle;
	}
	
	def setupAnamnesisCheck1(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Rauchen Sie?'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 1   // should be a boolean type
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck1 = anamnesisCheck;

	}
	
	def setupAnamnesisCheck2(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Leiden Sie unter Diabetes?'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 1
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck2 = anamnesisCheck;

	}
	
	def setupAnamnesisCheck3(){
		def anamnesisCheck = new AnamnesisCheck();
		anamnesisCheck.text = 'Describe your best ever holiday'
		anamnesisCheck.value = ''
		anamnesisCheck.sortOrder = 1
		anamnesisCheck.type = 0 // should be a string type according to yyb
		anamnesisCheck.anamnesisCheckTitle = null
		anamnesisCheck.origId = 2
		
		anamnesisCheck.save();
		
		anamnesisCheck3 = anamnesisCheck;

	}
	
	
	def setupAnamnesisCheckValue1(){
		def anamnesisChecksValue = new AnamnesisChecksValue();
		anamnesisChecksValue.origId = 5
		anamnesisChecksValue.comment = null;
		anamnesisChecksValue.truth = false
		anamnesisChecksValue.anamnesisForm = anamnesisForm1
		anamnesisChecksValue.anamnesisCheck = anamnesisCheck1
		
		anamnesisChecksValue.save()
		
		anamnesisChecksValue1 = anamnesisChecksValue;
	}
	
	def setUpTraining1(){
		def training = new Training()
		training.name = 'traning1'
		training.trainingDate = new Date(new Date().getTime()+24*60*60*1000)
		training.timeStart = new Date(new Date().getTime()+24*60*60*1000)
		training.timeEnd = new Date(new Date().getTime()+24*60*60*1000+120*60*1000)
		training.save()
		training1 = training;
	}
	
	def setUpOsceDay1(){
		def osceDay = new OsceDay()
		osceDay.osceDate = new Date(new Date().getTime()+24*60*60*1000)
		osceDay.save()
		osceDay1 = osceDay;
		
	}

}
