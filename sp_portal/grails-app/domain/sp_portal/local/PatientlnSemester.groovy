package sp_portal.local
import local.*;

class PatientlnSemester {
    static mapping = {
	datasources(['DEFAULT'])
       standardizedPatient column: 'standardized_patient'
       acceptedOsceDay column: 'accepted_osce_day'
       acceptedTraining column: 'accepted_training'
    }
    Long id
    Integer version
    StandardizedPatient standardizedPatient


    static constraints = {
    }


    static hasMany = [  acceptedOsceDay: OsceDay,
					  acceptedTraining: Training
                       ]

  /*  static mappedBy = [
                       acceptedOsceDay: 'id',
					   acceptedTraining: 'id'
			  
			 ]*/
}
