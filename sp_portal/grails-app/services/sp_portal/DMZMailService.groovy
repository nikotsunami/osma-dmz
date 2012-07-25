package sp_portal

import org.springframework.mail.MailMessage
import grails.util.GrailsUtil;
import java.text.SimpleDateFormat;


class DMZMailService {

	def mailService;
	
	void sendMails(String eTo,String eFrom,String eSubject,String eBody) {

		Map mail =[to: eTo,from: eFrom,subject: eSubject,body: eBody]
		
		if(!hasSaveEmail(eTo,eFrom,eSubject,eBody)){
			if (GrailsUtil.getEnvironment() != "production"){
				mailService.sendMail {     
					  to mail.to
					  from mail.from
					  subject mail.subject     
					  body mail.body 
				}
				
				
			} else {
			
				println("Simulate sending email to <<${eTo}>> with subject: <<${eSubject}>> body: <<${eBody}>> ");
			}
			
			saveEmail(mail.to,mail.body,mail.subject,mail.from)
		}
		
    }
	void sendMailByChangeDays(String eTo,String eFrom,String eSubject,String eBody) {

		Map mail =[to: eTo,from: eFrom,subject: eSubject,body: eBody]
		
	
			if (GrailsUtil.getEnvironment() == "production"){
				mailService.sendMail {     
					  to mail.to
					  from mail.from
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
	
	/**
	 *Verify has been sent the Email, is don't have send, or to send, and save the send Email information
	 */
	private boolean hasSaveEmail(String to,String from,String subject,String body){
		def oldEmailList = local.Emails.findAll();
		boolean hasExist = false;
		for(local.Emails mail : oldEmailList){
			if(mail.receiver == to && mail.content == body
			   && mail.subject == subject && mail.sent == from && isTheSameDay(mail.sendDate,getCurrentDate())){
				hasExist = true;
			}
		}
		return hasExist;
	}
	
	/**
	 *Validation two date is on the same day
	 */
	private boolean isTheSameDay(Date firstDate,Date sendDate){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd")
		String date1 = sf.format(firstDate);
		String date2 = sf.format(sendDate);
		if(date1.equals(date2)){
			return true;
		}
		return false
	}
	
	
	
	
	
}