package sp_portal.remote
import remote.*;

class Scar {
   static mapping = {
      datasources([ 'original'])
   }

    Integer version
    Long id

    String bodypart
    Integer traitType


    static constraints = {
        bodypart nullable: true, maxSize: 60
        version nullable: true
    }
}
