package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class StandardizedPatientController  extends sp_portal.MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsUser]
	private static final log = LogFactory.getLog(this)
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		log.info("index of standardizedPatient")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of standardizedPatient")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [standardizedPatientInstanceList: StandardizedPatient.list(params), standardizedPatientInstanceTotal: StandardizedPatient.count()]
    }

    def create() {
		log.info("user create standardizedPatient")
        [standardizedPatientInstance: new StandardizedPatient(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class StandardizedPatientController Method save() with params : "+params)
		}
        def standardizedPatientInstance = new StandardizedPatient(params)
		if(log.isDebugEnabled()){
			log.debug("new standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance.save(flush: true)) {
            render(view: "create", model: [standardizedPatientInstance: standardizedPatientInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), standardizedPatientInstance.id])
        redirect(action: "show", id: standardizedPatientInstance.id)
    }

    def show() {
		log.info("user show standardizedPatient")
        def standardizedPatientInstance = session.user.standardizedPatient;
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def edit() {
		log.info("user edit standardizedPatient")
        def standardizedPatientInstance = StandardizedPatient.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class StandardizedPatientController Method save() with params : "+params)
		}
        def standardizedPatientInstance = StandardizedPatient.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (standardizedPatientInstance.version > version) {
                standardizedPatientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'standardizedPatient.label', default: 'StandardizedPatient')] as Object[],
                          "Another user has updated this StandardizedPatient while you were editing")
                render(view: "edit", model: [standardizedPatientInstance: standardizedPatientInstance])
                return
            }
        }

        standardizedPatientInstance.properties = params

        if (!standardizedPatientInstance.save(flush: true)) {
            render(view: "edit", model: [standardizedPatientInstance: standardizedPatientInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), standardizedPatientInstance.id])
        redirect(action: "show", id: standardizedPatientInstance.id)
    }

    def delete() {
		if(log.isTraceEnabled()){
			log.trace(">> In class StandardizedPatientController Method delete() with params : "+params)
		}
        def standardizedPatientInstance = StandardizedPatient.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        try {
            standardizedPatientInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cancel(){
		log.info("user cancel StandardizedPatient")
		def standardizedPatientInstance = StandardizedPatient.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: standardizedPatientInstance.id)
		}
		
	}
}
