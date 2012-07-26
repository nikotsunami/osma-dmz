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
		timeStart nullable: true
		timeEnd nullable: true, validator: { val, obj ->
        (obj.timeStart!=null&&(val?.after(obj.timeStart)||val==null))||(obj.timeStart==null)
		}	
		trainingDate nullable: false
    }
	
	public String toString(){
		return trainingDate.format("yyyy-MM-dd") + " " + timeStart.format("hh:mm:ss");
	}
}
