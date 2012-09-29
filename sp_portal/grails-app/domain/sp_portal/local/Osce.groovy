package sp_portal.local
import local.*;

class Osce {
   static mapping = {
      datasources(['DEFAULT'])
   }
  
    Long id
	Long origId
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
	Short middleBreak
	Integer maxNumberStudents
	
	static hasMany = [copiedOsce: Osce,
                     semester: Semester
					 ]

   static mappedBy = [copiedOsce: 'id',
                      semester: 'id'
					]

	

    static constraints = {
        origId nullable:true
		longBreak nullable:true
		maxNumberStudents nullable:true
		isRepeOsce nullable:true
		isValid nullable:true
		lunchBreak nullable:true
		middleBreak nullable:true
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
	
	public String toString(){
		return name;
	}
	
}
