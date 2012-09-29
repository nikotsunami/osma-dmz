package sp_portal.remote
import remote.*;
import java.util.Calendar;
import org.apache.log4j.Logger;

class Osce {
   static mapping = {
      datasources(['original'])
	  copiedOsce column: 'copied_osce'
	  semester column: 'semester'

   }
  
    Long id
    Short longBreak
	Boolean isRepeOsce
	Boolean isValid
	Short lunchBreak
	String name
	Integer numberCourses
	Integer numberPosts
	Integer numberRooms
	Integer osceSecurityTypes
	Integer osceStatus
	Integer patientAveragePerPost
    Integer postLength
	Integer security
	Short shortBreak
	Short shortBreakSimpatChange
	Integer studyYear
	Integer version
	Osce copiedOsce
	Semester semester
	
	static hasMany = [copiedOsce: Osce,
                     semester: Semester
					 ]

   static mappedBy = [copiedOsce: 'id',
                      semester: 'id'
					]

	

    static constraints = {
		longBreak nullable:true
		isRepeOsce nullable:true
		isValid nullable:true
		lunchBreak nullable:true
		name nullable:true
		numberCourses nullable:true
		numberPosts nullable:true
		osceSecurityTypes nullable:true
		osceStatus nullable:true
		patientAveragePerPost nullable:true
		postLength nullable:true
		security nullable:true
		shortBreak nullable:true
		shortBreakSimpatChange nullable:true
		studyYear nullable:true
		version nullable:true
		copiedOsce nullable:true
    }
	
}
