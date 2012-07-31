package sp_portal.local
import local.*;
import java.util.Calendar;

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
	
	/**
	 * find trainings by trainingDate(between 1 minute before the trainingDate and 1 minute after the trainingDate) and name
	 **/
	def static findTrainingsByDateAndName(def trainingDate,def name){
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(trainingDate);
		calBegin.add(Calendar.MINUTE,-1);
		Date minDate = calBegin.getTime();
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(trainingDate);
		calEnd.add(Calendar.MINUTE,1);
		Date maxDate = calEnd.getTime();
		def query = Training.where {
			trainingDate < maxDate && trainingDate > minDate && name==name
		}
	
		def results = query.list(sort:"trainingDate")
		return results;
	
	}
	
	/**
	 * find trainings by trainingDate(between 1 minute before the trainingDate and 1 minute after the trainingDate) and timeStart(same as trainingDate)
	 **/
	def static findTraningsByDateAndStart(def trainingDate,def timeStart){
		if(trainingDate == null){
			return null;
		}
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(trainingDate);
		calBegin.add(Calendar.MINUTE,-1);
		Date minDate = calBegin.getTime();
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(trainingDate);
		calEnd.add(Calendar.MINUTE,1);
		Date maxDate = calEnd.getTime();
		
		if(timeStart == null){
			return null;
		}

		Calendar calStartMin = Calendar.getInstance();
		calStartMin.setTime(timeStart);
		calStartMin.add(Calendar.MINUTE,-1);
		Date minStartDate = calStartMin.getTime();
		
		Calendar calEndMax = Calendar.getInstance();
		calEndMax.setTime(timeStart);
		calEndMax.add(Calendar.MINUTE,1);
		Date maxStartDate = calEndMax.getTime();		

		def query = Training.where {
			trainingDate < maxDate && trainingDate > minDate && timeStart < maxStartDate && timeStart > minStartDate
		}
	
		def results = query.list(sort:"trainingDate")
		return results;
	
	}
	
}
