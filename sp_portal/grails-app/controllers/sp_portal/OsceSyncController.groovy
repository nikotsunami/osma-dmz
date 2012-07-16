package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



class OsceSyncController extends MainController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


	def allMsg = [];
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

			for(int i = 0; i<data.osceDay.size();i++){
				def day = data.osceDay[i];
				if(day.osceDate != JSONObject.NULL){
					def date = convertToDate(day.osceDate);
					
					def osceDay = local.OsceDay.findByOsceDate(date);
					if(!osceDay){
						osceDay = new local.OsceDay();
						osceDay.osceDate = date;
						osceDay.save();
					
						key = message(code: 'default.notFound.OsceDay.message', args: [date])
						importMessage(key)
					}else{
					
						key = message(code: 'default.found.OsceDay.message', args: [date])
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

						key = message(code: 'default.notFound.Training.message', args: [start])
						importMessage(key)
					}else{
						if(training.timeEnd.getTime()  == convertToDate(jsonTraining.timeEnd).getTime() && training.name.equals(jsonTraining.name)){
								key = message(code: 'default.foundExist.Training.message', args: [start])
								importMessage(key)
						}else{
								training.name = jsonTraining.name;
								training.timeEnd = convertToDate(jsonTraining.timeEnd);
								training.save();
								key = message(code: 'default.foundNotExist.Training.message', args: [start])
								importMessage(key)
						}
					}
				}
			
			}
			
			

			
			
			for(int i = 0; i<data.standardizedPatient.size();i++){
				def jsonPatient = data.standardizedPatient[i];
				if(jsonPatient.id != JSONObject.NULL){
					def patient = local.StandardizedPatient.findByOrigId(jsonPatient.id)
					if(!patient){
						key = message(code: 'default.notFound.Patient.message',args:[jsonPatient.preName,jsonPatient.name])
						importMessage(key)
						
					}
				}
			
			}
			println(">>>>>oneMsg: "+oneMsg)
			allMsg << ["messages":oneMsg]; 
			
			def osceDayList = local.OsceDay.list();
			
			allMsg << ["osceDay":osceDayList];
			
			def trainingList = local.Training.list();
			println(">>>>>trainingList: "+trainingList);
			allMsg << ["trainings":trainingList];
			
			def patientImSemesterList = local.PatientlnSemester.list();
			allMsg << ["patientInSemester":patientImSemesterList];
			
			def json = osceDayList as JSON;
			def oneMsgJson = oneMsg as JSON;
			def osceDayListJson = osceDayList as JSON;
			def trainingListJson = trainingList as JSON;
			def patientImSemesterListJson = patientImSemesterList as JSON;
			
			def jsonStr = "{\"messege\" : "+oneMsgJson+",\"osceDay\" :"+osceDayListJson+",\"trainings\" : "+trainingListJson + ",\"patientInSemester\" : "+patientImSemesterListJson + "}"
			
			render jsonStr
			
		}      
    }    


	/**
	 *The date of the format string into "yyyy-MM-dd 'T' HH: MM: ss 'Z'" format
	 */
	private Date convertToDate(String dateStr){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}