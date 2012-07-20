package sp_portal
import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;



class SelectAvailableDatesController extends MainController{


	def beforeInterceptor = [action:this.&isLoggedInAsUser]


	def index(){
		redirect(action: "show",params: params);

	}


	def show(){
		
		def acceptedPatinetln;
		def availableOsceDays =[]; 
		def availableTrainingDays= [];
		def acceptedTrainingDays = [];
		def acceptedOsceDays = [];

		
		availableOsceDays = local.OsceDay.findAll();
		availableTrainingDays = local.Training.findAll();
		def currentUser = User.findById(session.user.id);
		
		
		if(currentUser!=null){
			acceptedPatinetln = local.PatientlnSemester.findByStandardizedPatient(currentUser.standardizedPatient);
			
			if(acceptedPatinetln!=null){
				
				acceptedOsceDays = acceptedPatinetln.acceptedOsceDay
				acceptedTrainingDays = acceptedPatinetln.acceptedTraining;
			}

		}
		[availableTrainingDays:availableTrainingDays , availableOsceDays:availableOsceDays, acceptedTrainingDays:acceptedTrainingDays , acceptedOsceDays:acceptedOsceDays]

	
	
	}


	def update(){
		def acceptedPatinetln;
		def availableOsceDays =[]; 
		def availableTrainingDays= [];
		def acceptedTrainingDays = [];
		def acceptedOsceDays = [];
		
		def training = params.findAll({ key, value ->
													key.startsWith("training")
													});
													
		training.each({ key, value ->

				def components = key.split("\\.");
		
				if(value.equals("on")){
				
					
					def trainingDay = local.Training.findById(components[1]);
					acceptedTrainingDays.add(trainingDay);
				}
			});	
											
		
		
		def osce=params.findAll({ key, value ->
													key.startsWith("osce")
													});
													
		osce.each({ key, value ->

	     def component = key.split("\\.");
		
				if(value.equals("on")){
					
					def osceDay = local.OsceDay.findById(component[1]);
					acceptedOsceDays.add(osceDay);
				}
			});	
				
		boolean accepted ;
		
		def currentUser = User.findById(session.user.id);
		
	
		if(currentUser!=null){
			acceptedPatinetln = local.PatientlnSemester.findByStandardizedPatient(currentUser.standardizedPatient);	
			if(acceptedPatinetln!=null){
					if(acceptedOsceDays.size()!=0 || acceptedTrainingDays.size()!=0){
						acceptedPatinetln.accepted=false;
						accepted=false
						
					}
					if(acceptedOsceDays.size()!=0 && acceptedTrainingDays.size()!=0){
						acceptedPatinetln.accepted=true;
						accepted=true
					}
					
					if(acceptedOsceDays.size()==0 && acceptedTrainingDays.size()==0){
						acceptedPatinetln.accepted=false
						accepted=true
						
					}
				if(accepted==true){
						acceptedPatinetln.acceptedOsceDay.clear();
						acceptedPatinetln.acceptedTraining.clear();
						acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
						acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
						acceptedPatinetln.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");

				}else{
					  flash.message = message(code: 'patientInSemester.error.message');
					  redirect(action: "show",params: params);
				
				}
				
			}else{
				def patient =new local.PatientlnSemester();
				patient.standardizedPatient=currentUser.standardizedPatient	
				if(acceptedOsceDays.size()!=0 || acceptedTrainingDays.size()!=0){
						patient.accepted=false;
						accepted=false
						
					}
					if(acceptedOsceDays.size()!=0 && acceptedTrainingDays.size()!=0){
						patient.accepted=true;
						accepted=true
					}
					
					if(acceptedOsceDays.size()==0 && acceptedTrainingDays.size()==0){
						patient.accepted=false
						accepted=true
						
					}
				if(accepted==true){
						
						patient.acceptedOsceDay=acceptedOsceDays;
						patient.acceptedTraining=acceptedTrainingDays;
						patient.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");

				}else{
					  flash.message = message(code: 'patientInSemester.error.message');
					  redirect(action: "show",params: params);
				
				}
																	
							
			}
					
													
	
		}		
	}


	DMZMailService DMZMailService =null;
	
	private void sendEmail(def currentUser){
		
		def patient = currentUser.standardizedPatient;
		try{
			
			
			String to = grailsApplication.config.grails.mail.username;

			String subject= grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.subject;
			String body = grailsApplication.config.sp_portal.mail.saveTraningDate.defaultText;
		
			String from = patient.email;

			//TODO Used for testing whether can send email
			//String from = "paul@jserver";
			if(patient.preName){
				body = body.replaceAll("#preName",patient.preName);
			}
			
			if(patient.name){
				body = body.replaceAll("#name",patient.name);
			}
			DMZMailService.sendMails(to,from,subject,body);
			


		}catch(Exception e){
			e.printStackTrace();
			println(">>>>>>>>>>>exception: "+e.getMessage());
		}
		
	}			
	
}

