package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(DescriptionController)
@Mock(Description)
class DescriptionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/description/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.descriptionInstanceList.size() == 0
        assert model.descriptionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.descriptionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.descriptionInstance != null
        assert view == '/description/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/description/show/1'
        assert controller.flash.message != null
        assert Description.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/description/list'


        populateValidParams(params)
        def description = new Description(params)

        assert description.save() != null

        params.id = description.id

        def model = controller.show()

        assert model.descriptionInstance == description
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/description/list'


        populateValidParams(params)
        def description = new Description(params)

        assert description.save() != null

        params.id = description.id

        def model = controller.edit()

        assert model.descriptionInstance == description
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/description/list'

        response.reset()


        populateValidParams(params)
        def description = new Description(params)

        assert description.save() != null

        // test invalid parameters in update
        params.id = description.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/description/edit"
        assert model.descriptionInstance != null

        description.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/description/show/$description.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        description.clearErrors()

        populateValidParams(params)
        params.id = description.id
        params.version = -1
        controller.update()

        assert view == "/description/edit"
        assert model.descriptionInstance != null
        assert model.descriptionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/description/list'

        response.reset()

        populateValidParams(params)
        def description = new Description(params)

        assert description.save() != null
        assert Description.count() == 1

        params.id = description.id

        controller.delete()

        assert Description.count() == 0
        assert Description.get(description.id) == null
        assert response.redirectedUrl == '/description/list'
    }
}
