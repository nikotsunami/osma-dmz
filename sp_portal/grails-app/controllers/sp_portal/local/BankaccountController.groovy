package sp_portal.local
import sp_portal.User;
import org.springframework.dao.DataIntegrityViolationException

class BankaccountController extends sp_portal.MainController{
	def beforeInterceptor = [action:this.&isLoggedInAsUser]
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bankaccountInstanceList: Bankaccount.list(params), bankaccountInstanceTotal: Bankaccount.count()]
    }

    def create() {
        [bankaccountInstance: new Bankaccount(params)]
    }

    def save() {
        def bankaccountInstance = new Bankaccount(params)
        if (!bankaccountInstance.save(flush: true)) {
            render(view: "create", model: [bankaccountInstance: bankaccountInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), bankaccountInstance.id])
        redirect(action: "show", id: bankaccountInstance.id)
    }

    def show() {
        def bankaccountInstance = User.findById(session.user.id).standardizedPatient.bankaccount;
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
       	    return
        }
        [bankaccountInstance: bankaccountInstance]
    }

    def edit() {
        def bankaccountInstance = User.findById(session.user.id).standardizedPatient.bankaccount;
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
            redirect(action: "list")
            return
        }

        [bankaccountInstance: bankaccountInstance]
    }

    def update() {
        def bankaccountInstance = User.findById(session.user.id).standardizedPatient.bankaccount;
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (bankaccountInstance.version > version) {
                bankaccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bankaccount.label', default: 'Bankaccount')] as Object[],
                          "Another user has updated this Bankaccount while you were editing")
                render(view: "edit", model: [bankaccountInstance: bankaccountInstance])
                return
            }
        }

        bankaccountInstance.properties = params

        if (!bankaccountInstance.save(flush: true)) {
            render(view: "edit", model: [bankaccountInstance: bankaccountInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), bankaccountInstance.id])
        redirect(action: "show", id: bankaccountInstance.id)
    }

    def delete() {
        def bankaccountInstance = User.findById(session.user.id).standardizedPatient.bankaccount;
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
            redirect(action: "list")
            return
        }

        try {
            bankaccountInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bankaccount.label', default: 'Bankaccount'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
