package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException

class OsceDayController extends sp_portal.MainController {

  def beforeInterceptor = [action:this.&isLoggedInAsAdmin]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [osceDayInstanceList: OsceDay.list(params), osceDayInstanceTotal: OsceDay.count()]
    }

    def create() {
        [osceDayInstance: new OsceDay(params)]
    }

    def save() {
        def osceDayInstance = new OsceDay(params)
        if (!osceDayInstance.save(flush: true)) {
            render(view: "create", model: [osceDayInstance: osceDayInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), osceDayInstance.id])
        redirect(action: "show", id: osceDayInstance.id)
    }

    def show() {
        def osceDayInstance = OsceDay.get(params.id)
        if (!osceDayInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        [osceDayInstance: osceDayInstance]
    }

    def edit() {
        def osceDayInstance = OsceDay.get(params.id)
        if (!osceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        [osceDayInstance: osceDayInstance]
    }

    def update() {
        def osceDayInstance = OsceDay.get(params.id)
        if (!osceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }
		def standardizedPatients = getStandardizedPatientsStr(osceDayInstance.id)
		if(!standardizedPatients.equals('')){
			flash.message = message(code: 'default.osceday.is.accepted' , args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id]) + standardizedPatients
			redirect(action: "show", id: params.id)
			return
		}
        if (params.version) {
            def version = params.version.toLong()
            if (osceDayInstance.version > version) {
                osceDayInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'osceDay.label', default: 'OsceDay')] as Object[],
                          "Another user has updated this OsceDay while you were editing")
                render(view: "edit", model: [osceDayInstance: osceDayInstance])
                return
            }
        }

        osceDayInstance.properties = params

        if (!osceDayInstance.save(flush: true)) {
            render(view: "edit", model: [osceDayInstance: osceDayInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), osceDayInstance.id])
        redirect(action: "show", id: osceDayInstance.id)
    }

    def delete() {
        def osceDayInstance = OsceDay.get(params.id)
        if (!osceDayInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        try {			
			def standardizedPatients = getStandardizedPatientsStr(osceDayInstance.id)
			if(!standardizedPatients.equals('')){
				flash.message = message(code: 'default.osceday.is.accepted' , args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id]) + standardizedPatients
				redirect(action: "show", id: params.id)
				return
			}
            osceDayInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cancel(){
		def osceDayInstance = OsceDay.get(params.id)
        if (!osceDayInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: osceDayInstance.id)
		}
		
	}
	
	def getStandardizedPatientsStr(osceDayInstanceId){
		def allPatientlnSemesters = PatientlnSemester.list()
		def standardizedPatients = ''
			for(PatientlnSemester patientlnSemester : allPatientlnSemesters){
				for(OsceDay osceDay : patientlnSemester.acceptedOsceDay){
					if(osceDay.id == osceDayInstanceId){
						if(!standardizedPatients.equals('')){
							standardizedPatients = standardizedPatients + ','
						}
						standardizedPatients = standardizedPatients + patientlnSemester.standardizedPatient.email 
						
					}
				}
			}
		return standardizedPatients;
	}
}
