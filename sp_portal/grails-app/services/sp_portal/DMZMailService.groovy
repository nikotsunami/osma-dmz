package sp_portal

import org.springframework.mail.MailMessage
import grails.util.GrailsUtil;

class DMZMailService {

	def mailService;

	void sendMails(String eTo,String eSubject,String eBody) {
		
		if (GrailsUtil.getEnvironment() == "production"){
			Map mail =[to: eTo,subject: eSubject,body: eBody]
			mailService.sendMail {     
				  to mail.to
				  subject mail.subject     
				  body mail.body 
			}
		} else {
		
			println("Simulate sending email to <<${eTo}>> with subject: <<${eSubject}>> body: <<${eBody}>> ");
		}
    }
	
	def getMailConfig() {
        mailService.getMailConfig();
    }

	boolean isDisabled() {
        mailService.isDisabled();
    }
}