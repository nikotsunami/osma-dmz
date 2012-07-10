package sp_portal

import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.TestFor
import sp_portal.DataSetupHelper;


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(CheckQuestionsController)
@Mock([User,Role,local.StandardizedPatient,local.AnamnesisForm,local.AnamnesisCheckTitle,local.AnamnesisCheck,local.AnamnesisChecksValue,local.Bankaccount])
class CheckQuestionsControllerTests {
    def datasetup = null;
	@Before
    void setUp() {
		datasetup = new DataSetupHelper()
        datasetup.getDataSetA()

        session.user = datasetup.normalUser;
    }

    void tearDown() {
       datasetup = null;
    }

    void testSomething() {
        assert true
    }
	

	
	void testSaveDataBoolean(){

		assert params != null
		params["question.2"] = "false"
		controller.save()
		
		assert local.AnamnesisChecksValue.count() == 2
		
		def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(datasetup.anamnesisForm1,datasetup.anamnesisCheck2);
		assert checkValue.truth == false
		
	}
	
	void testSaveDataString(){

		assert params != null
		params["question.3"] = "hello"
		controller.save()
		
		assert local.AnamnesisChecksValue.count() == 2
		
		def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(datasetup.anamnesisForm1,datasetup.anamnesisCheck3);
		assertEquals checkValue.anamnesisChecksValue , "hello"
		
	}


	void testSaveDataNullString(){

		assert params != null
		params["question.3"] = ""
		controller.save()
		
		assert local.AnamnesisChecksValue.count() == 1
		def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(datasetup.anamnesisForm1,datasetup.anamnesisCheck3);
		assert checkValue == null
		
	}

	
	
	void testUpdateDataBoolean(){
		
		assert params != null
		def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(datasetup.anamnesisForm1,datasetup.anamnesisCheck1);
		assert checkValue.truth == false
		
		
		params["question.1"] = "true"
		controller.save()
		
		assert local.AnamnesisChecksValue.count() == 1
		
		checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(datasetup.anamnesisForm1,datasetup.anamnesisCheck1);
		assert checkValue.truth == true
		
	}
	
	
	
}
