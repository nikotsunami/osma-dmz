package sp_portal.local
import local.*;

class Profession {
    static mapping = {
      datasources(['DEFAULT'])
   }

    Integer version
    Long id
        Long origId
    String profession


    static constraints = {
        profession nullable: true, maxSize: 60
        version nullable: true
    }

    public String toString(){
        return profession;
    }
}
