package sp_portal.local
import sp_portal.User;
import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class BankaccountController extends sp_portal.MainController{
    def beforeInterceptor = [action:this.&isLoggedInAsUser]
	private static final log = LogFactory.getLog(this)
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		log.info("index of bankaccount")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of bankaccount")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bankaccountInstanceList: Bankaccount.list(params), bankaccountInstanceTotal: Bankaccount.count()]
    }

    def create() {
		log.info("user create bankaccount")
        [bankaccountInstance: new Bankaccount(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class BankaccountController Method save() with params : "+params)
		}
        def bankaccountInstance = new Bankaccount(params)
        if (!bankaccountInstance.save(flush: true)) {
            render(view: "create", model: [bankaccountInstance: bankaccountInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), bankaccountInstance.id])
        redirect(action: "show", id: bankaccountInstance.id)
    }

    def show() {
		if(log.isTraceEnabled()){
			log.trace(">> In class BankaccountController Method show() with params : "+params)
		}
		def bankaccountInstance = null
		def user = User.findById(session.user.id)
		if(log.isDebugEnabled()){
			log.debug("find user : "+user)
		}
		def standardizedPatient = user.standardizedPatient
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatient : "+standardizedPatient)
		}
        bankaccountInstance = standardizedPatient.bankaccount;
		if(log.isDebugEnabled()){
			log.debug("get bankaccountInstance : "+bankaccountInstance)
		}
        if (!bankaccountInstance) {

            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
            return
        }
        [bankaccountInstance: bankaccountInstance]
    }

    def edit() {
		if(log.isTraceEnabled()){
			log.trace(">> In class BankaccountController Method edit() with params : "+params)
		}
		def bankaccountInstance = null
		def user = User.findById(session.user.id)
		if(log.isDebugEnabled()){
			log.debug("find user : "+user)
		}
		def standardizedPatient = user.standardizedPatient
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatient : "+standardizedPatient)
		}
		bankaccountInstance = standardizedPatient.bankaccount;
		if(log.isDebugEnabled()){
			log.debug("get bankaccountInstance : "+bankaccountInstance)
		}
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
			redirect(action: "show")
            return
        }
        [bankaccountInstance: bankaccountInstance]
    }

    def update() {
        if(log.isTraceEnabled()){
			log.trace(">> In class BankaccountController Method update() with params : "+params)
		}
		def bankaccountInstance = null
		def user = User.findById(session.user.id)
		if(log.isDebugEnabled()){
			log.debug("find user : "+user)
		}
		def standardizedPatient = user.standardizedPatient
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatient : "+standardizedPatient)
		}
		bankaccountInstance = standardizedPatient.bankaccount;
		if(log.isDebugEnabled()){
			log.debug("get bankaccountInstance : "+bankaccountInstance)
		}
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (bankaccountInstance.version > version) {
                bankaccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bankaccount.label', default: 'Bankkonto')] as Object[],
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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), bankaccountInstance.id])
        redirect(action: "show", id: bankaccountInstance.id)
    }

    def delete() {
        if(log.isTraceEnabled()){
			log.trace(">> In class BankaccountController Method update() with params : "+params)
		}

		def bankaccountInstance = null
		def user = User.findById(session.user.id)
		if(log.isDebugEnabled()){
			log.debug("find user : "+user)
		}
		def standardizedPatient = user.standardizedPatient
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatient : "+standardizedPatient)
		}
		bankaccountInstance = standardizedPatient.bankaccount;
		if(log.isDebugEnabled()){
			log.debug("get bankaccountInstance : "+bankaccountInstance)
		}
        if (!bankaccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            bankaccountInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bankaccount.label', default: 'Bankkonto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
