package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException

class TrainingController extends sp_portal.MainController {

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]


    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [trainingInstanceList: Training.list(params), trainingInstanceTotal: Training.count()]
    }

    def create() {
	
        [trainingInstance: new Training(params)]
    }

    def save() {
	
        def trainingInstance = new Training(params)
		String trainingDateParam = params.trainingDate
		setStartAndEndTime(trainingInstance)
		
        if (!trainingInstance.save(flush: true)) {
            render(view: "create", model: [trainingInstance: trainingInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'training.label', default: 'Training'), trainingInstance.id])
        redirect(action: "show", id: trainingInstance.id)
    }

    def show() {
        def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        [trainingInstance: trainingInstance]
    }

    def edit() {
        def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        [trainingInstance: trainingInstance]
    }

    def update() {
        def trainingInstance = Training.get(params.id)
		
	
		
        if (!trainingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }
		
		def standardizedPatients = getStandardizedPatientsStr(trainingInstance.id)
			
		if(!standardizedPatients.equals('')){
			flash.message = message(code: 'default.training.is.accepted', args: [message(code: 'osceDay.label', default: 'OsceDay'), params.id])+standardizedPatients
			redirect(action: "show", id: params.id)
			return
		}
			
		setStartAndEndTime(trainingInstance)
		println("params.version = "+params.version);
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
        def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'training.label', default: 'Training'), params.id])
            redirect(action: "list")
            return
        }

        try {
		
			def standardizedPatients = getStandardizedPatientsStr(trainingInstance.id)
			
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
		def trainingInstance = Training.get(params.id)
        if (!trainingInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: trainingInstance.id)
		}
		
	}
	
	def setStartAndEndTime(trainingInstance){
		Date trainingDate = trainingInstance.trainingDate
		println(">>>>>>>>>>>>>>>trainingDate = "+trainingDate);
		String endHourPrame = params.timeEndHour
		String endMinPrame = params.timeEndMin
		String startHourPrame = params.timeStartHour
		String startMinPrame = params.timeStartMin
		
		if(trainingDate !=null && startHourPrame != null && !startHourPrame.equals("") && startMinPrame != null && !startMinPrame.equals("")){
			Long startHour = Long.valueOf(startHourPrame)
			Long startMin = Long.valueOf(startMinPrame)

			Long timeStart = trainingDate.getTime()+startMin*60*1000+startHour*60*60*1000
			trainingInstance.timeStart = new Date(timeStart)
		}else{
			trainingInstance.timeStart = null
		}
		
		if(trainingDate !=null &&  endHourPrame != null && !endHourPrame.equals("") && endMinPrame != null && !endMinPrame.equals("")){
			Long endHour = Long.valueOf(endHourPrame)
			Long endMin = Long.valueOf(endMinPrame)
			Long timeEnd = trainingDate.getTime()+endMin*60*1000+endHour*60*60*1000
			trainingInstance.timeEnd = new Date(timeEnd)	
		}else{
			trainingInstance.timeEnd = null
		}
	}
	
	def getStandardizedPatientsStr(trainingInstanceId){
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
		return standardizedPatients;
	}
}
