package sp_portal.local
import local.*;

class Scar {
   static mapping = {
      datasources(['DEFAULT'])
   }

    Integer version
    Long id
    Long origId
    String bodypart
    Integer traitType


    static constraints = {
        bodypart nullable: true, maxSize: 60
        version nullable: true
    }
}
