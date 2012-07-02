package sp_portal.local



import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.TestFor
import sp_portal.DataSetupHelper;

@TestFor(BankaccountController)
@Mock([Bankaccount,sp_portal.User,sp_portal.Role,StandardizedPatient])
class BankaccountControllerTests  {

    def datasetup = new DataSetupHelper()

    @Before
    void setUp(){
        datasetup.getDataSetA()

        session.user = datasetup.normalUser;
    }

    @After
    void tearDown(){

      datasetup = null;

    }



    void setupData(){
        def user = new sp_portal.User();
        user.save()

        def bankaccount = new Bankaccount();
        def standardizedPatient = new StandardizedPatient();

        user.standardizedPatient = standardizedPatient;

        user.standardizedPatient.bankaccount = bankaccount;

    }

    void testSomething() {
        assert true
    }

    def populateValidParams(params) {
      assert params != null

        params["bic"] = 'jfskhfsdhj'
        params["iban"] = '132654987454654'
        params["bankName"] = 'ICBC'
        params["city"] = 'Wuhu'
        params["country"] = 'China'
        params["ownerName"] = 'owner'
        params["postalCode"] = '123456789'
        params["origId"] = 5

    }

    void testIndex() {
        controller.index()
        assert "/bankaccount/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bankaccountInstanceList.size() == 1
        assert model.bankaccountInstanceTotal == 1
    }

    void testCreate() {
       def model = controller.create()

       assert model.bankaccountInstance != null
    }

    /**
     * save a valid bank account
     */
    void testSaveValid() {

        populateValidParams(params)

        controller.save()

        assert response.redirectedUrl == '/bankaccount/show/2'
        assert flash.message != null
        assert Bankaccount.count() == 2
    }

    /**
     * save an invalid bank account
     */
    void testSaveInvalid() {
        params["bic"] = 'A2345678901234567890123456789012345678901'
        params["iban"] = 'B2345678901234567890123456789012345678901'
        params["bankName"] = 'C2345678901234567890123456789012345678901'
        params["city"] = 'D2345678901234567890123456789012345678901'
        params["country"] = 'E2345678901234567890123456789012345678901'
        params["ownerName"] = 'F2345678901234567890123456789012345678901'
        params["postalCode"] = '123456789'
        params["origId"] = 5

        controller.save()


        assert view == "/bankaccount/create"
        assert model.bankaccountInstance.bic == 'A2345678901234567890123456789012345678901'
        assert flash.message == null
        assert Bankaccount.count() == 1

    }


    void testShow() {

        def retModel = controller.show()

        assert retModel.bankaccountInstance == session.user.standardizedPatient.bankaccount
        assertNotNull retModel.bankaccountInstance

    }

    void testEdit() {
        def retModel = controller.edit()

        assert flash.message == null
        assert retModel.bankaccountInstance == session.user.standardizedPatient.bankaccount
        assertNotNull retModel.bankaccountInstance


        populateValidParams(params)
        def bankaccount = new Bankaccount(params)

        assert bankaccount.save() != null

        params.id = bankaccount.id

        def model = controller.edit()

        assert model.bankaccountInstance == session.user.standardizedPatient.bankaccount
    }
/*
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
