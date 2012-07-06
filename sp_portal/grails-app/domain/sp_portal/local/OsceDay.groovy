package sp_portal.local
import local.*;

class OsceDay {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
    Integer version
    Date osceDate
    Date timeStart
    Date timeEnd


    static constraints = {
    }
}
