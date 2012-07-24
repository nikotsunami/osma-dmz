package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


class OsceSyncController extends MainController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


	//def allMsg = [];
	def oneMsg = [];
	

    def syncJson(){
	
		def jsonObject = jsonToGroovy(params.data);
		sync(jsonObject)
	}


	def jsonToGroovy(json){
		def jsonObject = JSON.parse(json);		
		return jsonObject ;
	}
	
	private void importMessage(def key){
		oneMsg << ["key":key]
		
	}

    /**
	 *OscdDay synchronise database and Training and validate Patient is present
	 */
    def sync(data){
		
  
		def key = [];
			
		if(data){
 			def locale = Locale.GERMANY;
			if(data.language != JSONObject.NULL){
				if(data.language.equals("en")){
					locale = Locale.US;
				}else if(data.language.equals("de")){
					locale = Locale.GERMANY;
				}
			}
			for(int i = 0; i<data.osceDay.size();i++){
				def day = data.osceDay[i];
				if(day.osceDate != JSONObject.NULL){
					def date = convertToDate(day.osceDate);
					
					def osceDay = local.OsceDay.findByOsceDate(date);
					if(!osceDay){
						osceDay = new local.OsceDay();
						osceDay.osceDate = date;
						osceDay.save();
						key = message(code: 'default.notFound.OsceDay.message', args: [convertToString(date)],locale: locale);
						importMessage(key)
					}else{
						key = message(code: 'default.found.OsceDay.message', args: [convertToString(date)],locale: locale)
						importMessage(key)
					}
				}
			}
			

			for(int i = 0; i<data.trainings.size();i++){
				def jsonTraining = data.trainings[i]; 
			
				if(jsonTraining.timeStart!= JSONObject.NULL&&jsonTraining.trainingDate != JSONObject.NULL){
					def start= convertToDate(jsonTraining.timeStart);
					def date = convertToDate(jsonTraining.trainingDate);
					
					def training = local.Training.findByTrainingDateAndTimeStart(date,start);
					if(!training){
						training = new local.Training();
						training.name = jsonTraining.name;
						training.trainingDate = convertToDate(jsonTraining.trainingDate);
						training.timeStart = convertToDate(jsonTraining.timeStart);
						training.timeEnd = convertToDate(jsonTraining.timeEnd);
						training.save();
						if(start){	
							key = message(code: 'default.notFound.Training.message', args: [convertToString(start)],locale: locale)
						}else{
							key = message(code: 'default.notFound.Training.message', args: [convertTrainingDateToString(Date date)],locale: locale)
						}
						importMessage(key)
					}else{
						//if(training.timeEnd.getTime()  == convertToDate(jsonTraining.timeEnd).getTime() && training.name.equals(jsonTraining.name)){
						if(start){	
							key = message(code: 'default.foundExist.Training.message', args: [convertToString(start)],locale: locale)
						}else{
							key = message(code: 'default.foundExist.Training.message', args: [convertTrainingDateToString(date)],locale: locale)
						}
						importMessage(key)
						//}
												
					/*	else{
								training.name = jsonTraining.name;
								training.timeEnd = convertToDate(jsonTraining.timeEnd);
								training.save();
								key = message(code: 'default.foundNotExist.Training.message', args: [start])
								importMessage(key,[start])
						}
					*/	
						
					}
				}
			
			}
			
			

			
			
			for(int i = 0; i<data.standardizedPatient.size();i++){
				def jsonPatient = data.standardizedPatient[i];
				if(jsonPatient.id != JSONObject.NULL){
					def patient = local.StandardizedPatient.findByOrigId(jsonPatient.id)
					if(!patient){
						key = message(code: 'default.notFound.Patient.message',args:[jsonPatient.preName,jsonPatient.name],locale: locale)
						importMessage(key)
						
					}
				}
			
			}
			 
			//allMsg << ["messages":oneMsg]; 
			
			def osceDayList = local.OsceDay.list();
			
			//allMsg << ["osceDay":osceDayList];
			
			def trainingList = local.Training.list();
			
			//allMsg << ["trainings":trainingList];
			def patientImSemesterList = local.PatientlnSemester.list();
			//allMsg << ["patientInSemester":patientImSemesterList];
			
			//def json = osceDayList as JSON;
			def oneMsgJson = oneMsg as JSON;
			//def osceDayListJson = osceDayList as JSON;
			//def trainingListJson = trainingList as JSON;
			
			//def patientImSemesterListJson = patientImSemesterList as JSON;
			
			
			String osceDayListJson = getOsceDayJson(osceDayList);
			
			String trainingListJson =getTrainingJson(trainingList);
			
			String patientImSemesterListJson = getPatientInSemesterJson(patientImSemesterList)
			
			def jsonStr = "{\"message\" : "+oneMsgJson+",\"osceDay\" :"+osceDayListJson+",\"trainings\" : "+trainingListJson + ",\"patientInSemester\" : "+patientImSemesterListJson + "}"
			render jsonStr
			
		}      
    }    
	
	/**
	 * get the response json data of PatientInSemester 
	 */
	private String getPatientInSemesterJson(def patientImSemesterList){
		String patientImSemesterListJson = "[";
		int count =0;
		for(local.PatientlnSemester semeter : patientImSemesterList){
			count ++;
			String patientJson ="{\"standarizedPatientId\":"+semeter.standardizedPatient.getOrigId()+",";
			patientJson += "\"acceptedTrainings\":"+ getTrainingJson(semeter.acceptedTraining)+",";
			patientJson += "\"acceptedOsce\":"+ getOsceDayJson(semeter.acceptedOsceDay)+",";
			patientJson += "\"accepted\":"+ semeter.accepted;
			patientImSemesterListJson +=patientJson+"}";
			if(count != patientImSemesterList.size()){
				patientImSemesterListJson += ",";
			}
			
		}
		patientImSemesterListJson +="]";
		
		return patientImSemesterListJson;
	
	} 
	
	
	/**
	 * get the response Json data of OsceDay
	 */
	private String getOsceDayJson(def osceDayList){
		String osceDayListJson = "[";
		
		int i = 0; 
		for(  local.OsceDay day : osceDayList){
			i++
			String oneDayJson = "{\"osceDate\":\""+convertToString(day.getOsceDate())+"\"}";
			osceDayListJson+=oneDayJson;
			if(i != osceDayList.size()){
				osceDayListJson+=",";
			}
		}
		osceDayListJson+="]";
		return osceDayListJson;
	}
	
	/**
	 * get the response Json data of Training
	 */
	private String getTrainingJson(def trainingList){
		String trainingListJson = "[";
		
		int i = 0; 
		for( local.Training training : trainingList){
		i++
		String oneTrainingJson = "{\"name\":\""+training.getName()+"\",";
				   oneTrainingJson += "\"trainingDate\":\""+convertToString(training.getTrainingDate())+"\",";
			       oneTrainingJson += "\"timeStart\":\""+convertToString(training.getTimeStart())+"\",";
			       oneTrainingJson += "\"timeEnd\":\""+convertToString(training.getTimeEnd())+"\"}";
			trainingListJson+=oneTrainingJson;
			if(i != trainingList.size()){
				trainingListJson+=",";
			}
		}
		trainingListJson+="]";
		return trainingListJson;
	
	}


	/**
	 *The date of the format string into "yyyy-MM-dd 'T' HH: MM: ss 'Z'" format
	 */
	private Date convertToDate(String dateStr){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		Date date=null;
		try {
			if(dateStr){
				date = sdf.parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
		/**
	 * Time is converted to a string
	 **/
	private String convertToString(Date date){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		String dateStr=null;
		try {
			if(date){
				dateStr = sdf.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	/**
	 * Time is converted to a string
	 **/
	private String convertTrainingDateToString(Date date){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		String dateStr=null;
		try {
			if(date){
				dateStr = sdf.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

}