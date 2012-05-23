package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(AnamnesisCheckController)
@Mock(AnamnesisCheck)
class AnamnesisCheckControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/anamnesisCheck/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.anamnesisCheckInstanceList.size() == 0
        assert model.anamnesisCheckInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.anamnesisCheckInstance != null
    }

    void testSave() {
        controller.save()

        assert model.anamnesisCheckInstance != null
        assert view == '/anamnesisCheck/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/anamnesisCheck/show/1'
        assert controller.flash.message != null
        assert AnamnesisCheck.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisCheck/list'


        populateValidParams(params)
        def anamnesisCheck = new AnamnesisCheck(params)

        assert anamnesisCheck.save() != null

        params.id = anamnesisCheck.id

        def model = controller.show()

        assert model.anamnesisCheckInstance == anamnesisCheck
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisCheck/list'


        populateValidParams(params)
        def anamnesisCheck = new AnamnesisCheck(params)

        assert anamnesisCheck.save() != null

        params.id = anamnesisCheck.id

        def model = controller.edit()

        assert model.anamnesisCheckInstance == anamnesisCheck
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisCheck/list'

        response.reset()


        populateValidParams(params)
        def anamnesisCheck = new AnamnesisCheck(params)

        assert anamnesisCheck.save() != null

        // test invalid parameters in update
        params.id = anamnesisCheck.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/anamnesisCheck/edit"
        assert model.anamnesisCheckInstance != null

        anamnesisCheck.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/anamnesisCheck/show/$anamnesisCheck.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        anamnesisCheck.clearErrors()

        populateValidParams(params)
        params.id = anamnesisCheck.id
        params.version = -1
        controller.update()

        assert view == "/anamnesisCheck/edit"
        assert model.anamnesisCheckInstance != null
        assert model.anamnesisCheckInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/anamnesisCheck/list'

        response.reset()

        populateValidParams(params)
        def anamnesisCheck = new AnamnesisCheck(params)

        assert anamnesisCheck.save() != null
        assert AnamnesisCheck.count() == 1

        params.id = anamnesisCheck.id

        controller.delete()

        assert AnamnesisCheck.count() == 0
        assert AnamnesisCheck.get(anamnesisCheck.id) == null
        assert response.redirectedUrl == '/anamnesisCheck/list'
    }
}
