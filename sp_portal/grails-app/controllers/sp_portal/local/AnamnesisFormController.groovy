package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class AnamnesisFormController extends sp_portal.MainController{
	def beforeInterceptor = [action:this.&isLoggedInAsUser]
	private static final log = LogFactory.getLog(this)
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		log.info("index of anamnesisForm")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of anamnesisForm")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [anamnesisFormInstanceList: AnamnesisForm.list(params), anamnesisFormInstanceTotal: AnamnesisForm.count()]
    }

    def create() {
		log.info("user create AnamnesisForm")
        [anamnesisFormInstance: new AnamnesisForm(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class AnamnesisFormController Method save() with params : "+params)
		}
        def anamnesisFormInstance = new AnamnesisForm(params)
        if (!anamnesisFormInstance.save(flush: true)) {
            render(view: "create", model: [anamnesisFormInstance: anamnesisFormInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), anamnesisFormInstance.id])
        redirect(action: "show", id: anamnesisFormInstance.id)
    }

    def show() {
		log.info("user show AnamnesisForm")
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        [anamnesisFormInstance: anamnesisFormInstance]
    }

    def edit() {
		log.info("user edit AnamnesisForm")
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        [anamnesisFormInstance: anamnesisFormInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class AnamnesisFormController Method update() with params : "+params)
		}
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (anamnesisFormInstance.version > version) {
                anamnesisFormInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'anamnesisForm.label', default: 'AnamnesisForm')] as Object[],
                          "Another user has updated this AnamnesisForm while you were editing")
                render(view: "edit", model: [anamnesisFormInstance: anamnesisFormInstance])
                return
            }
        }

        anamnesisFormInstance.properties = params

        if (!anamnesisFormInstance.save(flush: true)) {
            render(view: "edit", model: [anamnesisFormInstance: anamnesisFormInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), anamnesisFormInstance.id])
        redirect(action: "show", id: anamnesisFormInstance.id)
    }

    def delete() {
		if(log.isTraceEnabled()){
			log.trace(">> In class AnamnesisFormController Method delete() with params : "+params)
		}
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        try {
            anamnesisFormInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
