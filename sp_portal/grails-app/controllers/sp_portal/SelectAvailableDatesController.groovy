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
			}else{
				//render message(code: 'checkquestion.noneavailable');
	
			
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

	
		println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		def training = params.findAll({ key, value ->
													key.startsWith("training")
													});
													
		training.each({ key, value ->

				def components = key.split("\\.");
		
				if(value.equals("on")){
					
					def trainingDay = local.Training.findById(components[1]);
					acceptedTrainingDays.add(trainingDay);
					println("acceptedTrainingDays" +acceptedTrainingDays);
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
					println("acceptedOsceDays" +acceptedOsceDays);
				}
			});	
				
		def currentUser = User.findById(session.user.id);
			
		if(currentUser!=null){
			acceptedPatinetln = local.PatientlnSemester.findByStandardizedPatient(currentUser.standardizedPatient);	
			if(acceptedPatinetln!=null){		
					acceptedPatinetln.acceptedOsceDay.clear();
					acceptedPatinetln.acceptedTraining.clear();
					acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
					acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
				acceptedPatinetln.save();
				redirect(action: "show");
				
				
			}else{
				println("acceptedPatinetln is null");
							
			
			}
					
		
													
	
		}		
	}
}

