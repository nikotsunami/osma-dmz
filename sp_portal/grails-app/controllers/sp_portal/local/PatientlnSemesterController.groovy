package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class PatientlnSemesterController extends sp_portal.MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsAdmin]
	private static final log = LogFactory.getLog(this)
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
		log.info("index of patientlnSemester")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of patientlnSemester")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [patientlnSemesterInstanceList: PatientlnSemester.list(params), patientlnSemesterInstanceTotal: PatientlnSemester.count()]
    }

    def create() {
		log.info("user create patientlnSemester")
        [patientlnSemesterInstance: new PatientlnSemester(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PatientlnSemesterController Method save() with params : "+params)
		}
		boolean accepted
		
		def sp=StandardizedPatient.findById(params.standardizedPatient.id);
		if(log.isDebugEnabled()){
			log.debug("find standardizedPatient : "+sp)
		}
		def patientlnSemesterInstance = PatientlnSemester.findByStandardizedPatient(sp)
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n patientlnSemesterInstance.accepted: ");
			sb.append(patientlnSemesterInstance?.accepted);
			sb.append( "\n patientlnSemesterInstance.acceptedOsceDay: ");
			sb.append(patientlnSemesterInstance?.acceptedOsceDay);
			sb.append( "\n patientlnSemesterInstance.acceptedTraining: ");
			sb.append(patientlnSemesterInstance?.acceptedTraining);
			log.debug( "find patientlnSemesterInstance by "+sp +" : " + sb.toString());
		} 
		
		if(patientlnSemesterInstance!=null){
		
				if(params.acceptedOsceDay!=null || params.acceptedTraining!=null){
					accepted=false
					
					
				}
				 if(params.acceptedOsceDay!=null && params.acceptedTraining!=null){
					patientlnSemesterInstance.accepted=true;
					accepted=true
				
				}
				
				if(params.acceptedOsceDay==null && params.acceptedTraining==null){
					accepted=false
					
					
				}

				if(!params.semester){
					accepted=false
					
				}
				
				
				if(accepted==true){
						def osces = [];
						def trainings=[];
						
						def osce=params.findAll({ key, value ->
													key.startsWith("acceptedOsceDay");
													});
													
						osce.each({ key, value ->
								for(int i =0; i< value.size();i++){
		
									def osceDay = OsceDay.findById(Long.valueOf(value[i]));
									osces.add(osceDay);
								}
								
						});

						def training=params.findAll({ key, value ->
													key.startsWith("acceptedTraining");
													});
													
						training.each({ key, value ->
						
								for(int i =0; i< value.size();i++){
						
									def train = Training.findById(Long.valueOf(value[i]));
									trainings.add(train);
								}
						});		
						
						def semester = Semester.get(params.semester);
						patientlnSemesterInstance.acceptedOsceDay.clear();
						patientlnSemesterInstance.acceptedOsceDay.addAll(osces);
						patientlnSemesterInstance.acceptedTraining.clear();
						patientlnSemesterInstance.acceptedTraining.addAll(trainings);

						patientlnSemesterInstance.semester= semester;
						patientlnSemesterInstance.save()
						redirect(action: "show", id: patientlnSemesterInstance.id)
				}else{
					  flash.message = message(code: 'patientInSemester.error.message');
					  redirect(action: "show", id: patientlnSemesterInstance.id)
				
				}
				if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n patientlnSemesterInstance.accepted: ");
					sb.append(patientlnSemesterInstance?.accepted);
					sb.append( "\n patientlnSemesterInstance.acceptedOsceDay: ");
					sb.append(patientlnSemesterInstance?.acceptedOsceDay);
					sb.append( "\n patientlnSemesterInstance.acceptedTraining: ");
					sb.append(patientlnSemesterInstance?.acceptedTraining);
					log.debug( "find patientlnSemesterInstance and update to : " + sb.toString());
				} 
				
				
			}else{				
			
			
				if(params.semester){
					params.semester = Semester.get(params.semester);
				}
			
				def patient =new PatientlnSemester(params);
				patient.standardizedPatient=patient.standardizedPatient						
								
				if(patient.acceptedOsceDay!=null || patient.acceptedTraining!=null){
						accepted=false
						
				}
				if(patient.acceptedOsceDay!=null && patient.acceptedTraining!=null){
					patient.accepted=true;
					accepted=true
				}
				
				if(patient.acceptedOsceDay==null && patient.acceptedTraining==null){
					accepted=false
					
				}
				
				if(patient.semester==null && patient.semester==null){
					accepted=false
					
				}
				
				
				
				if(accepted==true){
						
						patient.acceptedOsceDay=patient.acceptedOsceDay;
						patient.acceptedTraining=patient.acceptedTraining;
						patient.save()
						redirect(action:"show");

				}else{
					  flash.message = message(code: 'patientInSemester.error.message');
					  redirect(action: "create");
				
				}
				if(log.isDebugEnabled()){
					StringBuffer sb = new StringBuffer();
					sb.append( "\n patient.accepted: ");
					sb.append(patient?.accepted);
					sb.append( "\n patient.acceptedOsceDay: ");
					sb.append(patient?.acceptedOsceDay);
					sb.append( "\n patient.acceptedTraining: ");
					sb.append(patient?.acceptedTraining);
					log.debug( "not find patientlnSemesterInstance and new PatientlnSemester: " + sb.toString());
				} 			
			}
			
			
		
		
        /*if (!patientlnSemesterInstance.save(flush: true)) {
            render(view: "create", model: [patientlnSemesterInstance: patientlnSemesterInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), patientlnSemesterInstance.id])
        redirect(action: "show", id: patientlnSemesterInstance.id)
		
		*/
    }

    def show() {
		log.info("user show patientlnSemester")
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get patientlnSemesterInstance : "+patientlnSemesterInstance)
		}
        if (!patientlnSemesterInstance) {
			//flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        [patientlnSemesterInstance: patientlnSemesterInstance]
    }

    def edit() {
		log.info("user edit patientlnSemester")
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get patientlnSemesterInstance : "+patientlnSemesterInstance)
		}
        if (!patientlnSemesterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'patientlnSemester.label', default: 'PatientlnSemester'), params.id])
            redirect(action: "list")
            return
        }

        [patientlnSemesterInstance: patientlnSemesterInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PatientlnSemesterController Method update() with params : "+params)
		}
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get patientlnSemesterInstance : "+patientlnSemesterInstance)
		}
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
		
		def semester;
		if(params.semester){
			params.semester = Semester.get(params.semester);
		}else{
		    render(view: "edit", model: [patientlnSemesterInstance: patientlnSemesterInstance])
			return
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
		if(log.isTraceEnabled()){
			log.trace(">> In class PatientlnSemesterController Method delete() with params : "+params)
		}
        def patientlnSemesterInstance = PatientlnSemester.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get patientlnSemesterInstance : "+patientlnSemesterInstance)
		}
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
		log.info("user cancel patientlnSemester")
		def patientlnSemesterInstance = PatientlnSemester.get(params.id)
		if(log.isDebugEnabled()){
			log.debug("get patientlnSemesterInstance : "+patientlnSemesterInstance)
		}
        if (!patientlnSemesterInstance) {
            redirect(action: "list")
            
        }else{
		    redirect(action: "show", id: patientlnSemesterInstance.id)
		}
		
	}
}
