package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(StandardizedPatientController)
@Mock(StandardizedPatient)
class StandardizedPatientControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/standardizedPatient/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.standardizedPatientInstanceList.size() == 0
        assert model.standardizedPatientInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.standardizedPatientInstance != null
    }

    void testSave() {
        controller.save()

        assert model.standardizedPatientInstance != null
        assert view == '/standardizedPatient/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/standardizedPatient/show/1'
        assert controller.flash.message != null
        assert StandardizedPatient.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/standardizedPatient/list'


        populateValidParams(params)
        def standardizedPatient = new StandardizedPatient(params)

        assert standardizedPatient.save() != null

        params.id = standardizedPatient.id

        def model = controller.show()

        assert model.standardizedPatientInstance == standardizedPatient
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/standardizedPatient/list'


        populateValidParams(params)
        def standardizedPatient = new StandardizedPatient(params)

        assert standardizedPatient.save() != null

        params.id = standardizedPatient.id

        def model = controller.edit()

        assert model.standardizedPatientInstance == standardizedPatient
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/standardizedPatient/list'

        response.reset()


        populateValidParams(params)
        def standardizedPatient = new StandardizedPatient(params)

        assert standardizedPatient.save() != null

        // test invalid parameters in update
        params.id = standardizedPatient.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/standardizedPatient/edit"
        assert model.standardizedPatientInstance != null

        standardizedPatient.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/standardizedPatient/show/$standardizedPatient.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        standardizedPatient.clearErrors()

        populateValidParams(params)
        params.id = standardizedPatient.id
        params.version = -1
        controller.update()

        assert view == "/standardizedPatient/edit"
        assert model.standardizedPatientInstance != null
        assert model.standardizedPatientInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/standardizedPatient/list'

        response.reset()

        populateValidParams(params)
        def standardizedPatient = new StandardizedPatient(params)

        assert standardizedPatient.save() != null
        assert StandardizedPatient.count() == 1

        params.id = standardizedPatient.id

        controller.delete()

        assert StandardizedPatient.count() == 0
        assert StandardizedPatient.get(standardizedPatient.id) == null
        assert response.redirectedUrl == '/standardizedPatient/list'
    }
}
