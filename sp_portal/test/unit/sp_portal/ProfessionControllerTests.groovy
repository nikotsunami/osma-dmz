package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(ProfessionController)
@Mock(Profession)
class ProfessionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/profession/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.professionInstanceList.size() == 0
        assert model.professionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.professionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.professionInstance != null
        assert view == '/profession/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/profession/show/1'
        assert controller.flash.message != null
        assert Profession.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/profession/list'


        populateValidParams(params)
        def profession = new Profession(params)

        assert profession.save() != null

        params.id = profession.id

        def model = controller.show()

        assert model.professionInstance == profession
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/profession/list'


        populateValidParams(params)
        def profession = new Profession(params)

        assert profession.save() != null

        params.id = profession.id

        def model = controller.edit()

        assert model.professionInstance == profession
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/profession/list'

        response.reset()


        populateValidParams(params)
        def profession = new Profession(params)

        assert profession.save() != null

        // test invalid parameters in update
        params.id = profession.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/profession/edit"
        assert model.professionInstance != null

        profession.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/profession/show/$profession.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        profession.clearErrors()

        populateValidParams(params)
        params.id = profession.id
        params.version = -1
        controller.update()

        assert view == "/profession/edit"
        assert model.professionInstance != null
        assert model.professionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/profession/list'

        response.reset()

        populateValidParams(params)
        def profession = new Profession(params)

        assert profession.save() != null
        assert Profession.count() == 1

        params.id = profession.id

        controller.delete()

        assert Profession.count() == 0
        assert Profession.get(profession.id) == null
        assert response.redirectedUrl == '/profession/list'
    }
}
