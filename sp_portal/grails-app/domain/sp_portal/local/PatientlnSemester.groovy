package sp_portal.local
import local.*;

class PatientlnSemester {
    static mapping = {
	datasources(['DEFAULT'])
       standardizedPatient column: 'standardized_patient'
       acceptedOsceDay column: 'accepted_osce_day'
       acceptedTraining column: 'accepted_training'
	   semester column:'semester'
    }
    Long id
    Integer version
    StandardizedPatient standardizedPatient
	boolean accepted 
	Semester semester
	
	


    static constraints = {
    }


    static hasMany = [acceptedOsceDay: OsceDay,
					  acceptedTraining: Training,
					  semester:Semester
                       ]

    /*static mappedBy = [
                       Semester: 'id'
					   
			  
			 ]*/
}
