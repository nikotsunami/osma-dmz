package sp_portal

import org.springframework.mail.MailMessage

import grails.util.Environment;
import grails.util.GrailsUtil;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

class DMZMailService {
	private static Logger log = Logger.getLogger(DMZMailService.class);
	def mailService;
	
	void sendMails(String eTo,String eFrom,String eSubject,String eBody) {
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method sendMails entered eTo : " + eTo + " eFrom : " + eFrom + " eSubject : " + eSubject + " eBody : " + eBody)
		}
		Map mail =[to: eTo,from: eFrom,subject: eSubject,body: eBody]
		println(">> In class DMZMailService Method sendMails entered eTo : " + mail.to + " eFrom : " + mail.from + " eSubject : " + mail.subject+ " eBody : " + mail.body)
		if(!hasSaveEmail(eTo,eFrom,eSubject,eBody)){
			String curEnv = Environment.getCurrent().toString()
			if ("PRODUCTION".equals(curEnv)){
				mailService.sendMail {     
					  to mail.to
					  //to "somewhere@example.com"
					  from mail.from
					  subject mail.subject     
					  body mail.body 
				}
				
			} else {
				println("Environment is ${curEnv}\nSimulate sending email to <<${eTo}>> with subject: <<${eSubject}>> body: <<${eBody}>> ");
			}
			saveEmail(mail.to,mail.body,mail.subject,mail.from)
		}		
    }
	
	void sendMailByChangeDays(String eTo,String eFrom,String eSubject,String eBody) {
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method sendMailByChangeDays entered eTo : "+eTo+" eFrom : "+eFrom+" eSubject : "+eSubject+" eBody : "+eBody)
		}
		Map mail =[to: eTo,from: eFrom,subject: eSubject,body: eBody]
		String curEnv = Environment.getCurrent().toString()
		println(">> In class DMZMailService Method sendMails entered eTo : " + mail.to + " eFrom : " + mail.from + " eSubject : " + mail.subject+ " eBody : " + mail.body)
		if ("PRODUCTION".equals(curEnv)){
			mailService.sendMail {     
				  to mail.to
//				  to "michael.wgnr@gmail.com"
				  from mail.from
				  subject mail.subject     
				  body mail.body 
			}		
		} else {
			println("Environment is ${curEnv}\nSimulate sending email to <<${eTo}>> with subject: <<${eSubject}>> body: <<${eBody}>> ");
		}
		saveEmail(mail.to,mail.body,mail.subject,mail.from)
    }
	
	private Date getCurrentDate(){
		log.info("get current date")
		Date dateTime = new java.util.Date();   
		return dateTime
	}
	
	private void saveEmail(String to,String body,String subject,String from){
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method saveEmail entered to : "+to+" body : "+body+" subject : "+subject+" from : "+from)
		}
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
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method hasSaveEmail entered to : " + to + " body : " + body + " subject : " + subject + " from : "+from)
		}
		def oldEmailList = local.Emails.findAll();
		boolean hasExist = false;
		for(local.Emails mail : oldEmailList){
			if(mail.receiver == to && mail.content == body
			   && mail.subject == subject && mail.sent == from && isTheSameDay(mail.sendDate,getCurrentDate())){
				hasExist = true;
			}
		}
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method hasSaveEmail return hasExist : "+hasExist)
		}
		return hasExist;
	}
	
	/**
	 *Validation two date is on the same day
	 */
	private boolean isTheSameDay(Date firstDate,Date sendDate){
		if(log.isTraceEnabled()){
			log.trace(">> In class DMZMailService Method isTheSameDay entered firstDate : "+ firstDate + " sendDate : "+sendDate)
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd")
		String date1 = sf.format(firstDate);
		String date2 = sf.format(sendDate);
		if(date1.equals(date2)){
			log.info("isTheSameDay return true")
			return true;
		}
		log.info("isTheSameDay return false")
		return false
	}
	
	
	
	
	
}
