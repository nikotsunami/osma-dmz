package sp_portal

import static org.junit.Assert.*
import org.junit.*
import grails.test.mixin.*

@TestFor(DataImportExportController)
class DataImportExportControllerTests extends GroovyTestCase{

	void testImportSP(){
		
					def controller = new DataImportExportController()
				Role role1 = new Role();
				role1.roleName= "USER_ROLE";
				role1.save();
					
					params.data = '{"class":"ch.unibas.medizin.osce.domain.StandardizedPatient","anamnesisForm":{"class":"ch.unibas.medizin.osce.domain.AnamnesisForm","anamnesisChecksValues":[{"class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue","anamnesisCheck":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":3,"sortOrder":10,"text":"Nehmen Sie zurzeit regelmässig Medikamente ein?","title":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":12,"sortOrder":9,"text":"Treatment history category","title":null,"type":"QUESTION_TITLE","userSpecifiedOrder":null,"value":""},"type":"QUESTION_YES_NO","userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"class":"ch.unibas.medizin.osce.domain.AnamnesisForm"},"comment":null,"id":9,"truth":false},{"class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue","anamnesisCheck":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie?","title":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":101,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":"QUESTION_TITLE","userSpecifiedOrder":null,"value":""},"type":"QUESTION_YES_NO","userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"class":"ch.unibas.medizin.osce.domain.AnamnesisForm"},"comment":null,"id":8,"truth":true},{"class":"ch.unibas.medizin.osce.domain.AnamnesisChecksValue","anamnesisCheck":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie 222?","title":{"class":"ch.unibas.medizin.osce.domain.AnamnesisCheck","id":101,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":"QUESTION_TITLE","userSpecifiedOrder":null,"value":""},"type":"QUESTION_YES_NO","userSpecifiedOrder":null,"value":"JDJDJDDJDJDJ"},"anamnesisChecksValue":null,"anamnesisForm":{"class":"ch.unibas.medizin.osce.domain.AnamnesisForm"},"comment":null,"id":80,"truth":true}],"createDate":new Date(1279382400000),"id":3,"scars":[],"standardizedPatients":[{"class":"ch.unibas.medizin.osce.domain.StandardizedPatient"}]},"bankaccount":{"class":"ch.unibas.medizin.osce.domain.Bankaccount","bankName":"Basler Kantonalbank","bic":"GENODEF1JEV","city":"SHanghai","iban":"CH46 3948 4853 2029 3","id":51,"ownerName":null,"postalCode":null,"standardizedPatients":[{"class":"ch.unibas.medizin.osce.domain.StandardizedPatient"}]},"birthday":new Date(1279382400000),"city":"Metz","description":null,"email":"qqq@rrr.com","gender":"FEMALE","height":162,"immagePath":null,"maritalStatus":null,"mobile":"078 427 24 85","name":"Lamarie","nationality":{"class":"ch.unibas.medizin.osce.domain.Nationality","nationality":"Frankreich","id":7},"id":5711,"postalCode":4057,"preName":"Marianne","profession":{"class":"ch.unibas.medizin.osce.domain.Profession","id":3,"profession":"Bauarbeiter/in"},"socialInsuranceNo":"qqq","street":"Feldbergstrasse 145","telephone":null,"telephone2":";kjlkjlj","videoPath":"hello nick","weight":57,"workPermission":null}'
				
					def model = controller.importSP()
					
					def standardizedPatient = local.StandardizedPatient.findByOrigId(5711);
					
					def list = local.StandardizedPatient.list();
					
					def anamnesisForm = local.AnamnesisForm.findByOrigId(3);
					
					def anamnesisChecksValue1 = local.AnamnesisChecksValue.findByOrigId(9);
					def anamnesisChecksValue2 = local.AnamnesisChecksValue.findByOrigId(8);
					def anamnesisChecksValue3 = local.AnamnesisChecksValue.findByOrigId(80);
					
					def bankaccount = local.Bankaccount.findByOrigId(51);
					
					def description = local.Description.findByOrigId(null);
					
					def nationality = local.Nationality.findByOrigId(7);
					
					def profession=local.Profession.findByOrigId(3);
						
					assert list.size == 1;
				
					assert response.text == "qqq@rrr.com";
					
					//-------------judge standardizedPatient-----------------		
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
					
					//-------------judge bankaccount-----------------		
					assertEquals "Basler Kantonalbank", bankaccount.bankName;
					assertEquals "GENODEF1JEV", bankaccount.bic;
					assertEquals "SHanghai", bankaccount.city;
					assertEquals "CH46 3948 4853 2029 3", bankaccount.iban;
					assertEquals null, bankaccount.ownerName;
					assertEquals null, bankaccount.postalCode;
							
					//-------------judge nationality-----------------
					assertEquals "Frankreich", nationality.nationality;
					
					//-------------judge profession-----------------
					assertEquals "Bauarbeiter/in", profession.profession;
					
					//-------------judge anamnesisForm-----------------
					assertEquals new Date(1279382400000), anamnesisForm.createDate;
					
					//-------------judge anamnesisChecksValue1-----------------
					assertEquals null, anamnesisChecksValue1.anamnesisChecksValue;
					assertEquals null, anamnesisChecksValue1.comment;
					assertEquals Boolean.FALSE, anamnesisChecksValue1.truth;
					
					//--------------judge anamnesisCheckVlaue1------------------
					assertEquals null, anamnesisChecksValue2.anamnesisChecksValue;
					assertEquals null, anamnesisChecksValue2.comment;
					assertEquals Boolean.TRUE, anamnesisChecksValue2.truth;
					
					//---------------judge anamnesisCheckValue3------------------
					assertEquals null, anamnesisChecksValue3.anamnesisChecksValue;
					assertEquals null, anamnesisChecksValue3.comment;
					assertEquals Boolean.TRUE anamnesisChecksValue3.truth ;
					
					
					
					
					   		
   }

}
