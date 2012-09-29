package sp_portal.local
import local.*;
import org.apache.log4j.Logger;

class OsceDay {
 static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
	Integer lunchBreakAfterRotation
	Date lunchBreakStart
	Date timeEnd
	Date timeStart
    Integer value
    Integer version
    Date osceDate
	Osce osce


    static constraints = {
		osceDate nullable: false,unique:true
		lunchBreakAfterRotation nullable:true
		lunchBreakStart nullable:true
		timeEnd nullable:true, validator: { val, obj ->
        if(!((obj.timeStart!=null&&(val?.after(obj.timeStart)||val==null))||(obj.timeStart==null))) return ["timeEndError"]
		
        }
		timeStart nullable:true
		value nullable:true
		version nullable:true
		osceDate nullable:true
		osce nullable:true
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
