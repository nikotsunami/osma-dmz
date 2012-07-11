package sp_portal.local
import local.*;

class Emails {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
    Date sendDate
	String sent
    String receiver
	String content
	String subject

    static constraints = {
	    sendDate nullable: true
        receiver nullable: true
        content nullable: true, maxSize: 999
		subject nullable: true
		sent nullable: true
    }
} 
