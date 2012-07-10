package sp_portal.local
import local.*;

class Training {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
    Integer version
    String name
    Date trainingDate
    Date timeStart
    Date timeEnd

    static constraints = {
		name nullable: false, validator: {val,obj -> !val?.equals("")}
		timeStart nullable: false
		timeEnd nullable: false, validator: { val, obj ->
        val?.after(obj.timeStart)
		}	
		trainingDate nullable: false
    }
}
