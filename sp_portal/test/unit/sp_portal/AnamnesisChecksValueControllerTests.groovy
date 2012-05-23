package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(AnamnesisChecksValueController)
@Mock(AnamnesisChecksValue)
class AnamnesisChecksValueControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/anamnesisChecksValue/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.anamnesisChecksValueInstanceList.size() == 0
        assert model.anamnesisChecksValueInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.anamnesisChecksValueInstance != null
    }

    void testSave() {
        controller.save()

        assert model.anamnesisChecksValueInstance != null
        assert view == '/anamnesisChecksValue/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/anamnesisChecksValue/show/1'
        assert controller.flash.message != null
        assert AnamnesisChecksValue.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisChecksValue/list'


        populateValidParams(params)
        def anamnesisChecksValue = new AnamnesisChecksValue(params)

        assert anamnesisChecksValue.save() != null

        params.id = anamnesisChecksValue.id

        def model = controller.show()

        assert model.anamnesisChecksValueInstance == anamnesisChecksValue
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisChecksValue/list'


        populateValidParams(params)
        def anamnesisChecksValue = new AnamnesisChecksValue(params)

        assert anamnesisChecksValue.save() != null

        params.id = anamnesisChecksValue.id

        def model = controller.edit()

        assert model.anamnesisChecksValueInstance == anamnesisChecksValue
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisChecksValue/list'

        response.reset()


        populateValidParams(params)
        def anamnesisChecksValue = new AnamnesisChecksValue(params)

        assert anamnesisChecksValue.save() != null

        // test invalid parameters in update
        params.id = anamnesisChecksValue.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/anamnesisChecksValue/edit"
        assert model.anamnesisChecksValueInstance != null

        anamnesisChecksValue.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/anamnesisChecksValue/show/$anamnesisChecksValue.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        anamnesisChecksValue.clearErrors()

        populateValidParams(params)
        params.id = anamnesisChecksValue.id
        params.version = -1
        controller.update()

        assert view == "/anamnesisChecksValue/edit"
        assert model.anamnesisChecksValueInstance != null
        assert model.anamnesisChecksValueInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisChecksValue/list'

        response.reset()

        populateValidParams(params)
        def anamnesisChecksValue = new AnamnesisChecksValue(params)

        assert anamnesisChecksValue.save() != null
        assert AnamnesisChecksValue.count() == 1

        params.id = anamnesisChecksValue.id

        controller.delete()

        assert AnamnesisChecksValue.count() == 0
        assert AnamnesisChecksValue.get(anamnesisChecksValue.id) == null
        assert response.redirectedUrl == '/anamnesisChecksValue/list'
    }
}
