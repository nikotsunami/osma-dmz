package sp_portal



import org.junit.*
import grails.test.mixin.*

@TestFor(LangSkillController)
@Mock(LangSkill)
class LangSkillControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/langSkill/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.langSkillInstanceList.size() == 0
        assert model.langSkillInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.langSkillInstance != null
    }

    void testSave() {
        controller.save()

        assert model.langSkillInstance != null
        assert view == '/langSkill/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/langSkill/show/1'
        assert controller.flash.message != null
        assert LangSkill.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/langSkill/list'


        populateValidParams(params)
        def langSkill = new LangSkill(params)

        assert langSkill.save() != null

        params.id = langSkill.id

        def model = controller.show()

        assert model.langSkillInstance == langSkill
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/langSkill/list'


        populateValidParams(params)
        def langSkill = new LangSkill(params)

        assert langSkill.save() != null

        params.id = langSkill.id

        def model = controller.edit()

        assert model.langSkillInstance == langSkill
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/langSkill/list'

        response.reset()


        populateValidParams(params)
        def langSkill = new LangSkill(params)

        assert langSkill.save() != null

        // test invalid parameters in update
        params.id = langSkill.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/langSkill/edit"
        assert model.langSkillInstance != null

        langSkill.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/langSkill/show/$langSkill.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        langSkill.clearErrors()

        populateValidParams(params)
        params.id = langSkill.id
        params.version = -1
        controller.update()

        assert view == "/langSkill/edit"
        assert model.langSkillInstance != null
        assert model.langSkillInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/langSkill/list'

        response.reset()

        populateValidParams(params)
        def langSkill = new LangSkill(params)

        assert langSkill.save() != null
        assert LangSkill.count() == 1

        params.id = langSkill.id

        controller.delete()

        assert LangSkill.count() == 0
        assert LangSkill.get(langSkill.id) == null
        assert response.redirectedUrl == '/langSkill/list'
    }
}
