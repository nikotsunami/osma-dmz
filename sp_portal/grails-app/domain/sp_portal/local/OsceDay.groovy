package sp_portal.local
import local.*;

class OsceDay {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
    Integer version
    Date osceDate


    static constraints = {
		osceDate nullable: false,unique:true
		
    }
	
	public String toString(){
		
		return osceDate.format("yyyy-MM-dd");
	}
	
	def static findOsceDaysByOsceDate(def osceDate){
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(osceDate);
		calBegin.add(Calendar.MINUTE,-1);
		Date minDate = calBegin.getTime();
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(osceDate);
		calEnd.add(Calendar.MINUTE,1);
		Date maxDate = calEnd.getTime();

		def query = OsceDay.where {
			osceDate < maxDate && osceDate > minDate
		}
	
		def results = query.list(sort:"osceDate")
		return results;
	
	}
	
}
