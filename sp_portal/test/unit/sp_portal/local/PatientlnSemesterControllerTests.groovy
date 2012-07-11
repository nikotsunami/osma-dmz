package sp_portal.local



import org.junit.*
import grails.test.mixin.*
import sp_portal.DataSetupHelper;

@TestFor(PatientlnSemesterController)
@Mock([sp_portal.User,Bankaccount,sp_portal.Role,StandardizedPatient,AnamnesisForm,AnamnesisCheckTitle,AnamnesisCheck,AnamnesisChecksValue,Training,OsceDay,PatientlnSemester])
class PatientlnSemesterControllerTests {
	def datasetup = new DataSetupHelper()

	@Before
	void setUp() {
        // Setup logic here
		datasetup.setUpTraining1()
		datasetup.setUpOsceDay1()
		datasetup.getDataSetA()
    }

    void tearDown() {
        // Tear down logic here
		datasetup = null;
    }

    void testSomething() {
        assert true
    }
	
	 def populateValidParams(params) {
      assert params != null
      params["standardizedPatient"] = datasetup.standardizedPatient1
	  params["acceptedOsceDay"] = datasetup.osceDay1
	  params["acceptedTraining"] = datasetup.training1
    }

    void testIndex() {
        controller.index()
        assert "/patientlnSemester/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.patientlnSemesterInstanceList.size() == 0
        assert model.patientlnSemesterInstanceTotal == 0
		
		populateValidParams(params)
        def patientlnSemester = new PatientlnSemester(params)
        assert patientlnSemester.save() != null
		assert PatientlnSemester.count() == 1
		
		model = controller.list()
		assert model.patientlnSemesterInstanceList.size() == 1
        assert model.patientlnSemesterInstanceTotal == 1
		
    }

    void testCreate() {
       def model = controller.create()

       assert model.patientlnSemesterInstance != null
    }

    void testSave() {
        controller.save()

        assert model.patientlnSemesterInstance != null
        assert view == '/patientlnSemester/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/patientlnSemester/show/1'
        assert controller.flash.message != null
        assert PatientlnSemester.count() == 1
		
		def patientlnSemester = PatientlnSemester.findById(1)
		assertNotNull patientlnSemester;
		assert patientlnSemester.standardizedPatient == datasetup.standardizedPatient1
		assert patientlnSemester.acceptedOsceDay.size() == 1
		assert patientlnSemester.acceptedOsceDay == [datasetup.osceDay1] as LinkedHashSet
		assert patientlnSemester.acceptedTraining.size() == 1
		assert patientlnSemester.acceptedTraining == [datasetup.training1] as LinkedHashSet
		
    }

    void testShow() {

        populateValidParams(params)
        def patientlnSemester = new PatientlnSemester(params)

        assert patientlnSemester.save() != null

        params.id = patientlnSemester.id

        def model = controller.show()

        assert model.patientlnSemesterInstance == patientlnSemester
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/patientlnSemester/list'


        populateValidParams(params)
        def patientlnSemester = new PatientlnSemester(params)

        assert patientlnSemester.save() != null

        params.id = patientlnSemester.id

        def model = controller.edit()

        assert model.patientlnSemesterInstance == patientlnSemester
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/patientlnSemester/list'

        response.reset()


        populateValidParams(params)
        def patientlnSemester = new PatientlnSemester(params)

        assert patientlnSemester.save() != null

        // test invalid parameters in update
        params.id = patientlnSemester.id
        params["standardizedPatient"] = null

        controller.update()

        assert view == "/patientlnSemester/edit"
        assert model.patientlnSemesterInstance != null

        patientlnSemester.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/patientlnSemester/show/$patientlnSemester.id"
        assert flash.message != null
		def patientlnSemester_1 = PatientlnSemester.findById(patientlnSemester.id)
		assertNotNull patientlnSemester_1;
		assert patientlnSemester_1.standardizedPatient == datasetup.standardizedPatient1
		assert patientlnSemester_1.acceptedOsceDay.size() == 1
		assert patientlnSemester_1.acceptedOsceDay == [datasetup.osceDay1] as LinkedHashSet
		assert patientlnSemester_1.acceptedTraining.size() == 1
		assert patientlnSemester_1.acceptedTraining == [datasetup.training1] as LinkedHashSet

        //test outdated version number
        response.reset()
        patientlnSemester.clearErrors()

        populateValidParams(params)
        params.id = patientlnSemester.id
        params.version = -1
        controller.update()

        assert view == "/patientlnSemester/edit"
        assert model.patientlnSemesterInstance != null
        assert model.patientlnSemesterInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/patientlnSemester/list'

        response.reset()

        populateValidParams(params)
        def patientlnSemester = new PatientlnSemester(params)

        assert patientlnSemester.save() != null
        assert PatientlnSemester.count() == 1

        params.id = patientlnSemester.id

        controller.delete()

        assert PatientlnSemester.count() == 0
        assert PatientlnSemester.get(patientlnSemester.id) == null
        assert response.redirectedUrl == '/patientlnSemester/list'
    }


}
