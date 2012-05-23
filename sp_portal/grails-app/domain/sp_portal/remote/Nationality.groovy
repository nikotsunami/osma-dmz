package sp_portal.remote
import remote.*;

class Nationality {
    static mapping = {
      datasources([ 'original'])
   }

    Integer version
    Long id
    String nationality


    static constraints = {
        nationality nullable: true, maxSize: 40
        version nullable: true
    }
}
