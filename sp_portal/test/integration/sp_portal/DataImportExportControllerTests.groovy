package sp_portal

import static org.junit.Assert.*
import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.web.json.*;
import grails.converters.deep.JSON
import org.joda.time.LocalDate;
import grails.plugin.jodatime.converters.JodaConverters 

class DataImportExportControllerTests extends GroovyTestCase{

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testImportSP(){


                def controller = new DataImportExportController()
                Role role1 = new Role();
                role1.roleName= "USER_ROLE";
                role1.save();


                controller.params.data = getTestData1();

                def response = controller.response;

                    def model = controller.importSP()

                    def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);

                    def list = local.StandardizedPatient.list();

                    def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
                    def scar = local.Scar.findByOrigId(9);

                    def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
                    def anamnesisCheck1 = local.AnamnesisCheck.findByOrigId(3);
                    def anamnesisChecksTitle1 =  anamnesisCheck1.anamnesisCheckTitle

                    def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
                    def anamnesisCheck2 = local.AnamnesisCheck.findByOrigId(1);
                    def anamnesisChecksTitle2 = anamnesisCheck2.anamnesisCheckTitle

                    def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
                    def anamnesisCheck3 = local.AnamnesisCheck.findByOrigId(2);
                    def anamnesisChecksTitle3 = anamnesisCheck3.anamnesisCheckTitle

                    def bankaccount = local.Bankaccount.findByOrigId(51);

                    def description = local.Description.findByOrigId(35);

                    def nationality = local.Nationality.findByOrigId(7);

                    def profession=local.Profession.findByOrigId(3);

                    assert list.size == 1;

     //               assert response.text == "qqq@rrr.com";

                    //-------------verify standardizedPatient-----------------
                    assertEquals "qqq@rrr.com", standardizedPatient.email;
                    assert standardizedPatient.birthday == new LocalDate(1279382400000);
                    assertEquals 1,standardizedPatient.gender
                    assertEquals 162,standardizedPatient.height
                    assertEquals null,standardizedPatient.immagePath
                    assertEquals null,standardizedPatient.maritalStatus
                    assertEquals "078 427 24 85",standardizedPatient.mobile
                    assertEquals "Lamarie",standardizedPatient.name
                    assertEquals 5711,standardizedPatient.origId
                    assertEquals 4057,standardizedPatient.postalCode
                    assertEquals "Marianne",standardizedPatient.preName
                    assertEquals "1234567891234",standardizedPatient.socialInsuranceNo
                    assertEquals "Feldbergstrasse 145",standardizedPatient.street
                    assertEquals null,standardizedPatient.telephone
                    assertEquals ";kjlkjlj",standardizedPatient.telephone2
                    assertEquals 57,standardizedPatient.weight
                    assertEquals null,standardizedPatient.workPermission
                    assertEquals "Metz",standardizedPatient.city.toString()

                    assertEquals anamnesisForm,standardizedPatient.anamnesisForm
                    assertEquals bankaccount,standardizedPatient.bankaccount
                    assertEquals null,standardizedPatient.description
                    assertEquals nationality,standardizedPatient.nationality
                    assertEquals profession,standardizedPatient.profession

                    //-------------verify bankaccount-----------------
                    assertEquals "Basler Kantonalbank", bankaccount.bankName;
                    assertEquals "GENODEF1JEV", bankaccount.bic;
                    assertEquals "SHanghai", bankaccount.city;
                    assertEquals "CH46 3948 4853 2029 3", bankaccount.iban;
                    assertEquals "sqq", bankaccount.ownerName;
                    assertEquals 241000, bankaccount.postalCode;

                    //-------------verify nationality-----------------
                    assertEquals "Frankreich", nationality.nationality;

                    //-------------verify profession-----------------
                    assertEquals "Bauarbeiter/in", profession.profession;

                    //-------------verify anamnesisForm-----------------
                    assertEquals new Date(1279382400000), anamnesisForm.createDate;

                    //-------------verify anamnesisChecksValue1-----------------
                    assertEquals null, anamnesisChecksValue1.comment;
                    assertEquals Boolean.FALSE, anamnesisChecksValue1.truth;
                    assertEquals null, anamnesisChecksValue1.anamnesisChecksValue;
                    assertEquals anamnesisCheck1, anamnesisChecksValue1.anamnesisCheck;
                    assertEquals anamnesisForm, anamnesisChecksValue1.anamnesisForm;

                    //-------------verify anamnesisChecksValue1's anamnesisCheck1-----------------
                    assertEquals 11, anamnesisCheck1.sortOrder;
                    assertEquals "Nehmen Sie zurzeit regelmässig Medikamente ein?", anamnesisCheck1.text;
                    assertEquals anamnesisChecksTitle1, anamnesisCheck1.anamnesisCheckTitle;
                    assertEquals 1, anamnesisCheck1.type;
                    assertEquals null, anamnesisCheck1.userSpecifiedOrder;
                    assertEquals "", anamnesisCheck1.value;


                    //-------------verify anamnesisChecksValue1's anamnesisCheck1's title-----------------
                    assertEquals 9, anamnesisChecksTitle1.sortOrder;
                    assertEquals "Treatment history category", anamnesisChecksTitle1.text;


                    //--------------verify anamnesisCheckVlaue2------------------
                    assertEquals null, anamnesisChecksValue2.comment;
                    assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
                    assertEquals "1-0-0", anamnesisChecksValue2.anamnesisChecksValue;
                    assertEquals anamnesisCheck2, anamnesisChecksValue2.anamnesisCheck;
                    assertEquals anamnesisForm, anamnesisChecksValue2.anamnesisForm;

                    //-------------verify anamnesisChecksValue2's anamnesisCheck2-----------------
                    assertEquals 2, anamnesisCheck2.sortOrder;
                    assertEquals "Rauchen Sie?", anamnesisCheck2.text;
                    assertEquals anamnesisChecksTitle2, anamnesisCheck2.anamnesisCheckTitle;
                    assertEquals 2, anamnesisCheck2.type;
                    assertEquals null, anamnesisCheck2.userSpecifiedOrder;
                    assertEquals "oft|mittel|selten", anamnesisCheck2.value;


                    //-------------verify anamnesisChecksValue2's anamnesisCheck2's title-----------------
                    assertEquals 2, anamnesisChecksTitle2.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle2.text;


                    //---------------verify anamnesisCheckValue3------------------
                    assertEquals null, anamnesisChecksValue3.comment;
                    assertEquals Boolean.TRUE, anamnesisChecksValue3.truth ;
                    assertEquals "1",anamnesisChecksValue3.anamnesisChecksValue;
                    assertEquals anamnesisCheck3, anamnesisChecksValue3.anamnesisCheck;
                    assertEquals anamnesisForm, anamnesisChecksValue3.anamnesisForm;

                    //-------------verify anamnesisChecksValue3's anamnesisCheck3-----------------
                    assertEquals 2, anamnesisCheck3.sortOrder;
                    assertEquals "Rauchen Sie 222?", anamnesisCheck3.text;
                    assertEquals anamnesisChecksTitle3, anamnesisCheck3.anamnesisCheckTitle;
                    assertEquals 0, anamnesisCheck3.type;
                    assertEquals null, anamnesisCheck3.userSpecifiedOrder;
                    assertEquals "JDJDJDDJDJDJ", anamnesisCheck3.value;


                    //-------------verify anamnesisChecksValue3's anamnesisCheck3's title-----------------
                    assertEquals 3, anamnesisChecksTitle3.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle3.text;


                    //-------------verify scar-----------------
                    assertEquals 1, scar.traitType;
                    assertEquals "Oberschenkel (links)", scar.bodypart;



   }


    void testImportSP2(){

                def controller = new DataImportExportController()
                Role role1 = new Role();
                role1.roleName= "USER_ROLE";
                role1.save();

                def params = controller.params;
                def response = controller.response;

                params.data = getTestData2();

                def model = controller.importSP()

                def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);

                def list = local.StandardizedPatient.list();

                def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
                def scar = local.Scar.findByOrigId(9);

                def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
                def anamnesisCheck1 = local.AnamnesisCheck.findByOrigId(3);
                def anamnesisChecksTitle1 = anamnesisCheck1.anamnesisCheckTitle

                def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
                def anamnesisCheck2 = local.AnamnesisCheck.findByOrigId(1);
                def anamnesisChecksTitle2 = anamnesisCheck2.anamnesisCheckTitle

                def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
                def anamnesisCheck3 = local.AnamnesisCheck.findByOrigId(2);
                def anamnesisChecksTitle3 = anamnesisCheck3.anamnesisCheckTitle

                def bankaccount = local.Bankaccount.findByOrigId(51);

                def description = local.Description.findByOrigId(35);

                def nationality = local.Nationality.findByOrigId(7);

                def profession=local.Profession.findByOrigId(3);

                assert list.size == 1;                

                //-------------verify standardizedPatient-----------------
                assertEquals "qqq@rrr.com", standardizedPatient.email;
                assert standardizedPatient.birthday == new LocalDate(1279382400000);
                assertEquals 1,standardizedPatient.gender
                assertEquals 162,standardizedPatient.height
                assertEquals "/image/path",standardizedPatient.immagePath
                assertEquals 3,standardizedPatient.maritalStatus
                assertEquals "078 427 24 85",standardizedPatient.mobile
                assertEquals "Lamarie",standardizedPatient.name
                assertEquals 5711,standardizedPatient.origId
                assertEquals 4057,standardizedPatient.postalCode
                assertEquals "Marianne",standardizedPatient.preName
                assertEquals "1234567891234",standardizedPatient.socialInsuranceNo
                assertEquals "Feldbergstrasse 145",standardizedPatient.street
                assertEquals "123456789",standardizedPatient.telephone
                assertEquals ";kjlkjlj",standardizedPatient.telephone2
                assertEquals 57,standardizedPatient.weight
                assertEquals null,standardizedPatient.workPermission
                assertEquals "Metz",standardizedPatient.city.toString()

                assertEquals anamnesisForm,standardizedPatient.anamnesisForm
                assertEquals bankaccount,standardizedPatient.bankaccount
                assertNotNull standardizedPatient.description
				
                assertEquals "this is a description", standardizedPatient?.description?.description
                assertEquals 35, standardizedPatient?.description?.origId
                assertEquals nationality,standardizedPatient.nationality
                assertEquals profession,standardizedPatient.profession

                assertNotNull anamnesisForm
                //-------------verify bankaccount-----------------
                assertEquals "Basler Kantonalbank", bankaccount.bankName;
                assertEquals "GENODEF1JEV", bankaccount.bic;
                assertEquals "SHanghai", bankaccount.city;
                assertEquals "CH46 3948 4853 2029 3", bankaccount.iban;
                assertEquals "sqq", bankaccount.ownerName;
                assertEquals 241000, bankaccount.postalCode;

                //-------------verify nationality-----------------
                assertEquals "Frankreich", nationality.nationality;

                //-------------verify profession-----------------
                assertEquals "Bauarbeiter/in", profession.profession;

                //-------------verify anamnesisForm-----------------
                assertEquals new Date(1279382400000), anamnesisForm.createDate;

                //-------------verify anamnesisChecksValue1-----------------
                assertEquals "this is a comment 3", anamnesisChecksValue1.comment;
                assertEquals Boolean.FALSE, anamnesisChecksValue1.truth;
                assertEquals null, anamnesisChecksValue1.anamnesisChecksValue;
                assertEquals anamnesisCheck1, anamnesisChecksValue1.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue1.anamnesisForm;

                //-------------verify anamnesisChecksValue1's anamnesisCheck1-----------------
                assertEquals 10, anamnesisCheck1.sortOrder;
                assertEquals "Nehmen Sie zurzeit regelmässig Medikamente ein?", anamnesisCheck1.text;
                assertEquals 1, anamnesisCheck1.type;
                assertEquals null, anamnesisCheck1.userSpecifiedOrder;
                assertEquals "", anamnesisCheck1.value;


                //-------------verify anamnesisChecksValue1's anamnesisCheck1's title-----------------
                assertEquals 9, anamnesisChecksTitle1.sortOrder;
                assertEquals "Treatment history category", anamnesisChecksTitle1.text;

                //--------------verify anamnesisCheckVlaue2------------------
                assertEquals "this is a comment 2", anamnesisChecksValue2.comment;
                assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
                assertEquals "1-0-0", anamnesisChecksValue2.anamnesisChecksValue;
                assertEquals anamnesisCheck2, anamnesisChecksValue2.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue2.anamnesisForm;

                //-------------verify anamnesisChecksValue2's anamnesisCheck2-----------------
                assertEquals 2, anamnesisCheck2.sortOrder;
                assertEquals "Rauchen Sie?", anamnesisCheck2.text;
                assertEquals anamnesisChecksTitle2, anamnesisCheck2.anamnesisCheckTitle;
                assertEquals 2, anamnesisCheck2.type;
                assertEquals null, anamnesisCheck2.userSpecifiedOrder;
                assertEquals "oft|mittel|selten", anamnesisCheck2.value;



                //-------------verify anamnesisChecksValue2's anamnesisCheck2's title-----------------
                assertEquals 1, anamnesisChecksTitle2.sortOrder;
                assertEquals "Personal lifestyle category", anamnesisChecksTitle2.text;


                //---------------verify anamnesisCheckValue3------------------
                assertEquals "this is a comment 1", anamnesisChecksValue3.comment;
                assertEquals Boolean.TRUE, anamnesisChecksValue3.truth ;
                assertEquals "1",anamnesisChecksValue3.anamnesisChecksValue;
                assertEquals anamnesisCheck3, anamnesisChecksValue3.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue3.anamnesisForm;

                //-------------verify anamnesisChecksValue3's anamnesisCheck3-----------------
                assertEquals 2, anamnesisCheck3.sortOrder;
                assertEquals "Rauchen Sie 222?", anamnesisCheck3.text;
                assertEquals anamnesisChecksTitle3, anamnesisCheck3.anamnesisCheckTitle;
                assertEquals 0, anamnesisCheck3.type;
                assertEquals null, anamnesisCheck3.userSpecifiedOrder;
                assertEquals "JDJDJDDJDJDJ", anamnesisCheck3.value;


                //-------------verify anamnesisChecksValue3's anamnesisCheck3's title-----------------
                assertEquals 1, anamnesisChecksTitle3.sortOrder;
                assertEquals "Personal lifestyle category", anamnesisChecksTitle3.text;



                //-------------verify scar-----------------
                assertEquals 1, scar.traitType;
                assertEquals "Oberschenkel (links)", scar.bodypart;

   }


    void testExportSP(){

        def controller = new DataImportExportController()
        Role role1 = new Role();
        role1.roleName= "USER_ROLE";
        role1.save();

        def params = controller.params;
        def response = controller.response;

        params.data = getTestData2();
        controller.importSP();

        params.id=5711;

        response.reset()

        controller.exportSP();


        def jsonObject = JSON.parse(response.contentAsString);



        assertEquals 5711, jsonObject.origId ;

        assertEquals 3, jsonObject.anamnesisForm.origId ;

        assertEquals 3, jsonObject.anamnesisForm.anamnesisChecksValues.size()

        assertNotNull jsonObject.anamnesisForm.anamnesisChecksValues[0].anamnesisForm

		assertEquals "2010-07-18",jsonObject.birthday;
        
   }
   
    /**
	 * Test the json data that the birthday is String type data;
     */
    void testImportSP3(){

         def controller = new DataImportExportController()
                Role role1 = new Role();
                role1.roleName= "USER_ROLE";
                role1.save();

                def params = controller.params;
                def response = controller.response;

                params.data = getTestData3();

                def model = controller.importSP()

                def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);

                def list = local.StandardizedPatient.list();

                def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
                def scar = local.Scar.findByOrigId(9);

                def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
                def anamnesisCheck1 = local.AnamnesisCheck.findByOrigId(3);
                def anamnesisChecksTitle1 = anamnesisCheck1.anamnesisCheckTitle

                def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
                def anamnesisCheck2 = local.AnamnesisCheck.findByOrigId(1);
                def anamnesisChecksTitle2 = anamnesisCheck2.anamnesisCheckTitle

                def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
                def anamnesisCheck3 = local.AnamnesisCheck.findByOrigId(2);
                def anamnesisChecksTitle3 = anamnesisCheck3.anamnesisCheckTitle

                def bankaccount = local.Bankaccount.findByOrigId(51);

                def description = local.Description.findByOrigId(35);

                def nationality = local.Nationality.findByOrigId(7);

                def profession=local.Profession.findByOrigId(3);

                assert list.size == 1;

    //            assert response.text == "qqq@rrr.com";

                //-------------verify standardizedPatient-----------------
                assertEquals "qqq@rrr.com", standardizedPatient.email;
                assertEquals standardizedPatient.birthday.toString(),"2012-07-08";
                assertEquals 1,standardizedPatient.gender
                assertEquals 162,standardizedPatient.height
                assertEquals "/image/path",standardizedPatient.immagePath
                assertEquals 3,standardizedPatient.maritalStatus
                assertEquals "078 427 24 85",standardizedPatient.mobile
                assertEquals "Lamarie",standardizedPatient.name
                assertEquals 5711,standardizedPatient.origId
                assertEquals 4057,standardizedPatient.postalCode
                assertEquals "Marianne",standardizedPatient.preName
                assertEquals "1234567891234",standardizedPatient.socialInsuranceNo
                assertEquals "Feldbergstrasse 145",standardizedPatient.street
                assertEquals "123456789",standardizedPatient.telephone
                assertEquals ";kjlkjlj",standardizedPatient.telephone2
                assertEquals 57,standardizedPatient.weight
                assertEquals null,standardizedPatient.workPermission
                assertEquals "Metz",standardizedPatient.city.toString()

                assertEquals anamnesisForm,standardizedPatient.anamnesisForm
                assertEquals bankaccount,standardizedPatient.bankaccount
                assertNotNull standardizedPatient.description
				
                assertEquals "this is a description", standardizedPatient?.description?.description
                assertEquals 35, standardizedPatient?.description?.origId
                assertEquals nationality,standardizedPatient.nationality
                assertEquals profession,standardizedPatient.profession

                assertNotNull anamnesisForm
                //-------------verify bankaccount-----------------
                assertEquals "Basler Kantonalbank", bankaccount.bankName;
                assertEquals "GENODEF1JEV", bankaccount.bic;
                assertEquals "SHanghai", bankaccount.city;
                assertEquals "CH46 3948 4853 2029 3", bankaccount.iban;
                assertEquals "sqq", bankaccount.ownerName;
                assertEquals 241000, bankaccount.postalCode;

                //-------------verify nationality-----------------
                assertEquals "Frankreich", nationality.nationality;

                //-------------verify profession-----------------
                assertEquals "Bauarbeiter/in", profession.profession;

                //-------------verify anamnesisForm-----------------
                assertEquals new Date(1279382400000), anamnesisForm.createDate;

                //-------------verify anamnesisChecksValue1-----------------
                assertEquals "this is a comment 3", anamnesisChecksValue1.comment;
                assertEquals Boolean.FALSE, anamnesisChecksValue1.truth;
                assertEquals null, anamnesisChecksValue1.anamnesisChecksValue;
                assertEquals anamnesisCheck1, anamnesisChecksValue1.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue1.anamnesisForm;

                //-------------verify anamnesisChecksValue1's anamnesisCheck1-----------------
                assertEquals 10, anamnesisCheck1.sortOrder;
                assertEquals "Nehmen Sie zurzeit regelmässig Medikamente ein?", anamnesisCheck1.text;
                assertEquals 1, anamnesisCheck1.type;
                assertEquals null, anamnesisCheck1.userSpecifiedOrder;
                assertEquals "", anamnesisCheck1.value;


                //-------------verify anamnesisChecksValue1's anamnesisCheck1's title-----------------
                assertEquals 9, anamnesisChecksTitle1.sortOrder;
                assertEquals "Treatment history category", anamnesisChecksTitle1.text;

                //--------------verify anamnesisCheckVlaue2------------------
                assertEquals "this is a comment 2", anamnesisChecksValue2.comment;
                assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
                assertEquals "1-0-0", anamnesisChecksValue2.anamnesisChecksValue;
                assertEquals anamnesisCheck2, anamnesisChecksValue2.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue2.anamnesisForm;

                //-------------verify anamnesisChecksValue2's anamnesisCheck2-----------------
                assertEquals 2, anamnesisCheck2.sortOrder;
                assertEquals "Rauchen Sie?", anamnesisCheck2.text;
                assertEquals anamnesisChecksTitle2, anamnesisCheck2.anamnesisCheckTitle;
                assertEquals 2, anamnesisCheck2.type;
                assertEquals null, anamnesisCheck2.userSpecifiedOrder;
                assertEquals "oft|mittel|selten", anamnesisCheck2.value;



                //-------------verify anamnesisChecksValue2's anamnesisCheck2's title-----------------
                assertEquals 1, anamnesisChecksTitle2.sortOrder;
                assertEquals "Personal lifestyle category", anamnesisChecksTitle2.text;


                //---------------verify anamnesisCheckValue3------------------
                assertEquals "this is a comment 1", anamnesisChecksValue3.comment;
                assertEquals Boolean.TRUE, anamnesisChecksValue3.truth ;
                assertEquals "1",anamnesisChecksValue3.anamnesisChecksValue;
                assertEquals anamnesisCheck3, anamnesisChecksValue3.anamnesisCheck;
                assertEquals anamnesisForm, anamnesisChecksValue3.anamnesisForm;

                //-------------verify anamnesisChecksValue3's anamnesisCheck3-----------------
                assertEquals 2, anamnesisCheck3.sortOrder;
                assertEquals "Rauchen Sie 222?", anamnesisCheck3.text;
                assertEquals anamnesisChecksTitle3, anamnesisCheck3.anamnesisCheckTitle;
                assertEquals 0, anamnesisCheck3.type;
                assertEquals null, anamnesisCheck3.userSpecifiedOrder;
                assertEquals "JDJDJDDJDJDJ", anamnesisCheck3.value;


                //-------------verify anamnesisChecksValue3's anamnesisCheck3's title-----------------
                assertEquals 1, anamnesisChecksTitle3.sortOrder;
                assertEquals "Personal lifestyle category", anamnesisChecksTitle3.text;



                //-------------verify scar-----------------
                assertEquals 1, scar.traitType;
                assertEquals "Oberschenkel (links)", scar.bodypart;

        
   }
	
	 void testImportErrorFieldJsonData(){

                def controller = new DataImportExportController()
                Role role1 = new Role();
                role1.roleName= "USER_ROLE";
                role1.save();

                def params = controller.params;
                def response = controller.response;

				// test the locale is en
                params.data = getTestErrorFieldData("en");
				

                def model = controller.importSP()
				
				def jsonObject = JSON.parse(response.text);
								
				assertTrue jsonObject.errors.error.size() == 4
				
				def expectedError1 = "Property [city] of class [class sp_portal.local.StandardizedPatient] with value [Metzkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk] exceeds the maximum size of [30]";
				def expectedError2 = "Property [email] of class [class sp_portal.local.StandardizedPatient] with value [qqqrrr.com] is not a valid e-mail address";
				def expectedError3 = "Property [socialInsuranceNo] of class [class sp_portal.local.StandardizedPatient] with value [12345678911234] exceeds the maximum size of [13]";
				def expectedError4 = "Property [socialInsuranceNo] of class [class sp_portal.local.StandardizedPatient] with value [12345678911234] does not match the required pattern [[0-9]{13,13}]";

				assertEquals jsonObject.errors.error[0],expectedError1;
				assertEquals jsonObject.errors.error[1],expectedError2;
				assertEquals jsonObject.errors.error[2],expectedError3;
				assertEquals jsonObject.errors.error[3],expectedError4;

				//controller.response.contentAsString
				

   }
   
   void testImportErrorFieldJsonDataWithDe(){
				def controller = new DataImportExportController()
                Role role1 = new Role();
                role1.roleName= "USER_ROLE";
                role1.save();

                def params = controller.params;
                def response = controller.response;

				// test the locale is en
                params.data = getTestErrorFieldData("de");
				

                def model = controller.importSP()
				
				def jsonObject = JSON.parse(response.text);
								
				assertTrue jsonObject.errors.error.size() == 4
				
				def expectedError0 = "Eigenschaft [city] der Klasse [class sp_portal.local.StandardizedPatient] mit dem Wert [Metzkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk] überschreitet die maximale Größe von [30]";
				def expectedError1 = "Eigenschaft [email] der Klasse [class sp_portal.local.StandardizedPatient] mit dem Wert [qqqrrr.com] ist keine gültige E-Mail-Adresse";
				def expectedError2 = "Eigenschaft [socialInsuranceNo] der Klasse [class sp_portal.local.StandardizedPatient] mit dem Wert [12345678911234] überschreitet die maximale Größe von [13]";
				def expectedError3 = "Eigenschaft [socialInsuranceNo] der Klasse [class sp_portal.local.StandardizedPatient] mit dem Wert [12345678911234] entspricht nicht dem erforderlichen Muster [[0-9]{13,13}]";

				assertEquals jsonObject.errors.error[0],expectedError0;
				assertEquals jsonObject.errors.error[1],expectedError1;
				assertEquals jsonObject.errors.error[2],expectedError2;
				assertEquals jsonObject.errors.error[3],expectedError3;
   }

    
	
	
    private String getTestData1(){
        def ret = $/
           {
				"StandardizedPatient":{
                       "class":"ch.unibas.medizin.osce.domain.StandardizedPatient",
                       "cats":"dogs", // this is an unexpected field 
                       "anamnesisForm":{
                          "class":"ch.unibas.medizin.osce.domain.AnamnesisForm",
                          "anamnesisChecksValues":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                                "anamnesisCheck":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                   "id":3,
                                   "sortOrder":11,
                                   "text":"Nehmen Sie zurzeit regelmässig Medikamente ein?",

                                    "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":1,
                                      "sort_order":9,
                                      "text":"Treatment history category",
                                      "version":0
                                   },
                                   "type":"QUESTION_YES_NO",
                                   "userSpecifiedOrder":null,
                                   "value":""
                                },
                                "anamnesisChecksValue":null,
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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

                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":2,
                                      "sort_order":2,
                                      "text":"Personal lifestyle category",
                                      "version":0
                                   },
                                   "type":"QUESTION_MULT_S",
                                   "userSpecifiedOrder":null,
                                   "value":"oft|mittel|selten"
                                },
                                "anamnesisChecksValue":"1-0-0",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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
                                    "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":3,
                                      "sort_order":3,
                                      "text":"Personal lifestyle category",
                                      "version":0
                                   },
                                   "type":"QUESTION_OPEN",
                                   "userSpecifiedOrder":null,
                                   "value":"JDJDJDDJDJDJ"
                                },
                                "anamnesisChecksValue":"1",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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
                       "description":null,
                       "email":"qqq@rrr.com",
                       "gender":"FEMALE",
                       "height":162,
                       "immagePath":null,
                       "maritalStatus":null,
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
                       "socialInsuranceNo":"1234567891234",
                       "street":"Feldbergstrasse 145",
                       "telephone":null,
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    },
					"languages" :{"language": "en"}}
                /$
            return ret.toString();

    }

    private String getTestData2(){
        def ret = $/
               {
				"StandardizedPatient": {
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

                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":12,
                                      "sort_order":9,
                                      "text":"Treatment history category",
                                      "version":0
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
                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":101,
                                      "sort_order":1,
                                      "text":"Personal lifestyle category",
                                      "version":0
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
                                   "cats":"dogs", // this is an unexpected field 
                                   "id":2,
                                   "sortOrder":2,
                                   "text":"Rauchen Sie 222?",
                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":100,
                                      "sort_order":1,
                                      "text":"Personal lifestyle category",
                                      "version":0
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
                       "socialInsuranceNo":"1234567891234",
                       "street":"Feldbergstrasse 145",
                       "telephone":"123456789",
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    },
					"languages" :{"language": "en"}}
                /$
            return ret.toString();

    }
    private String getTestData3(){
        def ret = $/
                {"StandardizedPatient":{
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

                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":12,
                                      "sort_order":9,
                                      "text":"Treatment history category",
                                      "version":0
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
                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":101,
                                      "sort_order":1,
                                      "text":"Personal lifestyle category",
                                      "version":0
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
                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":100,
                                      "sort_order":1,
                                      "text":"Personal lifestyle category",
                                      "version":0
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
                       "birthday":"2012-07-08",
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
                       "socialInsuranceNo":"1234567891234",
                       "street":"Feldbergstrasse 145",
                       "telephone":"123456789",
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    },
					"languages" :{"language": "en"}}
                /$
            return ret.toString();

    }



	
	private String getTestErrorFieldData(String locale){
	def ret = $/{    
         "StandardizedPatient":{
                       "class":"ch.unibas.medizin.osce.domain.StandardizedPatient",
                       "cats":"dogs", 
                       "anamnesisForm":{
                          "class":"ch.unibas.medizin.osce.domain.AnamnesisForm",
                          "anamnesisChecksValues":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue",
                                "anamnesisCheck":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisCheck",
                                   "id":3,
                                   "sortOrder":11,
                                   "text":"Nehmen Sie zurzeit regelmässig Medikamente ein?",

                                    "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":1,
                                      "sort_order":9,
                                      "text":"Treatment history category",
                                      "version":0
                                   },
                                   "type":"QUESTION_YES_NO",
                                   "userSpecifiedOrder":null,
                                   "value":""
                                },
                                "anamnesisChecksValue":null,
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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

                                   "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":2,
                                      "sort_order":2,
                                      "text":"Personal lifestyle category",
                                      "version":0
                                   },
                                   "type":"QUESTION_MULT_S",
                                   "userSpecifiedOrder":null,
                                   "value":"oft|mittel|selten"
                                },
                                "anamnesisChecksValue":"1-0-0",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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
                                    "anamnesisCheckTitle":{
                                      "class":"ch.unibas.medizin.osce.domain.AnamnesisCheckTitle",
                                      "id":3,
                                      "sort_order":3,
                                      "text":"Personal lifestyle category",
                                      "version":0
                                   },
                                   "type":"QUESTION_OPEN",
                                   "userSpecifiedOrder":null,
                                   "value":"JDJDJDDJDJDJ"
                                },
                                "anamnesisChecksValue":"1",
                                "anamnesisForm":{
                                   "class":"ch.unibas.medizin.osce.domain.AnamnesisForm"
                                },
                                "comment":null,
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
                          "bankName":"Baslerl bank",
                          "bic":"GENODEF1JEV",
                          "city":"SHanghai",
                          "iban":"CH46 3948 4853 2029 3",
                          "id":12,
                          "ownerName":"sqq",
                          "postalCode":1234,
                          "standardizedPatients":[
                             {
                                "class":"ch.unibas.medizin.osce.domain.StandardizedPatient"
                             }
                          ]
                       },
                       "birthday":"1990-07-08",
                       "city":"Metzkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
                       "description":null,
                       "email":"qqqrrr.com",
                       "gender":"FEMALE",
                       "height":162,
                       "immagePath":null,
                       "maritalStatus":null,
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
                       "socialInsuranceNo":"12345678911234",
                       "street":"Feldbergstrasse 145",
                       "telephone":null,
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    },
			"languages" :{"language": "de"}}
		/$
		if(locale){
			ret = ret.replaceAll("\"language\": \"de\"","\"language\": \""+locale+"\"");
		}
		return ret;
	}




}
