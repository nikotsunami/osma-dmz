package sp_portal

import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.TestFor
import sp_portal.DataSetupHelper;



/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SelectAvailableDatesController)
@Mock([User,
		local.Bankaccount,
			Role,
				local.StandardizedPatient,
					local.AnamnesisForm,
						local.AnamnesisCheckTitle,
						  local.AnamnesisCheck,
							local.AnamnesisChecksValue,
								local.OsceDay,
									local.Training,
										local.PatientlnSemester,
											local.Emails])
class SelectAvailableDatesControllerTests {
   def  datasetup;
 
	
	@Before
    void setUp() {
		datasetup= new DataSetupHelper()

		datasetup.getDataSetB()
		datasetup.setUpOsceDays();
		datasetup.setUpTrainingDays()
		datasetup.setupStandardizedPatients();
		datasetup.setUpPatientLnSemester();
		datasetup.setUpEmptyPatientLnSemester();
        session.user= datasetup.normalUser;
		
    }

	@After
    void tearDown() {
		datasetup==null;
    }


	/**
	 *
	 *Unit test the contents of these lists. both empty and full and when PatientInSemester does not exist.
	 */
	@Test
    void xtestShow() {
		def mod = controller.show();


		assertNotNull	mod

		assertNotNull	mod.availableOsceDays
        assertEquals 3, mod.availableOsceDays.size()

		assertNotNull	mod.availableTrainingDays
        assertEquals 3, mod.availableTrainingDays.size()
		
		assertNotNull	mod.acceptedTrainingDays
        assertEquals 1, mod.acceptedTrainingDays.size()
		
		assertNotNull	mod.acceptedOsceDays
        assertEquals 1, mod.acceptedOsceDays.size()
		

		
    }
	
	
	
	void xtestupdate(){
	
		params["osce.1.id"] = "on"
		params["osce.2.id"] = [:]
		params["osce.3.id"] = "on"
	
		params["training.1.id"] = "on"
		params["training.2.id"] = [:]
		params["training.3.id"] = "on"

	
	
		def mod=controller.update();

		def currentSP = datasetup.normalUser.standardizedPatient
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		
	
		
		assertNotNull currentPatInsem.acceptedOsceDay
		assertNotNull currentPatInsem.acceptedTraining
		
		assertNotNull currentPatInsem.acceptedOsceDay.find{  day -> day.id == 1}
		assertNull currentPatInsem.acceptedOsceDay.find{  day -> day.id == 2}
		assertNotNull currentPatInsem.acceptedOsceDay.find{  day -> day.id == 3}
		assertNotNull currentPatInsem.acceptedTraining.find{  train -> train.id == 1}
		assertNull currentPatInsem.acceptedTraining.find{  train -> train.id == 2}
		assertNotNull currentPatInsem.acceptedTraining.find{  train -> train.id == 3}
				
	}
	
	/**
	 * Verify that if the user selects at least on osce AND at least on Training then the accepted flag in patientInSemester will be set to true
	 */
	void testAcceptedSetToTrue(){
				
        session.user= datasetup.normalUser2;
				
		// to start verify the accepted flag is false
		def currentSP = datasetup.normalUser2.standardizedPatient
		assertNotNull currentSP
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
				
		
		params["osce.1.id"] = "on"
		params["training.2.id"] = "on"



		// run the code under test
				
		def mod=controller.update();

		// check the result 
		
		currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertTrue currentPatInsem.accepted
		
		assertEquals 1, currentPatInsem.acceptedOsceDay.size()
		assertEquals datasetup.osce1 , currentPatInsem.acceptedOsceDay.toArray()[0]
		
		assertEquals 1, currentPatInsem.acceptedTraining.size()
		assertEquals datasetup.training2 , currentPatInsem.acceptedTraining.toArray()[0]



	}
	
	
	/**
	 * Verify that if the user selects at least on osce AND and no Training then the accepted flag in patientInSemester will be set to false
	 */
	void testAcceptedSetToFalseNoTraining(){
				
        session.user= datasetup.normalUser2;
				
		// to start verify the accepted flag is false
		def currentSP = datasetup.normalUser2.standardizedPatient
		assertNotNull currentSP
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
				
		
		params["osce.1.id"] = "on"


		// run the code under test
				
		def mod=controller.update();

		// check the result 
		
		currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
		
		assertEquals 0, currentPatInsem.acceptedOsceDay.size()
		assertEquals 0, currentPatInsem.acceptedTraining.size()
		


	}
	
	/**
	 * Verify that if the user selects at least one Training AND and no osce then the accepted flag in patientInSemester will be set to false
	 */
	void testAcceptedSetToFalseNoOsce(){
				
        session.user= datasetup.normalUser2;
				
		// to start verify the accepted flag is false
		def currentSP = datasetup.normalUser2.standardizedPatient
		assertNotNull currentSP
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
				
		
		params["training.2.id"] = "on"
		
		// run the code under test
				
		def mod=controller.update();

		// check the result 
		
		currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
		
		assertEquals 0, currentPatInsem.acceptedOsceDay.size()
		assertEquals 0, currentPatInsem.acceptedTraining.size()
		


	}
	
	
	/**
	 * Verify that if the user selects no Training AND and no osce then the accepted flag in patientInSemester will be set to false
	 */
	void testAcceptedSetToFalseNothingSelected(){
				
        session.user= datasetup.normalUser2;
				
		// to start verify the accepted flag is false
		def currentSP = datasetup.normalUser2.standardizedPatient
		assertNotNull currentSP
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
				
		
			
		// run the code under test
				
		def mod=controller.update();

		// check the result 
		
		currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		assertFalse currentPatInsem.accepted
		
		assertEquals 0, currentPatInsem.acceptedOsceDay.size()
		assertEquals 0, currentPatInsem.acceptedTraining.size()
		


	}
	
	
}
