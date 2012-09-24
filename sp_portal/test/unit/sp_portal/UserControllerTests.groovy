package sp_portal

import org.junit.*
import grails.test.mixin.*
import sp_portal.*;
import sp_portal.local.*;
import sp_portal.remote.*;

@TestFor(UserController)
@Mock([User,Role,remote.Scar,remote.AnamnesisCheck,local.Scar,local.AnamnesisCheck,remote.StandardizedPatient,local.StandardizedPatient,
local.AnamnesisCheckTitle,remote.AnamnesisCheckTitle,remote.AnamnesisForm,local.AnamnesisForm,remote.Bankaccount,local.Bankaccount,
remote.AnamnesisChecksValue,local.AnamnesisChecksValue,remote.Description,remote.LangSkill,remote.Nationality,OsceDay,
remote.Profession,remote.SpokenLanguage,remote.StandardizedPatient,local.Description,Emails,local.LangSkill,local.Nationality,
PatientlnSemester,local.Profession,local.SpokenLanguage,local.StandardizedPatient,Training])
class UserControllerTests{
    
	def dataSetUp = null
	
	@Before
	void setUp(){
       dataSetUp = new RemoteDataSetupHelper()
	   dataSetUp.getDataSetA()
	   //dataSetUp.getDataSetB()
	} 
	
	@After
	void tearDown(){
	   dataSetUp = null
	}
	
	def populateValidParams(params){
	  assert params != null
	  params.passwordHash = "123"
	  params.confirmPassword = "123"
	  params.userName = "abc"
	  params.userEmail = "abc@jserver.cn"
	  params.isActive = true
    } 
	
	void testIndex(){
	   def model = controller.index()
	   assert response.redirectUrl == "/user/welcome"
	}
	
	void testList(){
	   def model = controller.list()
	   assert model.userInstanceList.size() == 2
	   assert model.userInstanceTotal == 2
	   //assert model.userInstanceList[0].userName == "NormalUser"
	}
	
	void testCreate(){
	   def model = controller.create()
	   assert model.userInstance.isActive == true
	   assert model.userInstance != null
	}
	
	void testCreateUserMessage(){
	   def list = []
	   def username = "abc"
	   def password = "123"
	   controller.createUserMessage(list,username,password)
	   //list.add(" created user with username " + username + " " + password)
	   assert list != null
	}
	
	void testComparePasswords(){
	   String pwd1 = "123"
	   String pwd2 = "1234"
	   def model = controller.comparePasswords(pwd1,pwd2)
	   assert model == false
	   
	   pwd2 = "123"
	   response.reset()
	   model = controller.comparePasswords(pwd1,pwd2)
	   assert model == true
	}
	
	void testSave(){
	  controller.save()
	  assert view == "/user/create"
	  assert model.userInstance.userName == null
	  
	  response.reset()
	  populateValidParams(params)
	  controller.save()
	  int id = User.count()
	  assert response.redirectUrl == "/user/show/"+id
	  
	  response.reset()
	  params.passwordHash = "1234"
	  controller.save()
	  assert response.redirectUrl == "/user/create"
	}
	
	void testShow(){
	  controller.show()
	  assert response.redirectUrl == "/user/list"
	  
	  response.reset()
	  params.id = 1
	  def model = controller.show()
	  assert model.userInstance.userName == "NormalUser"
	}
	
	void testEdit(){
	  controller.edit()
	  assert flash.message != null
	  assert response.redirectUrl == "/user/list"
	  
	  response.reset()
	  params.id = 1
	  def model = controller.edit()
	  assert model.userInstance.userName == "NormalUser"
	}
	
	void testUpdate(){
	  populateValidParams(params)
	  params.id = 3
	  controller.save()
	  params.confirmPassword = "1234"
	  controller.update()
	  assert view == "/user/edit"
	  assert model.userInstance.userName == "abc"
	  
	  response.reset()
	  params.id = 4
	  params.confirmPassword = "123"
	  controller.update()
	  assert response.redirectUrl == "/user/list"
	  
	  response.reset()
	  params.id = 3
	  params.version = "0"
	  controller.update()
	  assert view == "/user/edit"
	  assert model.userInstance.userName == "abc"
	  
	  response.reset()
	  params.version = "1"
	  controller.update()
	  assert response.redirectUrl == "/user/show/3"
	}
	
	void testDelete(){
	  controller.delete()
	  assert response.redirectUrl == "/user/list"
	  
	  response.reset()
	  params.id = 3
	  try{
		  controller.delete()
		  assert response.redirectUrl == "/user/list"
	  }catch(Exception e){
	      response.redirectUrl == "/user/show/3"
	  }
	  
	}
	
	void testImportData(){
	    
	    def model = controller.importData()
		
		
		def scarRemote = remote.Scar.findById(1)		
		def anamnesisCheck1Remote = remote.AnamnesisCheck.findById(1)
		def anamnesisCheck2Remote = remote.AnamnesisCheck.findById(2)
		def anamnesisCheck3Remote = remote.AnamnesisCheck.findById(3)		
		def anamnesisCheckTitleRemote = remote.AnamnesisCheckTitle.findById(1)
		def anamnesisFormRemote = remote.AnamnesisForm.findById(1)
		def bankaccountRemote = remote.Bankaccount.findById(1)
		def anamnesisCheckValueRemote = remote.AnamnesisChecksValue.findById(1)
		def standardizedPatientRemote = remote.StandardizedPatient.findById(1)
		def descriptionRemote = remote.Description.findById(1)
		def spokenLanguageRemote = remote.SpokenLanguage.findById(1)
		def nationalityRemote = remote.Nationality.findById(1)
		def professionRemote = remote.Profession.findById(1)
		def langSkillRemote = remote.LangSkill.findById(1)
		
	    def scarLocal = local.Scar.findByOrigId(1)		
		def anamnesisCheck1Local = local.AnamnesisCheck.findByOrigId(1)
		def anamnesisCheck2Local = local.AnamnesisCheck.findByOrigId(2)
		def anamnesisCheck3Local = local.AnamnesisCheck.findByOrigId(3)		
		def anamnesisCheckLocalCount = local.AnamnesisCheck.count()
		def anamnesisCheckTitleLocal = local.AnamnesisCheckTitle.findByOrigId(1)
		def anamnesisFormLocal = local.AnamnesisForm.findByOrigId(1)
		def bankaccountLocal = local.Bankaccount.findByOrigId(1)
		def anamnesisCheckValueLocal = local.AnamnesisChecksValue.findByOrigId(1)
		def standardizedPatientLocal = local.StandardizedPatient.findByOrigId(1)
		def descriptionLocal = local.Description.findByOrigId(1)
		def spokenLanguageLocal = local.SpokenLanguage.findByOrigId(1)
		def nationalityLocal = local.Nationality.findByOrigId(1)
		def professionLocal = local.Profession.findByOrigId(1)
		def langSkillLocal = local.LangSkill.findByOrigId(1)
		
		def user = User.findByUserName(standardizedPatientLocal.email)
		
		
		//test import a new scar data
	    assert scarLocal.origId == scarRemote.id
		assert scarLocal.traitType == scarRemote.traitType
		assert model.messages[0] == " imported default.scar.message 1"
		
		//test import a new anamnesisCheckTitle data
		assert anamnesisCheckTitleLocal.origId == anamnesisCheckTitleRemote.id
		assert anamnesisCheckTitleLocal.text == anamnesisCheckTitleRemote.text
		assert anamnesisCheckTitleLocal.sortOrder == anamnesisCheckTitleRemote.sortOrder
		assert model.messages[1] == " imported default.AnamnesisCheckTitle.message 1"
		
		//test import anamnesisCheck data
		compareAnamnesisCheckTitle(anamnesisCheck1Local,anamnesisCheck1Remote)									
		compareAnamnesisCheckTitle(anamnesisCheck2Local,anamnesisCheck2Remote)
		compareAnamnesisCheckTitle(anamnesisCheck3Local,anamnesisCheck3Remote)
		assert anamnesisCheckLocalCount == 3 //test local.AnamnesisCheck was imported 3 datas 
		assert model.messages[2] == " imported default.AnamnesisCheck.message 1"
		assert model.messages[3] == " imported default.AnamnesisCheck.message 2"
		assert model.messages[4] == " imported default.AnamnesisCheck.message 3"
		
		//test import anamnesisForm data
		assert anamnesisFormLocal.origId == anamnesisFormRemote.id
		//assert anamnesisFormLocal.version == 3 //didn't insert field 'version'  into local.AnamnesisForm
		assert anamnesisFormLocal.createDate == anamnesisFormRemote.createDate
		assert model.messages[5] == " imported default.AnamnesisForm.message 1"
		
		//test import a bankaccout data
		assert bankaccountLocal.origId == bankaccountRemote.id
		assert bankaccountLocal.bic == bankaccountRemote.bic
        assert bankaccountLocal.iban == bankaccountRemote.iban
        assert bankaccountLocal.bankName == bankaccountRemote.bankName
        assert bankaccountLocal.city == bankaccountRemote.city
        assert bankaccountLocal.ownerName == bankaccountRemote.ownerName
        assert bankaccountLocal.postalCode == bankaccountRemote.postalCode
		assert model.messages[6] == " imported default.Bankaccount.message 1"
		
		//test import a description data
		assert descriptionLocal.origId == 1
		//assert descriptionLocal.version == 1 //didn't insert field 'version' into local.Description
		assert descriptionLocal.description == "have a lot of time"
		assert model.messages[7] == " imported default.Description.message 1"
		
		//test import a SpokenLanguage data
		assert spokenLanguageLocal.origId == spokenLanguageRemote.id
		//assert spokenLanguageLocal.version == 1 //didn't insert field 'version' into local.SpokenLanguage
		assert spokenLanguageLocal.languageName == spokenLanguageRemote.languageName
		assert model.messages[8] == " imported default.SpokenLanguage.message 1"
		
		//test import a nationality data
		assert nationalityLocal.origId == nationalityRemote.id
		assert nationalityLocal.nationality == nationalityRemote.nationality
		assert model.messages[9] == " imported default.Nationality.message 1"
		
		//test import a profession data
		assert professionLocal.origId == professionRemote.id
		assert professionLocal.profession == professionRemote.profession
		assert model.messages[10] == " imported default.Profession.message 1"
		
		//test import anamnesisCheckValue data
		assert anamnesisCheckValueLocal.origId == anamnesisCheckValueRemote.id
		assert anamnesisCheckValueLocal.comment == anamnesisCheckValueRemote.comment
		assert anamnesisCheckValueLocal.truth == anamnesisCheckValueRemote.truth
		if(anamnesisCheckValueRemote.anamnesisForm){
			assert anamnesisCheckValueLocal.anamnesisForm == local.AnamnesisForm.
		                              findByOrigId(anamnesisCheckValueRemote.anamnesisForm.id)
	    }
		if(anamnesisCheckValueRemote.anamnesisCheck){
		    assert anamnesisCheckValueLocal.anamnesisCheck == local.AnamnesisCheck.
		                              findByOrigId(anamnesisCheckValueRemote.anamnesisCheck.id)
	    }
		assert model.messages[11] == " imported default.AnamnesisChecksValue.message 1"
		
		//test import standardizedpatient data
		assert standardizedPatientLocal.origId == standardizedPatientRemote.id
        assert standardizedPatientLocal.city == standardizedPatientRemote.city
        assert standardizedPatientLocal.email == standardizedPatientRemote.email
        assert standardizedPatientLocal.gender == standardizedPatientRemote.gender
        assert standardizedPatientLocal.height == standardizedPatientRemote.height
        assert standardizedPatientLocal.immagePath == standardizedPatientRemote.immagePath
        assert standardizedPatientLocal.maritalStatus == standardizedPatientRemote.maritalStatus
        assert standardizedPatientLocal.mobile == standardizedPatientRemote.mobile
        assert standardizedPatientLocal.name == standardizedPatientRemote.name
        assert standardizedPatientLocal.postalCode == standardizedPatientRemote.postalCode
        assert standardizedPatientLocal.preName == standardizedPatientRemote.preName
        assert standardizedPatientLocal.socialInsuranceNo == standardizedPatientRemote.socialInsuranceNo
        assert standardizedPatientLocal.street == standardizedPatientRemote.street
        assert standardizedPatientLocal.telephone == standardizedPatientRemote.telephone
        assert standardizedPatientLocal.telephone2 == standardizedPatientRemote.telephone2
        assert standardizedPatientLocal.videoPath == standardizedPatientRemote.videoPath
        assert standardizedPatientLocal.weight == standardizedPatientRemote.weight
        assert standardizedPatientLocal.workPermission == standardizedPatientRemote.workPermission
		if(standardizedPatientRemote.anamnesisForm){
            assert standardizedPatientLocal.anamnesisForm  == local.AnamnesisForm.
			                        findByOrigId(standardizedPatientRemote.anamnesisForm.id)
        }
		if (standardizedPatientRemote.profession){
            assert standardizedPatientLocal.profession == local.Profession.
			                        findByOrigId(standardizedPatientRemote.profession.id)
        }
	    if (standardizedPatientRemote.nationality){
			assert standardizedPatientLocal.nationality == local.Nationality.
			                        findByOrigId(standardizedPatientRemote.nationality.id)
	    }
	    if (standardizedPatientRemote.bankaccount){
		    assert standardizedPatientLocal.bankaccount == local.Bankaccount.
			                        findByOrigId(standardizedPatientRemote.bankaccount.id)
		}
		if (standardizedPatientRemote.description){
		    assert standardizedPatientLocal.description == local.Description.
			                        findByOrigId(standardizedPatientRemote.description.id)
		}
		assert model.messages[12] == " imported default.StandardizedPatient.message 1"
		
		//assert user.roles.get(Role.findByRoleName(controller.USER_ROLE)).roleName.contains(controller.USER_ROLE)
		
		def result = user.roles.findAll(){role -> role.roleName.contains(controller.USER_ROLE)}
		assert result.size() > 0
		assert user.standardizedPatient == standardizedPatientLocal
		assert user.userEmail ==  standardizedPatientLocal.email
		assert user.isActive == true
		assert user.passwordHash == controller.hashPassword(""+standardizedPatientLocal.socialInsuranceNo,user.userName)
		assert model.messages[13].contains(" created user with username ${user.userName}")
		
		//test import langSkill data
		assert langSkillLocal.origId == langSkillRemote.id
        assert langSkillLocal.skill == langSkillRemote.skill
		if(langSkillRemote.standardizedPatient){
		    assert langSkillLocal.standardizedPatient == local.StandardizedPatient.
		                            findByOrigId(langSkillRemote.standardizedPatient.id);
		}
		if(langSkillRemote.spokenLanguage){
            assert langSkillLocal.spokenLanguage == local.SpokenLanguage.
								    findByOrigId(langSkillRemote.spokenLanguage.id);
        }
		
		assert model.messages[14] == " imported default.LangSkill.message 1"
		
		
		model = controller.importData()
		//test import data exists
		assert model.messages[0] == " default.scar.message 1 Already Exists "
		assert model.messages[1] == " default.AnamnesisCheckTitle.message 1 Already Exists "
		assert model.messages[2] == " default.AnamnesisCheck.message 1 Already Exists "
		assert model.messages[3] == " default.AnamnesisCheck.message 2 Already Exists "
		assert model.messages[4] == " default.AnamnesisCheck.message 3 Already Exists "
		assert model.messages[5] == " default.AnamnesisForm.message 1 Already Exists "
	    assert model.messages[6] == " default.Bankaccount.message 1 Already Exists "
	    assert model.messages[7] == " default.Description.message 1 Already Exists "			
	    assert model.messages[8] == " default.SpokenLanguage.message 1 Already Exists "
		assert model.messages[9] == " default.Nationality.message 1 Already Exists "
		assert model.messages[10] == " default.Profession.message 1 Already Exists "
		assert model.messages[11] == " default.AnamnesisChecksValue.message 1 Already Exists "
		assert model.messages[12] == " default.StandardizedPatient.message 1 Already Exists "
		assert model.messages[13] == " default.LangSkill.message 1 Already Exists "

	}
	
	def compareAnamnesisCheckTitle(titleLocal,titleRemote){
	    assert titleLocal.origId ==  titleRemote.id
		assert titleLocal.text == titleRemote.text
		assert titleLocal.value == titleRemote.value
		assert titleLocal.sortOrder == titleRemote.sortOrder
		assert titleLocal.type == titleRemote.type
	    if(titleRemote.title){
	    	assert titleLocal.title == local.AnamnesisCheck.
		                            findByOrigId(titleRemote.title.id)
		}
		if(titleRemote.anamnesisCheckTitle){
		    assert titleLocal.anamnesisCheckTitle == local.AnamnesisCheckTitle.
		                            findByOrigId(titleRemote.anamnesisCheckTitle.id)
		}
	}


}