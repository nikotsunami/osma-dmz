package sp_portal.local
import local.*;

class Nationality {
    static mapping = {
      datasources(['DEFAULT'])
   }

    Integer version
    Long id
    Long origId
    String nationality


    static constraints = {
        nationality nullable: true, maxSize: 40
        version nullable: true
    }

    public String toString(){
        return nationality;
    }
}
