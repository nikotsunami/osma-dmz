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
										local.PatientlnSemester])
class SelectAvailableDatesControllerTests {
   def  datasetup;
 
	
	@Before
    void setUp() {
		datasetup= new DataSetupHelper()

		datasetup.getDataSetA()
		datasetup.setUpOsceDays();
		datasetup.setUpTrainingDays()
		datasetup.setupStandardizedPatients();
		datasetup.setUpPatientLnSemester();
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
    void testShow() {
		def mod = controller.show();


		assertNotNull	mod

		assertNotNull	mod.availableOsceDays
        assertEquals 3, mod.availableOsceDays.size()

		assertNotNull	mod.availableTrainingDays
        assertEquals 3, mod.availableTrainingDays.size()
		
		assertNotNull	mod.acceptedTrainingDays
        assertEquals 1, mod.acceptedTrainingDays.size()
		
		assertNotNull	mod.acceptedOsceDays
        assertEquals 2, mod.acceptedOsceDays.size()
		
		
	

	
		
    }
	
	void testupdate(){
	
		params["osce.1"] = true
		params["osce.2"] = false
		params["osce.3"] = true
	
		params["training.1"] = true
		params["training.2"] = false
		params["training.3"] = true

	
	
		def mod=controller.update();

		def currentSP = datasetup.normalUser.standardizedPatient
		def currentPatInsem =local.PatientlnSemester.findByStandardizedPatient(currentSP);
		
		assertNull currentPatInsem.acceptedOsceDay.find{  day -> day.id == 1}
		assertNull  currentPatInsem.acceptedOsceDay.find{  day -> day.id == 2}
		 currentPatInsem.acceptedOsceDay.find{  day -> day.id == 3}
		
		assertNotNull currentPatInsem.acceptedOsceDay.addAll(params["osce.1"]);
		assertNotNull currentPatInsem.acceptedOsceDay.addAll(params["osce.2"]);
		assertNotNull currentPatInsem.acceptedOsceDay.addAll(params["osce.3"]);
		
	
		
		assertNull currentPatInsem.acceptedTraining.find{  train -> train.id == 1}
		assertNull currentPatInsem.acceptedTraining.find{  train -> train.id == 2}
		assertNull currentPatInsem.acceptedTraining.find{  train -> train.id == 3}
		
		
		assertNotNull currentPatInsem.acceptedTraining.addAll(params["osce.1"]);
		assertNotNull currentPatInsem.acceptedTraining.addAll(params["osce.2"]);
		assertNotNull currentPatInsem.acceptedTraining.addAll(params["osce.3"]);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
