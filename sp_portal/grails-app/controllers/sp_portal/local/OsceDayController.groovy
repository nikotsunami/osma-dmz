package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class OsceDayController extends sp_portal.MainController {

  def beforeInterceptor = [action:this.&isLoggedInAsAdmin]
  
  private static final log = LogFactory.getLog(this)
	
  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		log.info("index of osceDay")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of osceDay")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [osceDayInstanceList: OsceDay.list(params), osceDayInstanceTotal: OsceDay.count()]
    }

    def create() {
		log.info("user create osceDay")
        [osceDayInstance: new OsceDay(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceDayController Method save() with params : "+params)
		}
        def osceDayInstance = new OsceDay(params)
		if(log.isDebugEnabled()){
			log.debug("new OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance.save(flush: true)) {
            render(view: "create", model: [osceDayInstance: osceDayInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), osceDayInstance.id])
        redirect(action: "show", id: osceDayInstance.id)
    }

    def show() {
		log.info("user show training")
        def osceDayInstance = OsceDay.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        [osceDayInstance: osceDayInstance]
    }

    def edit() {
		log.info("user edit training")
        def osceDayInstance = OsceDay.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        [osceDayInstance: osceDayInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceDayController Method update() with params : "+params)
		}
        def osceDayInstance = OsceDay.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }
		//if there has standardized patient has selected this osceDay,can not update
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
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceDayController Method delete() with params : "+params)
		}
        def osceDayInstance = OsceDay.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])
            redirect(action: "list")
            return
        }

        try {	
			//if there has standardized patient has selected this osceDay,can not delete
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
		log.info("user edit training")
		def osceDayInstance = OsceDay.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get OsceDay : "+osceDayInstance)
		}
        if (!osceDayInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: osceDayInstance.id)
		}
		
	}
	
	/**
	 * get standardizedPatients has selected this osceDay
	 **/
	def getStandardizedPatientsStr(osceDayInstanceId){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceDayController Method getStandardizedPatientsStr entered osceDayInstanceId : "+osceDayInstanceId)
		}
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
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceDayController Method getStandardizedPatientsStr return standardizedPatients : "+standardizedPatients)
		}
		return standardizedPatients;
	}
}
