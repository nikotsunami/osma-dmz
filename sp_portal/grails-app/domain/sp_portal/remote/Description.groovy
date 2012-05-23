package sp_portal.remote
import remote.*;

class Description {

    Integer version
    Long id

    static mapping = {
      datasources(['original'])
   }

    String description

    static hasMany = [standardizedPatients: StandardizedPatient]

    static constraints = {
        description nullable: true, maxSize: 999
        version nullable: true
    }
}
