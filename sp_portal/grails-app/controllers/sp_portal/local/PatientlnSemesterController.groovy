package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException

class PatientlnSemesterController extends sp_portal.MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsAdmin]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [patientlnSemesterInstanceList: PatientlnSemester.list(params), patientlnSemesterInstanceTotal: PatientlnSemester.count()]
    }

    def create() {
        [patientlnSemesterInstance: new PatientlnSemester(params)]
    }

    def save() {
        def patientlnSemesterInstance = new PatientlnSemester(params)
        if (!patientlnSemesterInstance.save(flush: true)) {
            render(view: "create", model: [patientlnSemesterInstance: patientlnSemesterInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), patientlnSemesterInstance.id])
        redirect(action: "show", id: patientlnSemesterInstance.id)
    }

    def show() {
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
        if (!patientlnSemesterInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        [patientlnSemesterInstance: patientlnSemesterInstance]
    }

    def edit() {
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
        if (!patientlnSemesterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        [patientlnSemesterInstance: patientlnSemesterInstance]
    }

    def update() {
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
        if (!patientlnSemesterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (patientlnSemesterInstance.version > version) {
                patientlnSemesterInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'patientlnSemester.label', default: 'PatientlnSemester')] as Object[],
                          "Another user has updated this PatientlnSemester while you were editing")
                render(view: "edit", model: [patientlnSemesterInstance: patientlnSemesterInstance])
                return
            }
        }

        patientlnSemesterInstance.properties = params

        if (!patientlnSemesterInstance.save(flush: true)) {
            render(view: "edit", model: [patientlnSemesterInstance: patientlnSemesterInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), patientlnSemesterInstance.id])
        redirect(action: "show", id: patientlnSemesterInstance.id)
    }

    def delete() {
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
        if (!patientlnSemesterInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        try {
            patientlnSemesterInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cancel(){
		def patientlnSemesterInstance = PatientlnSemester.get(params.id)
        if (!patientlnSemesterInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: patientlnSemesterInstance.id)
		}
		
	}
}
