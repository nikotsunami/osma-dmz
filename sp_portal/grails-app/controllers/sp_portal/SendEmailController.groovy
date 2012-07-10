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
		try{
			for(local.StandardizedPatient patient: patients){
				
					to = patient.email;
					//to = "marvin@jserver"
					subject= params.editedEmailSubject;
					body = params.editedEmailText;
					body = body.replaceAll("#preName",patient.preName);
					body = body.replaceAll("#name",patient.name);
					DMZMailService.sendMails(to,subject,body);
			}
			render message(code: 'user.sendEmail.successful')
		}catch(Exception e){
			render message(code: 'user.sendEmail.failure')
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
			Alert.show("sssss");
			//render message(code: 'user.selectPatient.message')
		}
	}
	
	def cancel(){
		redirect(action: "show")
	}

	

}
