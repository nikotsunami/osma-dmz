package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

class TrainingController extends sp_portal.MainController {

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]

	private static final log = LogFactory.getLog(this)
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	 
    def index() {
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
		
		def semester;
		def trainingInstance
		if(params.semester){
			semester = Semester.get(params.semester);
			params.semester = semester;
			
			trainingInstance = new Training(params)
		}else{
			flash.message = message(code: 'default.null.message', args: [message(code: 'Semester.label', default: 'Semester')])
			 render(view: "create", model: [trainingInstance: trainingInstance])
			 return;
		}
		
		
		def isStartTimeRight = timeStartVerification(params)
		def isEndTimeRight = timeEndVerification(params)
		if(isStartTimeRight){
			setStartTime(trainingInstance)
		}
		if(isEndTimeRight){
			setEndTime(trainingInstance)
		}
		if(isStartTimeRight == false){
			flash.message = message(code: 'traning.start.error', args: [message(code: 'training.label', default: 'Training'), params.id])
			render(view: "create", model: [trainingInstance: trainingInstance])
            return
		}		
		
		if(isEndTimeRight == false){
			flash.message = message(code: 'traning.end.error', args: [message(code: 'training.label', default: 'Training'), params.id])
			render(view: "create", model: [trainingInstance: trainingInstance])
            return
		}
		
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
			
		def isStartTimeRight = timeStartVerification(params)
		def isEndTimeRight = timeEndVerification(params)
		if(isStartTimeRight){
			setStartTime(trainingInstance)
		}
		if(isEndTimeRight){
			setEndTime(trainingInstance)
		}
		if(isStartTimeRight == false){
			flash.message = message(code: 'traning.start.error', args: [message(code: 'training.label', default: 'Training'), params.id])
			render(view: "edit", model: [trainingInstance: trainingInstance])
            return
		}		
		
		if(isEndTimeRight == false){
			flash.message = message(code: 'traning.end.error', args: [message(code: 'training.label', default: 'Training'), params.id])
			render(view: "edit", model: [trainingInstance: trainingInstance])
            return
		}
		

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
		
		def semester;
		if(params.semester){
			semester = Semester.get(params.semester);
			params.semester = semester;
			
		}else{
			flash.message = message(code: 'default.null.message', args: [message(code: 'Semester.label', default: 'Semester')])
			 render(view: "edit", model: [trainingInstance: trainingInstance])
			 return;
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
	 * set timeStart of the training
	 **/
	def setStartTime(trainingInstance){
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
			log.debug( ">>In class TrainingController Method setStartTime(trainingInstance)->start this trainingInstance: " + sb.toString());
		} 
		Date trainingDate = trainingInstance.timeStart
		String startHourPrame = params.timeStartHour
		String startMinPrame = params.timeStartMin
		if(trainingDate !=null && startHourPrame != null && !startHourPrame.equals("") && startMinPrame != null && !startMinPrame.equals("")){
			
			Long startHour = Long.valueOf(startHourPrame)
			Long startMin = Long.valueOf(startMinPrame)
			Long timeStart = getTrainingDateWithNoHours(trainingDate)+startMin*60*1000+startHour*60*60*1000

			trainingInstance.timeStart = new Date(timeStart)
			trainingInstance.trainingDate = new Date(timeStart)
			
		}else{
			trainingInstance.timeStart = null
			trainingInstance.trainingDate = null
			
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
			log.debug( ">>In class TrainingController Method setStartTime(trainingInstance)->end this trainingInstance: " + sb.toString());
		} 
	}
	
	/**
	 * set timeEnd of the training
	 **/
	def setEndTime(trainingInstance){
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
			log.debug( ">>In class TrainingController Method setEndTime(trainingInstance)->start this trainingInstance: " + sb.toString());
		} 
		Date trainingDate = trainingInstance.timeEnd
		String endHourPrame = params.timeEndHour
		String endMinPrame = params.timeEndMin
		if(trainingDate !=null &&  endHourPrame != null && !endHourPrame.equals("") && endMinPrame != null && !endMinPrame.equals("")){
			Long endHour = Long.valueOf(endHourPrame)
			Long endMin = Long.valueOf(endMinPrame)
			Long timeEnd = getTrainingDateWithNoHours(trainingDate)+endMin*60*1000+endHour*60*60*1000
			if(log.isTraceEnabled()){
				log.trace(">> In class TrainingController Method setEndTime() timeEnd : "+timeEnd)
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
			log.debug( ">>In class TrainingController Method setEndTime(trainingInstance)->end this trainingInstance: " + sb.toString());
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
	
	/**
	 *start time and end time verification
	 **/
	def timeStartVerification(params){
		boolean isTimeStartRight = true

		
		def startHourStr = params.timeStartHour
		def startMinStr = params.timeStartMin
		//time start verification
		if(startHourStr&&!startHourStr.equals("")){
			if(startHourStr.startsWith("0")&&!startHourStr.equals("0")){
				startHourStr = startHourStr.substring(1)
			}
			try{
				def startHour = Long.valueOf(startHourStr)
				if(startHour<0 || startHour>23){
				isTimeStartRight = false
			}
			}catch (Exception e) {
				isTimeStartRight = false
				e.printStackTrace();
			}
			
			
		}
		if(startMinStr&&!startMinStr.equals("")){			
			if(startMinStr.startsWith("0")&&!startMinStr.equals("0")){
				startMinStr = startMinStr.substring(1)
			}
			try{
				def startMin = Long.valueOf(startMinStr)
				if(startMin<0 || startMin>59){
				isTimeStartRight = false
			}
			}catch (Exception e) {
				isTimeStartRight = false
				e.printStackTrace();
			}
			
			
		}
		return isTimeStartRight;
	}
	
	/**
	 *end time and end time verification
	 **/
	def timeEndVerification(params){
		boolean isTimeEndRight = true
		def endHourStr = params.timeEndHour
		def endMinStr = params.timeEndMin
		//time end verification
		if(endHourStr && !endHourStr.equals("")){
			if(endHourStr.startsWith("0") && !endHourStr.equals("0")){
				endHourStr = endHourStr.substring(1)
			}
			try{
				def endHour = Long.valueOf(endHourStr)
				if(endHour<0 || endHour>23){
				isTimeEndRight = false
			}
			}catch (Exception e) {
				isTimeEndRight = false
				e.printStackTrace();
			}
			
			
		}
		if(endMinStr && !endMinStr.equals("")){			
			if(endMinStr.startsWith("0") && !endMinStr.equals("0")){
				endMinStr = endMinStr.substring(1)
			}
			try{
				def endMin = Long.valueOf(endMinStr)
				if(endMin<0 || endMin>59){
				isTimeEndRight = false
			}
			}catch (Exception e) {
				isTimeEndRight = false
				e.printStackTrace();
			}
			
			
		}
		return isTimeEndRight;
	}
	
}
