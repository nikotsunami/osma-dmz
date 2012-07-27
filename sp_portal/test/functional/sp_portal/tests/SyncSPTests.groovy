package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import sp_portal.pages.*;
import grails.util.BuildSettingsHolder
import grails.util.BuildSettings
import static org.junit.Assert.*;

class SyncSPTests {
    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper()

    @Test
    public void test001LoginAdmin() {
        // first initialize the system with a single login/logout
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        adminPage.clickLogout()

    }

    @Test
    public void test002CreateAUserViaSyncOperation() {

        String baseURL = null;
        if (System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY) == null) {
            baseURL = System.getProperty("grails.functional.test.baseURL")

        } else {
            baseURL = System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY);
        }

        if (baseURL == null){
            baseURL = "http://localhost:8090/sp_portal"
        }


        String dataStr = getTestData();
        dataStr = dataStr.replaceAll("\n", "")
        dataStr = dataStr.replaceAll("  ", "")

        def dataImpExpPage  = webdriver.open('/dataImportExport/test', DataImportExportTestPage)
        dataImpExpPage.submitData(dataStr);


        LoginPage loginPage = webdriver.open('/', LoginPage)

        //qqq
        UserHomePage userHomePage = loginPage.loginUser("beddebu@hss.ch", "1234567891234")

        userHomePage.clickMyAccount()
        userHomePage.clickPersonalDetails()
        userHomePage.clickBanksDetails()
		assertFalse(userHomePage.isTextPresent("Bankaccount not found with id null"))
        userHomePage.clickQuestions()
        userHomePage.clickSelectAvailableDates()
        userHomePage.clickLogout()


    }
	
	
	@Test
    public void test003MyAccountLogin() {


        LoginPage loginPage = webdriver.open('/', LoginPage)
		
        def userPage = loginPage.loginUser("beddebu@hss.ch", "1234567891234");
		
		
        MyAccountPage accountPage = userPage.clickMyAccount();
		
		MyAccountEditPage myAccountEditPage = accountPage.clickEdit();
		
		
		myAccountEditPage.enterPassword("123")
		myAccountEditPage.enterConfirmPassword("12")
		myAccountEditPage.clickUpdate()
		
		myAccountEditPage.assertTextPresent("Passwords do not match");
		
		
		userPage.clickLogout()
		


    }
	
	@Test
	// so if passwirds are oth empty the password won't change
    public void test004MyAccountLogin() {


        LoginPage loginPage = webdriver.open('/', LoginPage)
		
        def userPage = loginPage.loginUser("beddebu@hss.ch", "1234567891234");
		
		
        MyAccountPage accountPage = userPage.clickMyAccount();
		
		MyAccountEditPage myAccountEditPage = accountPage.clickEdit();
		
		
		myAccountEditPage.enterPassword("")
		myAccountEditPage.enterConfirmPassword("")
		myAccountEditPage.clickUpdate()
				
		
		userPage.clickLogout()
		
        userPage = loginPage.loginUser("beddebu@hss.ch", "1234567891234");


    }	
	
		@Test
	//  change password and log in with it 
    public void test005MyAccountLogin() {


        LoginPage loginPage = webdriver.open('/', LoginPage)
		
        def userPage = loginPage.loginUser("beddebu@hss.ch", "1234567891234");
		
		
        MyAccountPage accountPage = userPage.clickMyAccount();
		
		MyAccountEditPage myAccountEditPage = accountPage.clickEdit();
		
		
		myAccountEditPage.enterPassword("123")
		myAccountEditPage.enterConfirmPassword("123")
		myAccountEditPage.clickUpdate()
		
	
		
		userPage.clickLogout()
		
        userPage = loginPage.loginUser("beddebu@hss.ch", "123");


    }	
	
	
	
	

   private String getTestData(){
        def ret = $/
                {
                   "anamnesisForm":{
                      "anamnesischecksvalues":[
                         {
                            "anamnesisChecksValue":null,
                            "anamnesischeck":{
                               "anamnesisCheckTitle":{
                                  "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                  "id":1,
                                  "sort_order":1,
                                  "text":"Konsumverhalten",
                                  "version":0
                               },
                               "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                               "id":1,
                               "sort_order":1,
                               "text":"Rauchen Sie?",
                               "title":null,
                               "type":"QUESTION_YES_NO",
                               "userSpecifiedOrder":null,
                               "value":"",
                               "version":0
                            },
                            "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                            "comment":null,
                            "id":14,
                            "truth":false,
                            "version":0
                         },
                         {
                            "anamnesisChecksValue":null,
                            "anamnesischeck":{
                               "anamnesisCheckTitle":{
                                  "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                  "id":2,
                                  "sort_order":3,
                                  "text":"Krankengeschichte",
                                  "version":0
                               },
                               "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                               "id":5,
                               "sort_order":1,
                               "text":"Leiden Sie unter Diabetes?",
                               "title":null,
                               "type":"QUESTION_YES_NO",
                               "userSpecifiedOrder":null,
                               "value":"",
                               "version":0
                            },
                            "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                            "comment":null,
                            "id":15,
                            "truth":false,
                            "version":0
                         }
                      ],
                      "class":"ch.unibas.medizin.osce.domain.AnamnesisForm",
                      "createDate":"2009-09-19T00:00:00Z",
                      "id":6,
                      "scars":[
                         {
                            "bodypart":"Hals",
                            "class":"ch.unibas.medizin.osce.domain.Scar",
                            "id":11,
                            "traitType":"SCAR",
                            "version":0
                         }
                      ],
                      "version":0
                   },
                   "bankAccount":{
                      "BIC":"BENDSFF1JEV",
                      "IBAN":"CH78 5685 7565 4364 7",
                      "bankName":"KTS",
                      "city":null,
                      "class":"ch.unibas.medizin.osce.domain.Bankaccount",
                      "country":null,
                      "id":31,
                      "ownerName":null,
                      "postalCode":null,
                      "version":0
                   },
                   "birthday":"1965-09-24T00:00:00Z",
                   "city":"Basel",
                   "class":"ch.unibas.medizin.osce.domain.StandardizedPatient",
                   "descriptions":null,
                   "email":"beddebu@hss.ch",
                   "gender":"FEMALE",
                   "height":182,
                   "id":23,
                   "immagePath":null,
                   "langskills":[
                      {
                         "class":"ch.unibas.medizin.osce.domain.LangSkill",
                         "id":12,
                         "skill":"NATIVE_SPEAKER",
                         "spokenlanguage":{
                            "class":"ch.unibas.medizin.osce.domain.SpokenLanguage",
                            "id":4,
                            "languageName":"Deutsch",
                            "version":0
                         },
                         "version":0
                      }
                   ],
                   "maritalStatus":null,
                   "mobile":"078 586 29 84",
                   "name":"Buser",
                   "nationality":{
                      "class":"ch.unibas.medizin.osce.domain.Nationality",
                      "id":6,
                      "nationality":"Deutschland",
                      "version":0
                   },
                   "postalCode":4051,
                   "preName":"Bettina",
                   "profession":{
                      "class":"ch.unibas.medizin.osce.domain.Profession",
                      "id":6,
                      "profession":"Florist/in",
                      "version":0
                   },
                   "socialInsuranceNo":"1234567891234",
                   "status":null,
                   "street":"Rankenbergweg 1",
                   "telephone":null,
                   "telephone2":null,
                   "version":0,
                   "videoPath":null,
                   "weight":82,
                   "workPermission":null
                }
                /$
            return ret.toString();

    }

}
