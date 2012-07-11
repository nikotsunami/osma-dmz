package sp_portal

import org.springframework.mail.MailMessage
import grails.util.GrailsUtil;
import java.text.SimpleDateFormat;

class DMZMailService {

	def mailService;
	
	void sendMails(String eTo,String eFrom,String eSubject,String eBody) {

		Map mail =[to: eTo,from: eFrom,subject: eSubject,body: eBody]
	
		if (GrailsUtil.getEnvironment() == "production"){
			
			mailService.sendMail {     
				  to mail.to
				  subject mail.subject     
				  body mail.body 
			}
			
			
		} else {
		
			println("Simulate sending email to <<${eTo}>> with subject: <<${eSubject}>> body: <<${eBody}>> ");
		}
		
		saveEmail(mail.to,mail.body,mail.subject,mail.from)
    }
	
	private Date getCurrentDate(){
		Date dateTime = new java.util.Date();   
		return dateTime
	}
	
	private void saveEmail(String to,String body,String subject,String from){
			local.Emails email = new local.Emails();
			email.sendDate = getCurrentDate();
			email.receiver = to;
			email.content = body;
			email.subject = subject;
			email.sent = from;
			email.save(flush: true);
	}
}