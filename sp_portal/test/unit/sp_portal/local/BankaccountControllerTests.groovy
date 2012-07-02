package sp_portal.local



import org.junit.*
import grails.test.mixin.*

@TestFor(BankaccountController)
@Mock(Bankaccount)
class BankaccountControllerTests {
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
        assert "/bankaccount/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bankaccountInstanceList.size() == 0
        assert model.bankaccountInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.bankaccountInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bankaccountInstance != null
        assert view == '/bankaccount/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bankaccount/show/1'
        assert controller.flash.message != null
        assert Bankaccount.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bankaccount/list'


        populateValidParams(params)
        def bankaccount = new Bankaccount(params)

        assert bankaccount.save() != null

        params.id = bankaccount.id

        def model = controller.show()

        assert model.bankaccountInstance == bankaccount
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bankaccount/list'


        populateValidParams(params)
        def bankaccount = new Bankaccount(params)

        assert bankaccount.save() != null

        params.id = bankaccount.id

        def model = controller.edit()

        assert model.bankaccountInstance == bankaccount
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bankaccount/list'

        response.reset()


        populateValidParams(params)
        def bankaccount = new Bankaccount(params)

        assert bankaccount.save() != null

        // test invalid parameters in update
        params.id = bankaccount.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bankaccount/edit"
        assert model.bankaccountInstance != null

        bankaccount.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bankaccount/show/$bankaccount.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bankaccount.clearErrors()

        populateValidParams(params)
        params.id = bankaccount.id
        params.version = -1
        controller.update()

        assert view == "/bankaccount/edit"
        assert model.bankaccountInstance != null
        assert model.bankaccountInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bankaccount/list'

        response.reset()

        populateValidParams(params)
        def bankaccount = new Bankaccount(params)

        assert bankaccount.save() != null
        assert Bankaccount.count() == 1

        params.id = bankaccount.id

        controller.delete()

        assert Bankaccount.count() == 0
        assert Bankaccount.get(bankaccount.id) == null
        assert response.redirectedUrl == '/bankaccount/list'
    }

*/
}
