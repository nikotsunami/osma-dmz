package sp_portal.local



import org.junit.*
import grails.test.mixin.*

@TestFor(AnamnesisFormController)
@Mock(AnamnesisForm)
class AnamnesisFormControllerTests {
    void testSomething() {
        assert true
    }
/*

    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/anamnesisForm/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.anamnesisFormInstanceList.size() == 0
        assert model.anamnesisFormInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.anamnesisFormInstance != null
    }

    void testSave() {
        controller.save()

        assert model.anamnesisFormInstance != null
        assert view == '/anamnesisForm/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/anamnesisForm/show/1'
        assert controller.flash.message != null
        assert AnamnesisForm.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisForm/list'


        populateValidParams(params)
        def anamnesisForm = new AnamnesisForm(params)

        assert anamnesisForm.save() != null

        params.id = anamnesisForm.id

        def model = controller.show()

        assert model.anamnesisFormInstance == anamnesisForm
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisForm/list'


        populateValidParams(params)
        def anamnesisForm = new AnamnesisForm(params)

        assert anamnesisForm.save() != null

        params.id = anamnesisForm.id

        def model = controller.edit()

        assert model.anamnesisFormInstance == anamnesisForm
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisForm/list'

        response.reset()


        populateValidParams(params)
        def anamnesisForm = new AnamnesisForm(params)

        assert anamnesisForm.save() != null

        // test invalid parameters in update
        params.id = anamnesisForm.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/anamnesisForm/edit"
        assert model.anamnesisFormInstance != null

        anamnesisForm.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/anamnesisForm/show/$anamnesisForm.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        anamnesisForm.clearErrors()

        populateValidParams(params)
        params.id = anamnesisForm.id
        params.version = -1
        controller.update()

        assert view == "/anamnesisForm/edit"
        assert model.anamnesisFormInstance != null
        assert model.anamnesisFormInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisForm/list'

        response.reset()

        populateValidParams(params)
        def anamnesisForm = new AnamnesisForm(params)

        assert anamnesisForm.save() != null
        assert AnamnesisForm.count() == 1

        params.id = anamnesisForm.id

        controller.delete()

        assert AnamnesisForm.count() == 0
        assert AnamnesisForm.get(anamnesisForm.id) == null
        assert response.redirectedUrl == '/anamnesisForm/list'
    }
   */
}
