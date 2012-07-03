package sp_portal

import org.springframework.dao.DataIntegrityViolationException

class PersonalDetailsController  extends MainController {

	  def beforeInterceptor = [action:this.&isLoggedInAsUser]

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [standardizedPatientInstanceList: StandardizedPatient.list(params), standardizedPatientInstanceTotal: StandardizedPatient.count()]
    }

    def create() {
        [standardizedPatientInstance: new local.StandardizedPatient(params)]
    }

    def save() {
        def standardizedPatientInstance = new local.StandardizedPatient(params)
        if (!standardizedPatientInstance.save(flush: true)) {
            render(view: "create", model: [standardizedPatientInstance: standardizedPatientInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), standardizedPatientInstance.id])
        redirect(action: "show", id: standardizedPatientInstance.id)
    }

    def show() {

        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
        if (standardizedPatientInstance){
            if (!standardizedPatientInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
                redirect(action: "list")
                return
            }
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def edit() {
        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def update() {
		println("params:"+params);
        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
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

}
