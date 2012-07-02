package sp_portal;
import sp_portal.*;
import sp_portal.local.*;

class DataSetupHelper {

    def adminRole
    def userRole

    def adminUser
    def normalUser

    def standardizedPatient1
    def bankaccount1

    def getDataSetA(){
        setupRoles()
        setupUsers()
        setupStandardizedPatients()

        assertNotNull normalUser

        normalUser.standardizedPatient = standardizedPatient1

        normalUser.save()

        assertNotNull normalUser.standardizedPatient

        setupBankAccounts()

        standardizedPatient1.bankaccount = bankaccount1
        standardizedPatient1.save()

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

    def setupStandardizedPatients(){
        StandardizedPatient standardizedPatient = new StandardizedPatient();
        standardizedPatient.origId = 1;
        standardizedPatient.birthday = new Date();
        standardizedPatient.city = "Wuhu"
        standardizedPatient.email = "sp1@test.com"
        standardizedPatient.gender = 1
        standardizedPatient.height = 81
        standardizedPatient.immagePath = "/aa/bb"
        standardizedPatient.maritalStatus = 1
        standardizedPatient.mobile = "123454567"
        standardizedPatient.name = "smith"
        standardizedPatient.postalCode = 123456789
        standardizedPatient.preName = "bob"
        standardizedPatient.socialInsuranceNo = "1234567890"
        standardizedPatient.street = "a street"
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

        standardizedPatient1 = standardizedPatient

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



}
