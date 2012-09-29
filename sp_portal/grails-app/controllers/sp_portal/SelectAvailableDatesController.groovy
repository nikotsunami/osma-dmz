package sp_portal
import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;



class SelectAvailableDatesController extends MainController{


	def beforeInterceptor = [action:this.&isLoggedInAsUser]
	private static final log = LogFactory.getLog(this)

	def index(){
		redirect(action: "show",params: params);

	}

	def showSemester(){
	
		session["semester"] = params.semester;
		redirect(controller:'SelectAvailableDates',action: "show",params: params);
	
	}

	def show(){
				
		log.info("show selectAvailableDates")
		
		def acceptedPatinetln;
		def availableOsceDays =[]; 
		def availableTrainingDays= [];
		def acceptedTrainingDays = [];
		def acceptedOsceDays = [];
		
		def semester;
		
		if(session.semester){
		
			semester = local.Semester.get(session.semester);
		}
			if(semester){
				def trainingSort = "trainingDate";
				def trainingOrder = "desc";
				def osceDaySort = "osceDate";
				def	osceDayOrder = "desc";
				
				params.max = Math.min(params.max ? params.int('max') : 10, 100)
				
				if(params.sort.equals("trainingDate")||params.sort.equals("timeStart")||params.sort.equals("timeEnd")){
				
					trainingSort = params.sort;
					trainingOrder = params.order;
					
					
				}
				
				if(params.sort.equals("osceDate")){
					osceDaySort = params.sort;
					osceDayOrder = params.order;
				}
				
				availableTrainingDays = local.Training.list(fetch: [semester:semester],max: params.max, offset: params.offset,sort: trainingSort, order: trainingOrder);
				
			
				def osceDaysOsce = local.Osce.findAllBySemester(semester);
				
				
				if(osceDaysOsce){
				
					availableOsceDays = local.OsceDay.list(fetch: [osce:osceDaysOsce],max: params.max, offset: params.offset,sort: osceDaySort, order: osceDayOrder);
					
				}
			}
									
			def currentUser = User.findById(session.user.id);
			if(log.isDebugEnabled()){
				log.debug("find availableOsceDays : "+availableOsceDays)
				log.debug("find availableTrainingDays :"+availableTrainingDays)
				log.debug("find currentUser : "+currentUser)
			}
			
			if(currentUser!=null){
				acceptedPatinetln = local.PatientlnSemester.findByStandardizedPatient(currentUser.standardizedPatient);
				if(log.isDebugEnabled()){
					log.debug("find acceptedPatinetln : "+acceptedPatinetln)
				}
				if(acceptedPatinetln!=null){
					
					acceptedOsceDays = acceptedPatinetln.acceptedOsceDay
					acceptedTrainingDays = acceptedPatinetln.acceptedTraining;
					if(log.isDebugEnabled()){
						log.debug("acceptedOsceDays : "+acceptedOsceDays)
						log.debug("acceptedTrainingDays :"+acceptedTrainingDays)
					}
				}

		}
		
		[semester: session.semester,availableTrainingDays:availableTrainingDays , availableOsceDays:availableOsceDays, acceptedTrainingDays:acceptedTrainingDays , acceptedOsceDays:acceptedOsceDays]		
		
	}


	def update(){
		if(log.isTraceEnabled()){
			log.trace(">> In class SelectAvailableDatesController Method update() with params : "+params)
		}
		def acceptedPatinetln;
		def availableOsceDays =[]; 
		def availableTrainingDays= [];
		def acceptedTrainingDays = [];
		def acceptedOsceDays = [];
		def oDay=[];
		def tDay=[];
		
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
		if(log.isDebugEnabled()){
			log.debug("find currentUser : "+currentUser)
		}
		if(currentUser!=null){
			acceptedPatinetln = local.PatientlnSemester.findByStandardizedPatient(currentUser.standardizedPatient);	
			
			if(log.isDebugEnabled()){
				log.debug("find acceptedPatinetln : "+acceptedPatinetln)
			}
			if(acceptedPatinetln!=null){
				if(acceptedPatinetln.acceptedOsceDay.size()>0){
					oDay.addAll(acceptedPatinetln.acceptedOsceDay);
				}
				if(acceptedPatinetln.acceptedTraining.size()>0){
					tDay.addAll(acceptedPatinetln.acceptedTraining);
				
				}
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
						boolean updateDays;
						boolean updateTrain;
			
		
				
				
			
				if(acceptedOsceDays.size()==oDay.size()){
					for(int i=0;i<acceptedOsceDays.size();i++){
					
							if(acceptedOsceDays.get(i).equals(oDay.get(i))==false){
								oDay.set(i, acceptedOsceDays.get(i));
							}
					}
					
				
					if(acceptedOsceDays.equals(oDay)){
							updateDays=true
					}else{
						updateDays=false
					}
					
				}
				
				if(acceptedTrainingDays.size()==tDay.size()){
					for(int j=0;j<acceptedTrainingDays.size();j++){
						if(acceptedTrainingDays.get(j).equals(tDay.get(j))==false){
							tDay.set(j,acceptedTrainingDays.get(j));
						
						}
					
					}
					if(acceptedTrainingDays.equals(tDay)){
							updateTrain=true
					}else{
						updateTrain=false
					}
						
				
				}
				
				if(updateDays==true && updateTrain==true){
						acceptedPatinetln.acceptedOsceDay.clear();
						acceptedPatinetln.acceptedTraining.clear();
						acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
						acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
						if(session.semester){
							def patinetSemester=local.Semester.get(session.semester);
							acceptedPatinetln.semester=patinetSemester
						}
						acceptedPatinetln.save()
						//sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");
				
				}else if(updateDays==false && updateTrain==false){ 
						acceptedPatinetln.acceptedOsceDay.clear();
						acceptedPatinetln.acceptedTraining.clear();
						acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
						acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
						if(session.semester){

							def patinetSemester=local.Semester.get(session.semester);
							acceptedPatinetln.semester=patinetSemester
						}
						acceptedPatinetln.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");
				
				}else if(updateDays==true && updateTrain==false){
						acceptedPatinetln.acceptedOsceDay.clear();
						acceptedPatinetln.acceptedTraining.clear();
						acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
						acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
						if(session.semester){
							def patinetSemester=local.Semester.get(session.semester);
							acceptedPatinetln.semester=patinetSemester
						}
						acceptedPatinetln.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");
				
				}else if(updateDays==false && updateTrain==true){
						acceptedPatinetln.acceptedOsceDay.clear();
						acceptedPatinetln.acceptedTraining.clear();
						acceptedPatinetln.acceptedOsceDay.addAll(acceptedOsceDays)
						acceptedPatinetln.acceptedTraining.addAll(acceptedTrainingDays);
						if(session.semester){
							def patinetSemester=local.Semester.get(session.semester);
							acceptedPatinetln.semester=patinetSemester
						}
						acceptedPatinetln.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");
				
				
				
				}
				
				if(log.isDebugEnabled()){
					log.debug("update acceptedPatinetln : "+acceptedPatinetln)
				}
					
						
						
						

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
						if(session.semester){
							def patinetSemester=local.Semester.get(session.semester);
							patient.semester=patinetSemester
						}
						patient.save()
						sendEmail(currentUser);
						redirect(controller:"thank", action:"thankPatientInSemester");

				}else{
					  flash.message = message(code: 'patientInSemester.error.message');
					  redirect(action: "show",params: params);
				
				}
				if(log.isDebugEnabled()){
					log.debug("new patientlnSemester : "+patient)
				}													
							
			}
					
													
	
		}		
	}


	DMZMailService DMZMailService =null;
	
	private void sendEmail(def currentUser){
		if(log.isTraceEnabled()){
			log.trace(">> In class SelectAvailableDatesController Method sendEmail entered currentUser : "+currentUser)
		}
		def patient = currentUser.standardizedPatient;
		if(log.isDebugEnabled()){
			log.debug("current patient : "+patient)
		}	
			
			
			String to = grailsApplication.config.grails.mail.to;

			String subject= grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.subject;
			String body = grailsApplication.config.sp_portal.mail.saveTraningDate.defaultText;
		
			String from = grailsApplication.config.grails.mail.from;
					
			if(patient.preName){
				body = body.replaceAll("#preName",patient.preName);
			}
			
			if(patient.name){
				body = body.replaceAll("#name",patient.name);
			}
			if(log.isDebugEnabled()){
				log.debug("sendMail to : "+to)
				log.debug("sendMail subject : "+subject)
				log.debug("sendMail body : "+body)
				log.debug("sendMail from : "+from)
			}
			DMZMailService.sendMailByChangeDays(to,from,subject,body);
			


		
	}			
	
}

