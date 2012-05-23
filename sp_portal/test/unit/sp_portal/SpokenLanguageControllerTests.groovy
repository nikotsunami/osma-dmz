package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(SpokenLanguageController)
@Mock(SpokenLanguage)
class SpokenLanguageControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/spokenLanguage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.spokenLanguageInstanceList.size() == 0
        assert model.spokenLanguageInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.spokenLanguageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.spokenLanguageInstance != null
        assert view == '/spokenLanguage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/spokenLanguage/show/1'
        assert controller.flash.message != null
        assert SpokenLanguage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/spokenLanguage/list'


        populateValidParams(params)
        def spokenLanguage = new SpokenLanguage(params)

        assert spokenLanguage.save() != null

        params.id = spokenLanguage.id

        def model = controller.show()

        assert model.spokenLanguageInstance == spokenLanguage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/spokenLanguage/list'


        populateValidParams(params)
        def spokenLanguage = new SpokenLanguage(params)

        assert spokenLanguage.save() != null

        params.id = spokenLanguage.id

        def model = controller.edit()

        assert model.spokenLanguageInstance == spokenLanguage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/spokenLanguage/list'

        response.reset()


        populateValidParams(params)
        def spokenLanguage = new SpokenLanguage(params)

        assert spokenLanguage.save() != null

        // test invalid parameters in update
        params.id = spokenLanguage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/spokenLanguage/edit"
        assert model.spokenLanguageInstance != null

        spokenLanguage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/spokenLanguage/show/$spokenLanguage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        spokenLanguage.clearErrors()

        populateValidParams(params)
        params.id = spokenLanguage.id
        params.version = -1
        controller.update()

        assert view == "/spokenLanguage/edit"
        assert model.spokenLanguageInstance != null
        assert model.spokenLanguageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/spokenLanguage/list'

        response.reset()

        populateValidParams(params)
        def spokenLanguage = new SpokenLanguage(params)

        assert spokenLanguage.save() != null
        assert SpokenLanguage.count() == 1

        params.id = spokenLanguage.id

        controller.delete()

        assert SpokenLanguage.count() == 0
        assert SpokenLanguage.get(spokenLanguage.id) == null
        assert response.redirectedUrl == '/spokenLanguage/list'
    }
}
