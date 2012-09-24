package sp_portal



import org.junit.*
import grails.test.mixin.*
import sp_portal.local.StandardizedPatient;
import sp_portal.*;
import sp_portal.local.*;

@TestFor(SendEmailController)
@Mock([Role,User,local.Emails,local.Bankaccount,local.StandardizedPatient,local.AnamnesisForm,local.AnamnesisCheckTitle,local.AnamnesisCheck,local.AnamnesisChecksValue])
class SendEmailControllerTests{


    def datasetup = null;

    @Before
    void setUp(){
		datasetup = new DataSetupHelper()
        datasetup.getDataSetB()
    }

    @After
    void tearDown(){

      datasetup = null;

    }


	
	void testShow() {
		def patientInstanceList = local.StandardizedPatient.findAll();
		assert 2 == patientInstanceList.size();


        def model = controller.show()
		
        assertNotNull model.patientInstanceList
		assertEquals 2, model.patientInstanceList.size()
		
    }
	
	/**
	*  test Bug786: Send emails has sorting head but does not work.
	*/
	void testBug786(){
	
        datasetup.setupStandardizedPatients("a") 	
		
        def model = null
		def list = null
	    
		
		model = controller.show()
		list = model.patientInstanceList
		assertEquals list.size(),3
		assert list[1].email == "Bsp1@test.com"
		
		params.sort = "email"
		params.order = "asc"
	    model = controller.show()
		list = model.patientInstanceList
		testDataAsc(list,"email",3)

		
		params.order = "desc"
		model = controller.show()
		list = model.patientInstanceList
		testDataDesc(list,"email",3)
		
		params.sort = "name"
		model = controller.show()
		list = model.patientInstanceList
		testDataDesc(list,"name",3)
		
		params.order = "asc"
		model = controller.show()
		list = model.patientInstanceList
		testDataAsc(list,"name",3)
		
	}
	
	/**
	* this method is used for test Bug786:test the size of data and that it is sorted ascendingly according to the supplied field 
	*/
	private void testDataAsc(list,field,expectedSize){
	    assertEquals ("Incorrect data size",list.size(),expectedSize)
		for(int i=0;i<list.size()-1;i++){
			assert list[i][field].getAt(0) <= list[i+1][field].getAt(0)
		}
	}
	
	/**
	* this method is used for testBug786:check the size of data and  that it is sorted descendingly according to the supplied field
	*/
	private void testDataDesc(list,field,expectedSize){
	    assertEquals ("Incorrect data size",list.size(),expectedSize)
		for(int i=0;i<list.size()-1;i++){
			assert list[i][field].getAt(0) >= list[i+1][field].getAt(0)
		}

	}


	void testSelectAll(){
		def model = controller.selectAll();
		assert "/sendEmail/show?isSend=true" == response.redirectedUrl
		//TODO can't test params
//		assertEquals true, response.redirectArgs.params.isSend
//		assertEquals true, response.params.isSend
//		assertEquals true, controller.params.isSend
//		assertEquals true, controller.redirectArgs.params.isSend
//		assertEquals true, redirectArgs.params.isSend
		


	}
	
	
	void testShowPreviewEmail(){
		assertNotNull "Invite email not configured", grailsApplication.config?.sp_portal?.mail?.inviteStandardizedPatients?.defaultText 
	
	
		 def emailText = """
		
			hello this is an invite
			
			cats
			
			dogs 
			
			sheep
		
		"""
	
		grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.defaultText = emailText;
	
	
		params["patient.${datasetup.standardizedPatient1.id}"] = "send"
		
		params["patient.${datasetup.standardizedPatient2.id}"] = "send"
		
	
		def retModel = controller.showPreviewEmail();
		
		assertEquals 2, session.sendPatients.size()
		assertEquals datasetup.standardizedPatient1 , session.sendPatients[0]
		assertEquals datasetup.standardizedPatient2 , session.sendPatients[1]


		assertEquals emailText, retModel.defaultEmail 

	
	}

	void testShowPreviewEmail2(){
	
		params["patient.${datasetup.standardizedPatient2.id}"] = "send"
		
	
		controller.showPreviewEmail();
		

		assertEquals 1, session.sendPatients.size()
		assertEquals datasetup.standardizedPatient2 , session.sendPatients[0]
		

	
	}

	
	void testSendEmail(){
	
		params.editedEmailSubject = "this is a test subject"
		def patients = local.StandardizedPatient.findAll();
		session.sendPatients =patients
		
		
		def sentEmails = [];
		
		controller.DMZMailService = [
			sendMails: { eTo, eFrom, eSubject, eBody ->
				def email = ["to":eTo,"subject":eSubject,"body":eBody ]
				sentEmails << email;
				
			}
		] as DMZMailService;
		
		String emailText = "hello some text for the email.";
		
		params.editedEmailText = emailText;
		
		
		
		

		def model = controller.sendEmail();
		
		assertEquals 2, sentEmails.size()
		assertEquals "sp1@test.com", sentEmails[0].to;
		assertEquals "Bsp1@test.com", sentEmails[1].to;
		
		assertEquals "this is a test subject", sentEmails[0].subject;
		assertEquals "this is a test subject", sentEmails[1].subject;
		
		assertEquals "hello some text for the email.", sentEmails[0].body;
		assertEquals "hello some text for the email.", sentEmails[1].body;
		
	}
	
	void testSendEmail2(){
	
		params.editedEmailSubject = "this is a test subject"
		def patients = local.StandardizedPatient.findAll();
		session.sendPatients =patients
		
		
		def sentEmails = [];
		
		controller.DMZMailService = [
			sendMails: { eTo,eFrom,eSubject, eBody ->
				def email = ["to":eTo,"subject":eSubject,"body":eBody ]
				sentEmails << email;
				
			}
		] as DMZMailService;
		
		String emailText = """
		Dear #preName #name
		
		this is email text for test;
		
				
		Yours sincerely

		paul
		
		"""
		String expectedEmailText= """
		Dear bob smith
		
		this is email text for test;
		
				
		Yours sincerely

		paul
		
		"""
		
		params.editedEmailText = emailText;
		
		
		
		

		def model = controller.sendEmail();
		
		assertEquals 2, sentEmails.size()
		assertEquals "sp1@test.com", sentEmails[0].to;
		assertEquals "Bsp1@test.com", sentEmails[1].to;
		
		assertEquals "this is a test subject", sentEmails[0].subject;
		assertEquals "this is a test subject", sentEmails[1].subject;
		
		assertEquals expectedEmailText, sentEmails[0].body;
		assertEquals expectedEmailText, sentEmails[1].body;
		
	}
	
	
	void testShowSentEmail() {	
		def model =controller.showSentEmail();
		
		assertEquals 2,model.emailTotal
		assertNotNull model.emailList
    }

	void testShowEmailDetails() {	
		def email = local.Emails.list();
		params.id = email[0].id;
		def model =controller.showEmailDetails();
		
		assertNotNull model.emailInstance
		
		assertEquals email[0],model.emailInstance
    }

}
