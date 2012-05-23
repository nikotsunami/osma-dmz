package sp_portal.local
import local.*;

class Description {

    Integer version
    Long id
		Long origId

    static mapping = {
      datasources(['DEFAULT'])
   }

    String description

    static hasMany = [standardizedPatients: StandardizedPatient]

    static constraints = {
        description nullable: true, maxSize: 999
        version nullable: true
    }
}
