package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

class TrainingController extends sp_portal.MainController {

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]

	private static final log = LogFactory.getLog(this)
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	 
    def index() {
	println("" + this.class.getCanonicalName())
		log.info("index of training")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of training")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [trainingInstanceList: Training.list(params), trainingInstanceTotal: Training.count()]
    }

    def create() {
		log.info("user create training")
        [trainingInstance: new Training(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method save() with params : "+params)
		}
		//log("params : "+params)
        def trainingInstance = new Training(params)
		setStartAndEndTime(trainingInstance)
		
		
        if (!trainingInstance.save(flush: true)) {
            render(view: "create", model: [trainingInstance: trainingInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'training.label', default: 'Training'), trainingInstance.id])
        redirect(action: "show", id: trainingInstance.id)
    }

    def show() {
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method show() with params : "+params)
		}
        def trainingInstance = Training.get(params.id)
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n trainingInstance.trainingDate: ");
			sb.append(trainingInstance?.trainingDate);
			sb.append( "\n trainingInstance.name: ");
			sb.append(trainingInstance?.name);
			sb.append( "\n trainingInstance.timeStart: ");
			sb.append(trainingInstance?.timeStart);
			sb.append( "\n trainingInstance.timeEnd: ");
			sb.append(trainingInstance?.timeEnd);
			log.debug( "get trainingInstance: " + sb.toString());
		} 
        if (!trainingInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        [trainingInstance: trainingInstance]
    }

    def edit() {
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method edit() with params : "+params)
		}
        def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        [trainingInstance: trainingInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method update() with params : "+params)
		}

		//log("params : "+params)
		
        def trainingInstance = Training.get(params.id)
		
        if (!trainingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }
		
	
		def standardizedPatients = getStandardizedPatientsStr(trainingInstance.id)
		//if there has standardized patient has selected this training,can not update	
		if(!standardizedPatients.equals('')){
			flash.message = message(code: 'default.training.is.accepted', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])+standardizedPatients
			redirect(action: "show", id: params.id)
			return
		}
			
		setStartAndEndTime(trainingInstance)
		

        if (params.version) {
            def version = params.version.toLong()
            if (trainingInstance.version > version) {
                trainingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'training.label', default: 'Training')] as Object[],
                          "Another user has updated this Training while you were editing")
                render(view: "edit", model: [trainingInstance: trainingInstance])
                return
            }
        }

        trainingInstance.properties = params

        if (!trainingInstance.save(flush: true)) {
            render(view: "edit", model: [trainingInstance: trainingInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'training.label', default: 'Training'), trainingInstance.id])
        redirect(action: "show", id: trainingInstance.id)
    }

    def delete() {
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method delete() with params : "+params)
		}
        def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        try {
		
			def standardizedPatients = getStandardizedPatientsStr(trainingInstance.id)			
			//if there has standardized patient has selected this training,can not delete
			if(!standardizedPatients.equals('')){
				flash.message = message(code: 'default.training.is.accepted' , args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])+standardizedPatients
				redirect(action: "show", id: params.id)
				return
			}
			
            trainingInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cancel(){
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method cancel() with params : "+params)
		}
		def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: trainingInstance.id)
		}
		
	}
	
	/**
	 * set timeStart and timeEnd of the training
	 **/
	def setStartAndEndTime(trainingInstance){
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n trainingInstance.trainingDate: ");
			sb.append(trainingInstance?.trainingDate);
			sb.append( "\n trainingInstance.name: ");
			sb.append(trainingInstance?.name);
			sb.append( "\n trainingInstance.timeStart: ");
			sb.append(trainingInstance?.timeStart);
			sb.append( "\n trainingInstance.timeEnd: ");
			sb.append(trainingInstance?.timeEnd);
			log.debug( ">>In class TrainingController Method setStartAndEndTime(trainingInstance)->start this trainingInstance: " + sb.toString());
		} 
		Date trainingDate = trainingInstance.trainingDate
		String endHourPrame = params.timeEndHour
		String endMinPrame = params.timeEndMin
		String startHourPrame = params.timeStartHour
		String startMinPrame = params.timeStartMin
		
		
		if(trainingDate !=null && startHourPrame != null && !startHourPrame.equals("") && startMinPrame != null && !startMinPrame.equals("")){
			
			Long startHour = Long.valueOf(startHourPrame)
			Long startMin = Long.valueOf(startMinPrame)
			Long timeStart = getTrainingDateWithNoHours(trainingDate)+startMin*60*1000+startHour*60*60*1000

			trainingInstance.timeStart = new Date(timeStart)
			
		}else{
			trainingInstance.timeStart = null
			
		}

		if(trainingDate !=null &&  endHourPrame != null && !endHourPrame.equals("") && endMinPrame != null && !endMinPrame.equals("")){
			Long endHour = Long.valueOf(endHourPrame)
			Long endMin = Long.valueOf(endMinPrame)
			Long timeEnd = getTrainingDateWithNoHours(trainingDate)+endMin*60*1000+endHour*60*60*1000
			if(log.isTraceEnabled()){
				log.trace(">> In class TrainingController Method setStartAndEndTime() timeEnd : "+timeEnd)
			}
			trainingInstance.timeEnd = new Date(timeEnd)	
		}else{
			trainingInstance.timeEnd = null
		}
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n trainingInstance.trainingDate: ");
			sb.append(trainingInstance?.trainingDate);
			sb.append( "\n trainingInstance.name: ");
			sb.append(trainingInstance?.name);
			sb.append( "\n trainingInstance.timeStart: ");
			sb.append(trainingInstance?.timeStart);
			sb.append( "\n trainingInstance.timeEnd: ");
			sb.append(trainingInstance?.timeEnd);
			log.debug( ">>In class TrainingController Method setStartAndEndTime(trainingInstance)->end this trainingInstance: " + sb.toString());
		} 
	}
	
	/**
	 * get 0:00 a.m of trainingDate
	 **/
	def getTrainingDateWithNoHours(trainingDate){
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method getTrainingDateWithNoHours entered trainingDate : "+trainingDate)
		}
		Calendar calendar  = Calendar.getInstance();
		calendar.setTime(trainingDate);
		Long timeInMillis = calendar.getTimeInMillis();
		Long hourOfDay = (long)calendar.get(Calendar.HOUR_OF_DAY);
		Long minute = (long)calendar.get(Calendar.MINUTE);
		Long second = (long)calendar.get(Calendar.SECOND);
		Long dateTime = timeInMillis-hourOfDay*60*60*1000-minute*60*1000-second*1000 

		if(log.isTraceEnabled()){
			log.trace("<< In class TrainingController Method getTrainingDateWithNoHours return dateTime : "+dateTime)
		}
		return dateTime
	}
	
	/**
	 * get standardizedPatients has selected this training
	 **/
	def getStandardizedPatientsStr(trainingInstanceId){
		if(log.isTraceEnabled()){
			log.trace(">> In class TrainingController Method getStandardizedPatientsStr entered trainingInstanceId : "+trainingInstanceId)
		}
		def allPatientlnSemesters = PatientlnSemester.list()
		def standardizedPatients = ''
		for(PatientlnSemester patientlnSemester : allPatientlnSemesters){
			for(Training training : patientlnSemester.acceptedTraining){
				if(training.id == trainingInstanceId){
					if(!standardizedPatients.equals('')){
						standardizedPatients = standardizedPatients + ','
					}
					standardizedPatients = standardizedPatients + patientlnSemester.standardizedPatient.email 
				
				}
			}
		}
		if(log.isTraceEnabled()){
			log.trace("<< In class TrainingController Method getStandardizedPatientsStr return standardizedPatients : "+standardizedPatients)
		}
		return standardizedPatients;
	}
}
