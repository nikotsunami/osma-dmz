package sp_portal.local



import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.TestFor
import sp_portal.DataSetupHelper;

@TestFor(OsceDayController)
@Mock([sp_portal.User,Bankaccount,sp_portal.Role,StandardizedPatient,AnamnesisForm,AnamnesisCheckTitle,AnamnesisCheck,AnamnesisChecksValue,Training,OsceDay,PatientlnSemester,Osce,Semester])
class OsceDayControllerTests {
	def datasetup = new DataSetupHelper()
	def Calendar baseTime = null;

	@Before
	void setUp() {
        // Setup logic here
		datasetup.setUpSemesters();
		datasetup.setUpOsces();
		datasetup.setUpTraining1()
		datasetup.setUpOsceDay1()
		datasetup.getDataSetA()
		baseTime = GregorianCalendar.getInstance();
		baseTime.setTime(new Date());
    }

    void tearDown() {
        // Tear down logic here
		datasetup = null;
		baseTime = null;
    }

    void testSomething() {
        assert true
    }


    def popuLateValidParamsSave(params) {
      assert params != null
	  params.osce = 1L;
	  def futureCal = baseTime.clone()
		
		futureCal.add(Calendar.DAY_OF_MONTH,2)
		futureCal.set(Calendar.HOUR_OF_DAY,0)
		futureCal.set(Calendar.MINUTE,0)
	  params["osceDate"] = futureCal.getTime();
      
    }
	
	def populateValidParams(params) {
      assert params != null
	  params.osce = Osce.get(1L);
	  def futureCal = baseTime.clone()
		
		futureCal.add(Calendar.DAY_OF_MONTH,2)
		futureCal.set(Calendar.HOUR_OF_DAY,0)
		futureCal.set(Calendar.MINUTE,0)
	  params["osceDate"] = futureCal.getTime();
      
    }

    void testIndex() {
        controller.index()
        assert "/osceDay/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.osceDayInstanceList.size() == 1
        assert model.osceDayInstanceTotal == 1
    }

    void testCreate() {
       def model = controller.create()

       assert model.osceDayInstance != null
    }

    void testSave() {
		
        controller.save()

        assert model.osceDayInstance == null
        assert view == '/osceDay/create'

        response.reset()

        popuLateValidParamsSave(params)
        controller.save()

        assert response.redirectedUrl == '/osceDay/show/2'
        assert controller.flash.message != null
        assert OsceDay.count() == 2
		
		def osceDay = OsceDay.findById(2)
		assertNotNull osceDay;
		def expectedOsceDate = baseTime.clone()
		
		expectedOsceDate.add(Calendar.DAY_OF_MONTH,2)
		expectedOsceDate.set(Calendar.HOUR_OF_DAY,0)
		expectedOsceDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedOsceDate.getTime() , osceDay.osceDate ;
    }

    void testShow() {
	
        populateValidParams(params)
        def osceDay = new OsceDay(params)

        assert osceDay.save() != null

        params.id = osceDay.id

        def model = controller.show()

        assert model.osceDayInstance == osceDay
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/osceDay/list'


        populateValidParams(params)
        def osceDay = new OsceDay(params)

        assert osceDay.save() != null

        params.id = osceDay.id

        def model = controller.edit()

        assert model.osceDayInstance == osceDay
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/osceDay/list'

        response.reset()


        populateValidParams(params)
        def osceDay = new OsceDay(params)

        assert osceDay.save() != null

        // test invalid parameters in update
        params.id = osceDay.id
		params["osceDate"] = null
		params.osce = null

        controller.update()

        assert view == "/osceDay/edit"
        assert model.osceDayInstance != null

        osceDay.clearErrors()

        popuLateValidParamsSave(params)
        controller.update()

        assert response.redirectedUrl == "/osceDay/show/$osceDay.id"
        assert flash.message != null
		def osceDay_update = OsceDay.findById(osceDay.id)
		assertNotNull osceDay_update;
		def expectedOsceDate = baseTime.clone()
		
		expectedOsceDate.add(Calendar.DAY_OF_MONTH,2)
		expectedOsceDate.set(Calendar.HOUR_OF_DAY,0)
		expectedOsceDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedOsceDate.getTime() , osceDay_update.osceDate ;

        //test outdated version number
        response.reset()
        osceDay.clearErrors()

        popuLateValidParamsSave(params)
        params.id = osceDay.id
        params.version = -1
        controller.update()

        assert view == "/osceDay/edit"
        assert model.osceDayInstance != null
        assert model.osceDayInstance.errors.getFieldError('version')
        assert flash.message != null
    }
	
	void testUpdateNotAllowed(){
		populateValidParams(params)
        def osceDay = new OsceDay(params)
        assert osceDay.save() != null
		params["standardizedPatient"] = datasetup.standardizedPatient1
	    params["acceptedOsceDay"] = osceDay
	    params["acceptedTraining"] = datasetup.training1
	    params["semester"] = datasetup.semester
	    def patientlnSemester = new PatientlnSemester(params)
        assert patientlnSemester.save() != null
		
		params.id = osceDay.id
		params["osceDate"] = new Date()
		controller.update()
		def osceDay_update = OsceDay.findById(osceDay.id)
		assertNotNull osceDay_update;
		def expectedOsceDate = baseTime.clone()
		
		expectedOsceDate.add(Calendar.DAY_OF_MONTH,2)
		expectedOsceDate.set(Calendar.HOUR_OF_DAY,0)
		expectedOsceDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedOsceDate.getTime() , osceDay_update.osceDate ;
		
	}

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/osceDay/list'

        response.reset()

        populateValidParams(params)
        def osceDay = new OsceDay(params)

        assert osceDay.save() != null
        assert OsceDay.count() == 2

        params.id = osceDay.id

        controller.delete()

        assert OsceDay.count() == 1
        assert OsceDay.get(osceDay.id) == null
        assert response.redirectedUrl == '/osceDay/list'
		
	   
    }
	
	void testDeleteNotAllowed(){
		 params["standardizedPatient"] = datasetup.standardizedPatient1
	    params["acceptedOsceDay"] = datasetup.osceDay1
	    params["acceptedTraining"] = datasetup.training1
	    params["semester"] = datasetup.semester
	    def patientlnSemester = new PatientlnSemester(params)
        assert patientlnSemester.save() != null
	  
		params.id = datasetup.osceDay1.id

        controller.delete()
		assert OsceDay.count() == 1
	}
}
