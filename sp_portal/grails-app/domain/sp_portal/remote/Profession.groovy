package sp_portal.remote
import remote.*;

class Profession {
    static mapping = {
      datasources(['original'])
   }

    Integer version
    Long id

    String profession


    static constraints = {
        profession nullable: true, maxSize: 60  
        version nullable: true
    }
}
