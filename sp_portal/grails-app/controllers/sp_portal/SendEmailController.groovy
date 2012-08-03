package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import java.util.ArrayList;
import java.util.List;
import org.springframework.mail.MailMessage
import org.apache.commons.logging.LogFactory;


class SendEmailController extends MainController {

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]
	private static final log = LogFactory.getLog(this)

    def index() {
		log.info("index of sendEmail")
        redirect(action: "show", params: params)
    }
    

    def show() {
		log.info("show sendEmail")
	    def patientInstanceList = local.StandardizedPatient.findAll();
	    [patientInstanceList: patientInstanceList]
    }
	
	
	DMZMailService DMZMailService =null;
	
	def sendEmail(){
		log.info("In class SendEmailController Method sendEmail ")
		def patients = session.sendPatients;
		if(log.isDebugEnabled()){
			log.debug("patients : "+patients)
		}
		String to="";
		String subject="";
		String body="";
		String from = "";
		try{
			for(local.StandardizedPatient patient: patients){
			
					to = patient.email;
					//to = "marvin@jserver"
					subject= params.editedEmailSubject;
					body = params.editedEmailText;
					from = grailsApplication.config.grails.mail.username
					if(patient.preName){
						body = body.replaceAll("#preName",patient.preName);
					}
					
					if(patient.name){
						body = body.replaceAll("#name",patient.name);
					}
					if(log.isDebugEnabled()){
						log.debug("to : "+to)
						log.debug("subject : "+subject)
						log.debug("body : "+body)
						log.debug("from : "+from)
					}
					log.info("call Service sendMails")
					DMZMailService.sendMails(to,from,subject,body);
			}
			flash.message = message(code: 'user.sendEmail.successful')
			redirect(action: "show")
		}catch(Exception e){
			e.printStackTrace();
			log("exception: "+e.getMessage())
			redirect(action: "sendFailure")
		}finally{
			session.sendPatients=null;
		}
		
	}
	
	def selectAll(){
		if(log.isTraceEnabled()){
			log.trace(">> In class SendEmailController Method selectAll with params : "+params)
		}
		redirect(action: "show", params: [isSend: true])
	}
	
	def showPreviewEmail(){   
        if(log.isTraceEnabled()){
			log.trace(">> In class SendEmailController Method showPreviewEmail with params : "+params)
		}
	
	    def patientIdToString = params.findAll({ key, value ->
													key.startsWith("patient")
													});
													
		
		
        def sendPatients=[];
        patientIdToString.each({ key, value ->

			
            def components = key.split("\\.");
		
				if(value.equals("send")){
					
					def patient = local.StandardizedPatient.findById(components[1]);
					
					sendPatients.add(patient);
				}
			});
		if(log.isDebugEnabled()){
			log.debug("sendPatients : "+sendPatients)
		}	
		session.sendPatients = sendPatients;
		
		if(session.sendPatients){
			["defaultEmail" : grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.defaultText ,"defaultSubject": grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.subject]
		}else{
			redirect(action: "alertWindow");
		}
	}
	
	def cancel(){
		log.info("In class SendEmailController Method cancel")
		redirect(action: "show")
	}
	
	def sendFailure(){
	
	}
	
	def alertWindow(){
	
	}
	
	
	def showSentEmail(){
		if(log.isTraceEnabled()){
			log.trace(">> In class SendEmailController Method isTraceEnabled with params : "+params)
		}
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def emailList = local.Emails.list(params);
		[emailList: emailList,emailTotal: local.Emails.count()]
	}
	
	def showEmailDetails(){
		if(log.isTraceEnabled()){
			log.trace(">> In class SendEmailController Method showEmailDetails with params : "+params)
		}
		def email = local.Emails.get(params.id);
		[emailInstance: email];
	}

}
