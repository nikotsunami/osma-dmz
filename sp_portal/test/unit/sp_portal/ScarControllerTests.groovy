package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(ScarController)
@Mock(Scar)
class ScarControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scar/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scarInstanceList.size() == 0
        assert model.scarInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.scarInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scarInstance != null
        assert view == '/scar/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scar/show/1'
        assert controller.flash.message != null
        assert Scar.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scar/list'


        populateValidParams(params)
        def scar = new Scar(params)

        assert scar.save() != null

        params.id = scar.id

        def model = controller.show()

        assert model.scarInstance == scar
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scar/list'


        populateValidParams(params)
        def scar = new Scar(params)

        assert scar.save() != null

        params.id = scar.id

        def model = controller.edit()

        assert model.scarInstance == scar
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scar/list'

        response.reset()


        populateValidParams(params)
        def scar = new Scar(params)

        assert scar.save() != null

        // test invalid parameters in update
        params.id = scar.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scar/edit"
        assert model.scarInstance != null

        scar.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scar/show/$scar.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scar.clearErrors()

        populateValidParams(params)
        params.id = scar.id
        params.version = -1
        controller.update()

        assert view == "/scar/edit"
        assert model.scarInstance != null
        assert model.scarInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scar/list'

        response.reset()

        populateValidParams(params)
        def scar = new Scar(params)

        assert scar.save() != null
        assert Scar.count() == 1

        params.id = scar.id

        controller.delete()

        assert Scar.count() == 0
        assert Scar.get(scar.id) == null
        assert response.redirectedUrl == '/scar/list'
    }
}
