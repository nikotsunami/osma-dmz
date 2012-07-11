package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import java.util.ArrayList;
import java.util.List;
import org.springframework.mail.MailMessage


class SendEmailController extends MainController {

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]


    def index() {
        redirect(action: "show", params: params)
    }
    

    def show() {
	  def patientInstanceList = local.StandardizedPatient.findAll();
	  [patientInstanceList: patientInstanceList]
    }
	
	
	DMZMailService DMZMailService =null;
	
	def sendEmail(){
		def patients = session.sendPatients;
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
					DMZMailService.sendMails(to,from,subject,body);
			}
			flash.message = message(code: 'user.sendEmail.successful')
			redirect(action: "show")
		}catch(Exception e){
			e.printStackTrace();
			println("exception: "+e.getMessage())
			redirect(action: "sendFailure")
		}finally{
			session.sendPatients=null;
		}
		
	}
	
	def selectAll(){
	
		redirect(action: "show", params: [isSend: true])
	}
	
	def showPreviewEmail(){   
       
	
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
			
		session.sendPatients = sendPatients;
		
		if(session.sendPatients){
			["defaultEmail" : grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.defaultText ,"defaultSubject": grailsApplication.config.sp_portal.mail.inviteStandardizedPatients.subject]
		}else{
			redirect(action: "alertWindow");
		}
	}
	
	def cancel(){
		redirect(action: "show")
	}
	
	def sendFailure(){
	
	}
	
	def alertWindow(){
	
	}
	
	
	def showSentEmail(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def emailList = local.Emails.list(params);
		[emailList: emailList,emailTotal: local.Emails.count()]
	}
	
	def showEmailDetails(){
		
		def email = local.Emails.get(params.id);
		[emailInstance: email];
	}

}
