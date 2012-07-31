package sp_portal.local



import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.TestFor
import sp_portal.DataSetupHelper;

@TestFor(TrainingController)
@Mock([sp_portal.User,Bankaccount,sp_portal.Role,StandardizedPatient,AnamnesisForm,AnamnesisCheckTitle,AnamnesisCheck,AnamnesisChecksValue,Training,OsceDay,PatientlnSemester])
class TrainingControllerTests {
	def datasetup = new DataSetupHelper()
	
	def Calendar baseTime = null;

	@Before
	void setUp() {
        // Setup logic here

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


    def populateValidParams(params) {
        assert params != null

		def futureCal = baseTime.clone()
		
		futureCal.add(Calendar.DAY_OF_MONTH,2)
		futureCal.set(Calendar.HOUR_OF_DAY,0)
		futureCal.set(Calendar.MINUTE,0)
		

	    params["name"] = 'checkout'
		params["trainingDate"] = futureCal.getTime();
		params["timeStartHour"] = 8
		params["timeStartMin"] = 30
		params["timeEndHour"] = 10
		params["timeEndMin"] = 30
    }

	def setTrainingParams(training){
		def futureCal = baseTime.clone()		
		futureCal.add(Calendar.DAY_OF_MONTH,2)
		futureCal.set(Calendar.HOUR_OF_DAY,0)
		futureCal.set(Calendar.MINUTE,0)
		training.name = 'traning_2'
		training.trainingDate = futureCal.getTime();
		training.timeStart = new Date(training.trainingDate.getTime()+6*60*60*1000)
		training.timeEnd = new Date(training.trainingDate.getTime()+10*60*60*1000)
	}

    void testSave() {
		
        controller.save()

        assert model.trainingInstance != null
        assert view == '/training/create'

        response.reset()

        populateValidParams(params)

        controller.save()

        assert response.redirectedUrl == '/training/show/1'
        assert controller.flash.message != null
        assert Training.count() == 1
		
		def training = Training.findByName("checkout")
		assertNotNull training;


		def expectedTrainingDate = baseTime.clone()
		
		expectedTrainingDate.add(Calendar.DAY_OF_MONTH,2)
		expectedTrainingDate.set(Calendar.HOUR_OF_DAY,0)
		expectedTrainingDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedTrainingDate.getTime() , training.trainingDate ;
		assert training.trainingDate.getHours() == 0;
		
		assert training.timeStart.getHours() == 8 ;
		assert training.timeStart.getMinutes() == 30 ;
		
		assert training.trainingDate.getYear() == training.timeStart.getYear()
		assert training.trainingDate.getMonth() == training.timeStart.getMonth()
		assert training.trainingDate.getDay() == training.timeStart.getDay()
		
		assert training.timeEnd.getHours() == 10 ;
		assert training.timeEnd.getMinutes() == 30 ;
		assert training.timeEnd.getSeconds() == 0 ;
		assert training.trainingDate.getYear() == training.timeStart.getYear()
		assert training.trainingDate.getMonth() == training.timeStart.getMonth()
		assert training.trainingDate.getDay() == training.timeStart.getDay()
    }
	

	
	
	void testSaveInvalid() {
		params["name"] = ''
		params["trainingDate"] = new Date(new Date().getTime()+48*60*60*1000)
		params["timeStartHour"] = 8
		params["timeStartMin"] = 30
		params["timeEndHour"] = 10
		params["timeEndMin"] = 30
		
		controller.save()
		assert Training.count() == 1
		
		params["name"] = 'traning'
		params["trainingDate"] = new Date(new Date().getTime()+48*60*60*1000)
		params["timeStartHour"] = 8
		params["timeStartMin"] = 30
		params["timeEndHour"] = 7
		params["timeEndMin"] = 30
		
		controller.save()
		assert Training.count() == 1
		
		params["name"] = 'traning'
		params["trainingDate"] = null
		params["timeStartHour"] = 8
		params["timeStartMin"] = 30
		params["timeEndHour"] = 10
		params["timeEndMin"] = 30
		
		controller.save()
		assert Training.count() == 1
	}


    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/training/list'

        response.reset()

        def training = new Training()
		setTrainingParams(training)

        assert training.save() != null
		assert Training.count() == 1

        // test invalid parameters in update
        params.id = training.id
		params["name"] = ''

        controller.update()

        assert view == "/training/edit"
        assert model.trainingInstance != null

        training.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/training/show/$training.id"
        assert flash.message != null
		assert Training.count() == 1

		def training_checkout = Training.findByName('checkout')
		assertNotNull training_checkout;
		def expectedTrainingDate = baseTime.clone()
		
		expectedTrainingDate.add(Calendar.DAY_OF_MONTH,2)
		expectedTrainingDate.set(Calendar.HOUR_OF_DAY,0)
		expectedTrainingDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedTrainingDate.getTime() , training_checkout.trainingDate ;
		assert training_checkout.trainingDate.getHours() == 0;
		
		assert training_checkout.timeStart.getHours() == 8 ;
		assert training_checkout.timeStart.getMinutes() == 30 ;
		assert training_checkout.timeStart.getSeconds() == 0 ;
		assert training_checkout.trainingDate.getYear() == training_checkout.timeStart.getYear()
		assert training_checkout.trainingDate.getMonth() == training_checkout.timeStart.getMonth()
		assert training_checkout.trainingDate.getDay() == training_checkout.timeStart.getDay()
		
		assert training_checkout.timeEnd.getHours() == 10 ;
		assert training_checkout.timeEnd.getMinutes() == 30 ;
		assert training_checkout.timeEnd.getSeconds() == 0 ;
		assert training_checkout.trainingDate.getYear() == training_checkout.timeStart.getYear()
		assert training_checkout.trainingDate.getMonth() == training_checkout.timeStart.getMonth()
		assert training_checkout.trainingDate.getDay() == training_checkout.timeStart.getDay()

        response.reset()
        training.clearErrors()

        populateValidParams(params)
        params.id = training.id
        params.version = -1
        controller.update()

        assert view == "/training/edit"
        assert model.trainingInstance != null
        assert model.trainingInstance.errors.getFieldError('version')
        assert flash.message != null
    }
	
	void testUpdateNotAllowed(){
		def training = new Training(params)
		setTrainingParams(training)
        assert training.save() != null
		params["standardizedPatient"] = datasetup.standardizedPatient1
	    params["acceptedOsceDay"] = datasetup.osceDay1
	    params["acceptedTraining"] = training
	    def patientlnSemester = new PatientlnSemester(params)
        assert patientlnSemester.save() != null
	  
		params.id = training.id
		populateValidParams(params)
		controller.update()
		def training_update = Training.findById(training.id)
		assert training_update.name == 'traning_2'
		
		def expectedTrainingDate = baseTime.clone()
		
		expectedTrainingDate.add(Calendar.DAY_OF_MONTH,2)
		expectedTrainingDate.set(Calendar.HOUR_OF_DAY,0)
		expectedTrainingDate.set(Calendar.MINUTE,0)
		
		assertEquals expectedTrainingDate.getTime() , training_update.trainingDate ;
		assert training_update.trainingDate.getHours() == 0;
		
		assert training_update.timeStart.getHours() == 6 ;
		assert training_update.timeStart.getMinutes() == 0 ;
		assert training_update.trainingDate.getTime() + 6*60*60*1000 == training_update.timeStart.getTime()
		
		assert training_update.timeEnd.getHours() == 10 ;
		assert training_update.timeEnd.getMinutes() == 0 ;
		assert training_update.trainingDate.getTime() + 10*60*60*1000 == training_update.timeEnd.getTime()
		
	}
	
	void testIndex() {
        controller.index()
        assert "/training/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.trainingInstanceList.size() == 1
        assert model.trainingInstanceTotal == 1
    }

	 void testCreate() {
       def model = controller.create()

       assert model.trainingInstance != null
    }

	 void testShow() {

        def training = new Training()
		setTrainingParams(training)

        assert training.save() != null

        params.id = training.id

        def model = controller.show()

        assert model.trainingInstance == training
    }

    void testEdit() {

        def training = new Training()
		setTrainingParams(training)

        assert training.save() != null

        params.id = training.id

        def model = controller.edit()

        assert model.trainingInstance == training
    }
	
	 void testDelete() {

        def training = new Training(params)
		setTrainingParams(training)

        assert training.save() != null
        assert Training.count() == 1

        params.id = training.id

        controller.delete()

        assert Training.count() == 0
        assert Training.get(training.id) == null
        assert response.redirectedUrl == '/training/list'
		
    }
	
	void testDeleteNotAllowed(){
		params["standardizedPatient"] = datasetup.standardizedPatient1
	    params["acceptedOsceDay"] = datasetup.osceDay1
	    params["acceptedTraining"] = datasetup.training1
	    def patientlnSemester = new PatientlnSemester(params)
        assert patientlnSemester.save() != null
	  
		params.id = datasetup.training1.id

        controller.delete()
		assert OsceDay.count() == 1
	}

	void testUpdateCancel(){
		params["id"] = 1
		controller.cancel()
		assert response.redirectedUrl == "/training/show/1"
	}
	
	void testCreateCancel(){
		controller.cancel()
		assert response.redirectedUrl == "/training/list"
	}
}
