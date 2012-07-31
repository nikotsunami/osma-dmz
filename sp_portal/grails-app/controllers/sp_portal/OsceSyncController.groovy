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
			//get locale from osce
 			def locale = Locale.GERMANY;
			if(data.language != JSONObject.NULL){
				if(data.language.equals("en")){
					locale = Locale.US;
				}else if(data.language.equals("de")){
					locale = Locale.GERMANY;
				}else if(data.language.equals("fr")){
					locale = Locale.FRANCE;
				}else if(data.language.equals("it")){
					locale = Locale.ITALY;
				}
			}
			//Synchronise OsceDay
            for(int i = 0; i<data.osceDay.size();i++){
                def day = data.osceDay[i];
                if(day.osceDate != null && !day.osceDate.equals("")){
                    def date = convertToDate(day.osceDate);
                    def osceDay = local.OsceDay.findOsceDaysByOsceDate(date);
                    if(!osceDay){
                        osceDay = new local.OsceDay();
                        osceDay.osceDate = date;
                        osceDay.save(flush:true);
						key = message(code: 'default.notFound.OsceDay.message', args: [convertToString(date)],locale: locale);
						println(">>>>>>key = "+key);
						importMessage(key)
					}else{
						key = message(code: 'default.found.OsceDay.message', args: [convertToString(date)],locale: locale)
                        importMessage(key)
                    }
                }else{
					
					key = message(code: 'default.cannotSave.OsceDay.message',locale: locale)
					importMessage(key)
				}
            }

			//Synchronise Training
            for(int i = 0; i<data.trainings.size();i++){
                def jsonTraining = data.trainings[i];

                if(jsonTraining.trainingDate != JSONObject.NULL){
                    def start= convertToDate(jsonTraining.timeStart);
                    def date = convertToDate(jsonTraining.trainingDate);
					def name = jsonTraining.name;
					def training = null;
					if(date){
						if(start){
							training = local.Training.findTraningsByDateAndStart(date,start);
						}else{
							training = local.Training.findTrainingsByDateAndName(date,name);							
						}
						if(!training){
							if(jsonTraining.name && jsonTraining.trainingDate && !jsonTraining.name.equals("") && !jsonTraining.trainingDate.equals("")){
								training = new local.Training();
								training.name = jsonTraining.name;
								training.trainingDate = convertToDate(jsonTraining.trainingDate);
								training.timeStart = convertToDate(jsonTraining.timeStart);
								training.timeEnd = convertToDate(jsonTraining.timeEnd);
								training.save(flush:true);
								if(start){
									key = message(code: 'default.notFound.Training.message', args: [convertToString(start)],locale: locale)
								}else{
									key = message(code: 'default.notFound.Training.message', args: [convertTrainingDateToString(date)],locale: locale)
								}
							}else{
								key = message(code: 'default.cannotSave.Training.message',locale: locale)
							}
							importMessage(key)
						}else{
							if(start){	
								key = message(code: 'default.foundExist.Training.message', args: [convertToString(start)],locale: locale)
							}else{
								key = message(code: 'default.foundExist.Training.message', args: [convertTrainingDateToString(date)],locale: locale)
							}
							importMessage(key)
							
						}
					}else{
						key = message(code: 'default.cannotSave.Training.message',locale: locale)
						importMessage(key)
					}
                }

            }




			//Validate standardizedPatient is present
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
			 
	 
			//set json date and send to OSCE
			def osceDayList = local.OsceDay.list();
			
			def trainingList = local.Training.list();
			
			def patientImSemesterList = local.PatientlnSemester.list();
	
			def oneMsgJson = oneMsg as JSON;
			

			
			String osceDayListJson = getOsceDayJson(osceDayList);
			
			String trainingListJson =getTrainingJson(trainingList);
			
			String patientImSemesterListJson = getPatientInSemesterJson(patientImSemesterList)
			
			def jsonStr = "{\"message\" : "+oneMsgJson+",\"osceDay\" :"+osceDayListJson+",\"trainings\" : "+trainingListJson + ",\"patientInSemester\" : "+patientImSemesterListJson + "}"
           
		    println("in DMZ jsonStr = "+jsonStr);
			response.setCharacterEncoding("UTF-8");
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
			if(dateStr && !dateStr.equals("")){
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

		String dateStr="";
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