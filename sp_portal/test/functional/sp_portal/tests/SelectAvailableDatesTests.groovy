package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.junit.After
import sp_portal.pages.*;
import grails.util.BuildSettingsHolder
import grails.util.BuildSettings
import static org.junit.Assert.*;

class SelectAvailableDatesTests {
    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper();
	
	private static boolean initialized = false;
	
	@Before
	public void initialize(){
		if (!initialized){
			String dataStr = getTestData();
			dataStr = dataStr.replaceAll("\n", "")
			dataStr = dataStr.replaceAll("  ", "")


			def dataImpExpPage  = webdriver.open('/dataImportExport/test', DataImportExportTestPage)

			dataImpExpPage.submitData(dataStr);
			initialized = true;
		}
	}
	
	@After
	public void deleteOutputFile(){
		webdriver = null;
	}
	
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


//        String dataStr = getTestData();
//        dataStr = dataStr.replaceAll("\n", "")
//        dataStr = dataStr.replaceAll("  ", "")
//
//
//        def dataImpExpPage  = webdriver.open('/dataImportExport/test', DataImportExportTestPage)
//
//        dataImpExpPage.submitData(dataStr);

        LoginPage loginPage = webdriver.open('/', LoginPage)

        //qqq
        UserHomePage userHomePage = loginPage.loginUser("SelectAvailableDatesTests@user.ch", "1234567891234")

        userHomePage.clickMyAccount()
        userHomePage.clickPersonalDetails()
        userHomePage.clickBanksDetails()
        assertFalse(userHomePage.isTextPresent("Bankaccount not found with id null"))
        userHomePage.clickQuestions()
        userHomePage.clickSelectAvailableDates()
        userHomePage.clickLogout()


    }

     @Test
     public void test003ClickCheck(){
         String baseURL = null;
        if (System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY) == null) {
            baseURL = System.getProperty("grails.functional.test.baseURL")

        } else {
            baseURL = System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY);
        }

        if (baseURL == null){
            baseURL = "http://localhost:8090/sp_portal"
        }

        String dataDay=getTestData2();
        dataDay = dataDay.replaceAll("\n", "")
        dataDay = dataDay.replaceAll("  ", "")
        def dataImpDays=webdriver.open('/osceSync/test',OsceSynPage)
        dataImpDays.submitData(dataDay);


         LoginPage loginPage = webdriver.open('/', LoginPage)
        UserHomePage userHomePage = loginPage.loginUser("SelectAvailableDatesTests@user.ch", "1234567891234")
        SelectAvailableDatesPage selectPage=userHomePage.clickSelectAvailableDates()
        selectPage.chooseDays("training.1");
        selectPage.chooseDays("osce.1");

        selectPage.clickSave();
        selectPage.assertTextPresent("Thank you for your participation");



     }

     @Test
     public void test004ClickTrainDay(){
         String baseURL = null;
        if (System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY) == null) {
            baseURL = System.getProperty("grails.functional.test.baseURL")

        } else {
            baseURL = System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY);
        }

        if (baseURL == null){
            baseURL = "http://localhost:8090/sp_portal"
        }

        String dataDay=getTestData2();
        dataDay = dataDay.replaceAll("\n", "")
        dataDay = dataDay.replaceAll("  ", "")
        def dataImpDays=webdriver.open('/osceSync/test',OsceSynPage)
        dataImpDays.submitData(dataDay);


         LoginPage loginPage = webdriver.open('/', LoginPage)
        UserHomePage userHomePage = loginPage.loginUser("SelectAvailableDatesTests@user.ch", "1234567891234")
        SelectAvailableDatesPage selectPage=userHomePage.clickSelectAvailableDates()
        selectPage.chooseDays("training.1");
        selectPage.clickSave();
        selectPage.assertTextPresent("Training Days");



     }

     @Test
     public void test005ClickOsceDay(){
         String baseURL = null;
        if (System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY) == null) {
            baseURL = System.getProperty("grails.functional.test.baseURL")

        } else {
            baseURL = System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY);
        }

        if (baseURL == null){
            baseURL = "http://localhost:8090/sp_portal"
        }

        String dataDay=getTestData2();
        dataDay = dataDay.replaceAll("\n", "")
        dataDay = dataDay.replaceAll("  ", "")
        def dataImpDays=webdriver.open('/osceSync/test',OsceSynPage)
        dataImpDays.submitData(dataDay);


         LoginPage loginPage = webdriver.open('/', LoginPage)
        UserHomePage userHomePage = loginPage.loginUser("SelectAvailableDatesTests@user.ch", "1234567891234")
        SelectAvailableDatesPage selectPage=userHomePage.clickSelectAvailableDates()
        selectPage.chooseDays("osce.1");
        selectPage.clickSave();
        selectPage.assertTextPresent("Training Days");



     }
     @Test
     public void test006ClickNull(){
         String baseURL = null;
        if (System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY) == null) {
            baseURL = System.getProperty("grails.functional.test.baseURL")

        } else {
            baseURL = System.getProperty(BuildSettings.FUNCTIONAL_BASE_URL_PROPERTY);
        }

        if (baseURL == null){
            baseURL = "http://localhost:8090/sp_portal"
        }

        String dataDay=getTestData2();
        dataDay = dataDay.replaceAll("\n", "")
        dataDay = dataDay.replaceAll("  ", "")
        def dataImpDays=webdriver.open('/osceSync/test',OsceSynPage)
        dataImpDays.submitData(dataDay);


         LoginPage loginPage = webdriver.open('/', LoginPage)
        UserHomePage userHomePage = loginPage.loginUser("SelectAvailableDatesTests@user.ch", "1234567891234")
        SelectAvailableDatesPage selectPage=userHomePage.clickSelectAvailableDates()
        selectPage.clickSave();
        selectPage.assertTextPresent("Thank you for your participation");



     }


    private String getTestData2(){
        def json  = $/
        {
              languages :[{language: "en"}],
              osceDay : [ {osceDate: "2010-08-01T08:00:00Z"},
                            {osceDate: ""}
                            ],
              trainings : [ {name: "",
                            trainingDate: "2000-06-10T00:00:00Z",
                            timeStart: "2000-06-10T09:20:00Z",
                            timeEnd: "2000-06-10T11:00:00Z"},
                            {name: "test6",
                            trainingDate: "",
                            timeStart: "2000-05-10T09:20:00Z",
                            timeEnd: "2000-05-10T11:00:00Z"},
                            {name: "test2",
                            trainingDate: "2012-05-10T00:00:00Z",
                            timeStart: "2012-05-10T09:20:00Z",
                            timeEnd: "2012-05-10T11:00:00Z"},
                            {name: "test7",
                            trainingDate: "2010-07-10T00:00:00Z",
                            timeStart: "2010-07-10T05:15:00Z",
                            timeEnd: "2010-07-10T09:00:00Z"}],
           standardizedPatient: []

            }

            /$


        return json.toString();
    }


   private String getTestData(){
        def ret = $/
                {    
         "StandardizedPatient": {
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
                   "email":"SelectAvailableDatesTests@user.ch",
                   "gender":"FEMALE",
                   "height":182,
                   "id":231,
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
                },
			"languages" :{"language": "de"}}
                /$
            return ret.toString();

    }

}
