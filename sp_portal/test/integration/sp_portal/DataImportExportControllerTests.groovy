package sp_portal

import static org.junit.Assert.*
import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.web.json.*;
import grails.converters.deep.JSON

@TestFor(DataImportExportController)
class DataImportExportControllerTests extends GroovyTestCase{

 /*   public void setUp(){
    
        println("In SETUP");
    }

*/
	void __testImportSP(){
         
	
				//def controller = new DataImportExportController()
				Role role1 = new Role();
				role1.roleName= "USER_ROLE";
				role1.save();
					

                params.data = getTestData1();
                
                println("data ---> " + params.data);
				
					def model = controller.importSP()
					
					def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);
					
					def list = local.StandardizedPatient.list();
					
					def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
                    def scar = local.Scar.findByOrigId(9);
					
					def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
                    def anamnesisCheck1 = local.AnamnesisCheck.findByOrigId(3);
					def anamnesisChecksTitle1 = local.AnamnesisCheck.findByOrigId(12);
                    
                    def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
					def anamnesisCheck2 = local.AnamnesisCheck.findByOrigId(1);
					def anamnesisChecksTitle2 = local.AnamnesisCheck.findByOrigId(101);
                    
                    def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
					def anamnesisCheck3 = local.AnamnesisCheck.findByOrigId(2);
					def anamnesisChecksTitle3 = local.AnamnesisCheck.findByOrigId(100);
                    
					def bankaccount = local.Bankaccount.findByOrigId(51);
					
					def description = local.Description.findByOrigId(null);
					
					def nationality = local.Nationality.findByOrigId(7);
					
					def profession=local.Profession.findByOrigId(3);
						
					assert list.size == 1;
				
					assert response.text == "qqq@rrr.com";
					
					//-------------verify standardizedPatient-----------------		
					assertEquals "qqq@rrr.com", standardizedPatient.email;
					assert standardizedPatient.birthday == new Date(1279382400000);
					assertEquals 1,standardizedPatient.gender
					assertEquals 162,standardizedPatient.height
					assertEquals null,standardizedPatient.immagePath
					assertEquals null,standardizedPatient.maritalStatus
					assertEquals "078 427 24 85",standardizedPatient.mobile
					assertEquals "Lamarie",standardizedPatient.name
					assertEquals 5711,standardizedPatient.origId
					assertEquals 4057,standardizedPatient.postalCode
					assertEquals "Marianne",standardizedPatient.preName
					assertEquals "qqq",standardizedPatient.socialInsuranceNo
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
                    assertEquals 10, anamnesisCheck1.sortOrder;
                    assertEquals "Nehmen Sie zurzeit regelmässig Medikamente ein?", anamnesisCheck1.text;
                    assertEquals anamnesisChecksTitle1, anamnesisCheck1.title;
                    assertEquals 1, anamnesisCheck1.type;
                    assertEquals null, anamnesisCheck1.userSpecifiedOrder;
                    assertEquals "", anamnesisCheck1.value;
                    
                                                          
                    //-------------verify anamnesisChecksValue1's anamnesisCheck1's title-----------------
                    assertEquals 9, anamnesisChecksTitle1.sortOrder;
                    assertEquals "Treatment history category", anamnesisChecksTitle1.text;
                    assertEquals null, anamnesisChecksTitle1.title;
                    assertEquals 4, anamnesisChecksTitle1.type;
                    assertEquals null, anamnesisChecksTitle1.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle1.value;
                    
					//--------------verify anamnesisCheckVlaue2------------------
					assertEquals null, anamnesisChecksValue2.comment;
					assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
                    assertEquals "1-0-0", anamnesisChecksValue2.anamnesisChecksValue;
                    assertEquals anamnesisCheck2, anamnesisChecksValue2.anamnesisCheck;
                    assertEquals anamnesisForm, anamnesisChecksValue2.anamnesisForm;
                    
                    //-------------verify anamnesisChecksValue2's anamnesisCheck2-----------------
                    assertEquals 2, anamnesisCheck2.sortOrder;
                    assertEquals "Rauchen Sie?", anamnesisCheck2.text;
                    assertEquals anamnesisChecksTitle2, anamnesisCheck2.title;
                    assertEquals 2, anamnesisCheck2.type;
                    assertEquals null, anamnesisCheck2.userSpecifiedOrder;
                    assertEquals "oft|mittel|selten", anamnesisCheck2.value;
                    
                                                          
                    //-------------verify anamnesisChecksValue2's anamnesisCheck2's title-----------------
                    assertEquals 1, anamnesisChecksTitle2.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle2.text;
                    assertEquals null, anamnesisChecksTitle2.title;
                    assertEquals 4, anamnesisChecksTitle2.type;
                    assertEquals null, anamnesisChecksTitle2.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle2.value;
                    
					//---------------verify anamnesisCheckValue3------------------
					assertEquals null, anamnesisChecksValue3.comment;
					assertEquals Boolean.TRUE, anamnesisChecksValue3.truth ;
                    assertEquals "1",anamnesisChecksValue3.anamnesisChecksValue;
					assertEquals anamnesisCheck3, anamnesisChecksValue3.anamnesisCheck;
					assertEquals anamnesisForm, anamnesisChecksValue3.anamnesisForm;
					
                    //-------------verify anamnesisChecksValue3's anamnesisCheck3-----------------
                    assertEquals 2, anamnesisCheck3.sortOrder;
                    assertEquals "Rauchen Sie 222?", anamnesisCheck3.text;
                    assertEquals anamnesisChecksTitle3, anamnesisCheck3.title;
                    assertEquals 0, anamnesisCheck3.type;
                    assertEquals null, anamnesisCheck3.userSpecifiedOrder;
                    assertEquals "JDJDJDDJDJDJ", anamnesisCheck3.value;
                    
                                                          
                    //-------------verify anamnesisChecksValue3's anamnesisCheck3's title-----------------
                    assertEquals 1, anamnesisChecksTitle3.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle3.text;
                    assertEquals null, anamnesisChecksTitle3.title;
                    assertEquals 4, anamnesisChecksTitle3.type;
                    assertEquals null, anamnesisChecksTitle3.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle3.value;
                    
                    
                    //-------------verify scar-----------------
                    assertEquals 1, scar.traitType;
                    assertEquals "Oberschenkel (links)", scar.bodypart;
                    
                  
                    
   }


    void __testImportSP2(){
		
			//	def controller = new DataImportExportController()
				Role role1 = new Role();
				role1.roleName= "USER_ROLE";
				role1.save();
					

                params.data = getTestData2();
                
                println("data ---> " + params.data);
				
					def model = controller.importSP()
					
					def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);
					
					def list = local.StandardizedPatient.list();
					
					def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
                    def scar = local.Scar.findByOrigId(9);
					
					def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
                    def anamnesisCheck1 = local.AnamnesisCheck.findByOrigId(3);
					def anamnesisChecksTitle1 = local.AnamnesisCheck.findByOrigId(12);
                    
                    def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
					def anamnesisCheck2 = local.AnamnesisCheck.findByOrigId(1);
					def anamnesisChecksTitle2 = local.AnamnesisCheck.findByOrigId(101);
                    
                    def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
					def anamnesisCheck3 = local.AnamnesisCheck.findByOrigId(2);
					def anamnesisChecksTitle3 = local.AnamnesisCheck.findByOrigId(100);
                    
					def bankaccount = local.Bankaccount.findByOrigId(51);
					
					def description = local.Description.findByOrigId(35);
					
					def nationality = local.Nationality.findByOrigId(7);
					
					def profession=local.Profession.findByOrigId(3);
						
					assert list.size == 1;
				
					assert response.text == "qqq@rrr.com";
					
					//-------------verify standardizedPatient-----------------		
					assertEquals "qqq@rrr.com", standardizedPatient.email;
					assert standardizedPatient.birthday == new Date(1279382400000);
					assertEquals 1,standardizedPatient.gender
					assertEquals 162,standardizedPatient.height
					assertEquals "/image/path",standardizedPatient.immagePath
					assertEquals 3,standardizedPatient.maritalStatus
					assertEquals "078 427 24 85",standardizedPatient.mobile
					assertEquals "Lamarie",standardizedPatient.name
					assertEquals 5711,standardizedPatient.origId
					assertEquals 4057,standardizedPatient.postalCode
					assertEquals "Marianne",standardizedPatient.preName
					assertEquals "qqq",standardizedPatient.socialInsuranceNo
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
                    assertEquals anamnesisChecksTitle1, anamnesisCheck1.title;
                    assertEquals 1, anamnesisCheck1.type;
                    assertEquals null, anamnesisCheck1.userSpecifiedOrder;
                    assertEquals "", anamnesisCheck1.value;
                    
                                                          
                    //-------------verify anamnesisChecksValue1's anamnesisCheck1's title-----------------
                    assertEquals 9, anamnesisChecksTitle1.sortOrder;
                    assertEquals "Treatment history category", anamnesisChecksTitle1.text;
                    assertEquals null, anamnesisChecksTitle1.title;
                    assertEquals 4, anamnesisChecksTitle1.type;
                    assertEquals null, anamnesisChecksTitle1.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle1.value;
                    
					//--------------verify anamnesisCheckVlaue2------------------
					assertEquals "this is a comment 2", anamnesisChecksValue2.comment;
					assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
                    assertEquals "1-0-0", anamnesisChecksValue2.anamnesisChecksValue;
                    assertEquals anamnesisCheck2, anamnesisChecksValue2.anamnesisCheck;
                    assertEquals anamnesisForm, anamnesisChecksValue2.anamnesisForm;
                    
                    //-------------verify anamnesisChecksValue2's anamnesisCheck2-----------------
                    assertEquals 2, anamnesisCheck2.sortOrder;
                    assertEquals "Rauchen Sie?", anamnesisCheck2.text;
                    assertEquals anamnesisChecksTitle2, anamnesisCheck2.title;
                    assertEquals 2, anamnesisCheck2.type;
                    assertEquals null, anamnesisCheck2.userSpecifiedOrder;
                    assertEquals "oft|mittel|selten", anamnesisCheck2.value;
          
          
                                                          
                    //-------------verify anamnesisChecksValue2's anamnesisCheck2's title-----------------
                    assertEquals 1, anamnesisChecksTitle2.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle2.text;
                    assertEquals null, anamnesisChecksTitle2.title;
                    assertEquals 4, anamnesisChecksTitle2.type;
                    assertEquals null, anamnesisChecksTitle2.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle2.value;
                    
					//---------------verify anamnesisCheckValue3------------------
					assertEquals "this is a comment 1", anamnesisChecksValue3.comment;
					assertEquals Boolean.TRUE, anamnesisChecksValue3.truth ;
                    assertEquals "1",anamnesisChecksValue3.anamnesisChecksValue;
					assertEquals anamnesisCheck3, anamnesisChecksValue3.anamnesisCheck;
					assertEquals anamnesisForm, anamnesisChecksValue3.anamnesisForm;
					
                    //-------------verify anamnesisChecksValue3's anamnesisCheck3-----------------
                    assertEquals 2, anamnesisCheck3.sortOrder;
                    assertEquals "Rauchen Sie 222?", anamnesisCheck3.text;
                    assertEquals anamnesisChecksTitle3, anamnesisCheck3.title;
                    assertEquals 0, anamnesisCheck3.type;
                    assertEquals null, anamnesisCheck3.userSpecifiedOrder;
                    assertEquals "JDJDJDDJDJDJ", anamnesisCheck3.value;
                    
                                                          
                    //-------------verify anamnesisChecksValue3's anamnesisCheck3's title-----------------
                    assertEquals 1, anamnesisChecksTitle3.sortOrder;
                    assertEquals "Personal lifestyle category", anamnesisChecksTitle3.text;
                    assertEquals null, anamnesisChecksTitle3.title;
                    assertEquals 4, anamnesisChecksTitle3.type;
                    assertEquals null, anamnesisChecksTitle3.userSpecifiedOrder;
                    assertEquals "", anamnesisChecksTitle3.value;
                    
                    
                    //-------------verify scar-----------------
                    assertEquals 1, scar.traitType;
                    assertEquals "Oberschenkel (links)", scar.bodypart;
                                        
   }
   
   
   void testExportSP(){
 // HERE  
      //  def controller = new DataImportExportController()
        Role role1 = new Role();
        role1.roleName= "USER_ROLE";
        role1.save();
        
        params.data = getTestData2();
		controller.importSP();

		params.id=5711;
		
		response.reset()
		
        controller.exportSP();
	println(response.text);
	
	
	
		def jsonObject = JSON.parse(response.contentAsString);
		
   	println(jsonObject);
		
		assertEquals 5711, jsonObject.origId ;
		
		assertEquals 3, jsonObject.anamnesisForm.origId ;
		
		assertEquals 3, jsonObject.anamnesisForm.anamnesisChecksValues.size()
		
		assertNotNull jsonObject.anamnesisForm.anamnesisChecksValues[0].anamnesisForm

		
	    //assertEquals getTestData2(), response.contentAsString;
   }


    private String getTestData1(){
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
                       "socialInsuranceNo":"qqq",
                       "street":"Feldbergstrasse 145",
                       "telephone":null,
                       "telephone2":";kjlkjlj",
                       "videoPath":"hello nick",
                       "weight":57,
                       "workPermission":null
                    }
                /$
            return ret.toString();  
    
    }
    
     private String getTestData2(){
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
