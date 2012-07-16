package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import sp_portal.pages.*;
import grails.util.BuildSettingsHolder
import grails.util.BuildSettings

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


        String url = "${baseURL}dataImportExport/importSP?data=" + getTestData();
        url = url.replaceAll("\n", "")
        url = url.replaceAll("  ", "")

    println(" URl  ${url} ");

        webdriver.open(url)

        String pageSource = webdriver.driver.getPageSource()
        println("page source is  ${pageSource}");

        LoginPage loginPage = webdriver.open('/', LoginPage)

        //qqq
        UserHomePage userHomePage = loginPage.loginUser("qqq@rrr.com", "qqq")

        userHomePage.clickMyAccount()
        userHomePage.clickPersonalDetails()
        userHomePage.clickBanksDetails()
        userHomePage.clickQuestions()
        userHomePage.clickSelectAvailableDates()
        userHomePage.clickLogout()


    }

   private String getTestData(){
        def ret = $/
                {
                       "class":"ch.unibas.medizin.osce.domain.StandardizedPatient",
                       "anamnesisForm":{
                          "class":"ch.unibas.medizin.osce.domain.AnamnesisForm",
                          "anamnesisChecksValues":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                                "anamnesisCheck":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                   "id":3,
                                   "sortOrder":10,
                                   "text":"Nehmen Sie zurzeit regelmässig Medikamente ein?",
                                   "title":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                      "id":12,
                                      "sortOrder":9,
                                      "text":"Treatment history category",
                                      "title":null,
                                      "type":"QUESTION_TITLE",
                                      "userSpecifiedOrder":null,
                                      "value":""
                                   },
                                   "type":"QUESTION_YES_NO",
                                   "userSpecifiedOrder":null,
                                   "value":""
                                },
                                "anamnesisChecksValue":null,
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":"this is a comment 3",
                                "id":9,
                                "truth":false
                             },
                             {
                                "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                                "anamnesisCheck":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                   "id":1,
                                   "sortOrder":2,
                                   "text":"Rauchen Sie?",
                                 "title":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                      "id":101,
                                      "sortOrder":1,
                                      "text":"Personal lifestyle category",
                                      "title":null,
                                      "type":"QUESTION_TITLE",
                                      "userSpecifiedOrder":null,
                                      "value":""
                                   },
                                   "type":"QUESTION_MULT_S",
                                   "userSpecifiedOrder":null,
                                   "value":"oft|mittel|selten"
                                },
                                "anamnesisChecksValue":"1-0-0",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":"this is a comment 2",
                                "id":8,
                                "truth":true
                             },
                             {
                                "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                                "anamnesisCheck":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                   "id":2,
                                   "sortOrder":2,
                                   "text":"Rauchen Sie 222?",
                                   "title":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                      "id":100,
                                      "sortOrder":1,
                                      "text":"Personal lifestyle category",
                                      "title":null,
                                      "type":"QUESTION_TITLE",
                                      "userSpecifiedOrder":null,
                                      "value":""
                                   },
                                   "type":"QUESTION_OPEN",
                                   "userSpecifiedOrder":null,
                                   "value":"JDJDJDDJDJDJ"
                                },
                                "anamnesisChecksValue":"1",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":"this is a comment 1",
                                "id":80,
                                "truth":true
                             }
                          ],
                          "createDate":new Date(1279382400000),
                          "id":3,
                          "scars":[{
                                   "id":9,
                                   "traitType":"TATTOO",
                                   "bodypart":"Oberschenkel (links)"}
                           ],
                          "standardizedPatients":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.StandardizedPatient"
                             }
                          ]
                       },
                       "bankaccount":{
                          "class":"ch.unibas.medizin.osce.domain.Bankaccount",
                          "bankName":"Basler Kantonalbank",
                          "bic":"GENODEF1JEV",
                          "city":"SHanghai",
                          "iban":"CH46 3948 4853 2029 3",
                          "id":51,
                          "ownerName":"sqq",
                          "postalCode":241000,
                          "standardizedPatients":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.StandardizedPatient"
                             }
                          ]
                       },
                       "birthday":new Date(1279382400000),
                       "city":"Metz",
                       "description":{"description":"this is a description", "id":35},
                       "email":"qqq@rrr.com",
                       "gender":"FEMALE",
                       "height":162,
                       "immagePath": "/image/path",
                       "maritalStatus":"DIVORCED",
                       "mobile":"078 427 24 85",
                       "name":"Lamarie",
                       "nationality":{
                          "class":"ch.unibas.medizin.osce.domain.Nationality",
                          "nationality":"Frankreich",
                          "id":7
                       },
                       "id":5711,
                       "postalCode":4057,
                       "preName":"Marianne",
                       "profession":{
                          "class":"ch.unibas.medizin.osce.domain.Profession",
                          "id":3,
                          "profession":"Bauarbeiter/in"
                       },
                       "socialInsuranceNo":"qqq",
                       "street":"Feldbergstrasse 145",
                       "telephone":"123456789",
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    }
                /$
            return ret.toString();

    }

}
