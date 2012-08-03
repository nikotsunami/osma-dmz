package sp_portal.local
import local.*;
import org.apache.log4j.Logger;

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
		
		return osceDate?.format("yyyy-MM-dd");
	}
	private static Logger log = Logger.getLogger(OsceDay.class);
	/**
	 * find osceDays by osceDate(between 1 minute before the osceDate and 1 minute after the osceDate)
	 **/
	def static findOsceDaysByOsceDate(def osceDate){
		if(log.isTraceEnabled()){
			log.trace(">> In class OsceDay Method findOsceDaysByOsceDate entered osceDate : "+osceDate)
		}
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
		if(log.isTraceEnabled()){
			log.trace("<< In class OsceDay Method findOsceDaysByOsceDate return results : "+results)
		}
		return results;
	
	}
	
}
