package sp_portal

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.LogFactory;

import org.joda.time.LocalDate;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import java.util.Locale;

class DataImportExportController extends MainController {

    //def beforeInterceptor = [action:this.&isLoggedInAsAdmin]
	private static final log = LogFactory.getLog(this)
	

	def dataImportExportService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    def test(){

    }

    def push(){
		log.info("user push data")
        redirect(action: "exportSP", id: params.data)
    }


    def exportSP(){
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method exportSP with params : "+params)
		}
        if (params.id){
            local.StandardizedPatient patient = local.StandardizedPatient.findByOrigId(params.id);
           // remote.StandardizedPatient patient = remote.StandardizedPatient.findById(params.id);
		   if(log.isDebugEnabled()){
				StringBuffer sb = new StringBuffer();
				sb.append( "\n origId: ");
				sb.append(patient?.origId);
				sb.append( "\n birthday: ");
				sb.append(patient?.birthday);
				sb.append( "\n city: ");
				sb.append(patient?.city);
				sb.append( "\n email: ");
				sb.append(patient?.email);
				sb.append( "\n gender: ");
				sb.append(patient?.gender);
				sb.append( "\n height: ");
				sb.append(patient?.height);
				sb.append( "\n immagePath: ");
				sb.append(patient?.immagePath);
				sb.append( "\n maritalStatus: ");
				sb.append(patient?.maritalStatus);
				sb.append( "\n mobile: ");
				sb.append(patient?.mobile);
				sb.append( "\n name: ");
				sb.append(patient?.name);
				sb.append( "\n postalCode: ");
				sb.append(patient?.postalCode);
				sb.append( "\n preName: ");
				sb.append(patient?.preName);			
				sb.append( "\n socialInsuranceNo: ");
				sb.append(patient?.socialInsuranceNo);
				sb.append( "\n street ");
				sb.append(patient?.street);
				sb.append( "\n telephone: ");
				sb.append(patient?.telephone);
				sb.append( "\n telephone2: ");
				sb.append(patient?.telephone2);
				sb.append( "\n videoPath: ");
				sb.append(patient?.videoPath);
				sb.append( "\n weight: ");
				sb.append(patient?.weight);
				sb.append( "\n workPermission: ");
				sb.append(patient?.workPermission);
				sb.append( "\n anamnesisForm: ");
				sb.append(patient?.anamnesisForm);
				sb.append( "\n description: ");
				sb.append(patient?.description);
				sb.append( "\n profession: ");
				sb.append(patient?.profession);
				sb.append( "\n nationality: ");
				sb.append(patient?.nationality);
				sb.append( "\n bankaccount: ");
				sb.append(patient?.bankaccount);
				log.debug( "find patient : " + sb.toString());
			}
            if (patient){
			def json = patient as JSON;
			deleteAll(patient)
				
				
						
			
                 render json;
            } else {
                render params.id +"not found"
            }
        }
    }
	
	
	def deleteAll(patient){
		def anamnesisForm = patient.anamnesisForm
				
				def checkValues = anamnesisForm.anamnesisChecksValues
				
				for(local.AnamnesisChecksValue value : checkValues){
					if(value){
						def anamnesisCheckValue = local.AnamnesisChecksValue.get(value.id);
						anamnesisCheckValue.delete()
					}
				}
				
				def scars = anamnesisForm.scars;
				
				for(local.Scar scar : scars){
					scar.delete();
				}
				
				def description = patient.description;
				
				if(description){
					description.delete();
				}
				
				def bankaccount = patient.bankaccount;
				
				if(bankaccount){
					bankaccount.delete();
				}
				
				
				
				def semester = local.PatientlnSemester.findByStandardizedPatient(patient)
				if(semester){
					def osceDays = semester.acceptedOsceDay
					for(local.OsceDay osceday : osceDays){
						if(osceday){
							osceday.delete();
						}
					}
					
					def trainings = semester.acceptedTraining
					for(local.Training training : trainings){
						if(training){
							training.delete();
						}
					}
				}
				
				def user = User.findByStandardizedPatient(patient)
				
				if(user){
					user.delete()
				
				}
				patient.delete();
				
				if(anamnesisForm){
					anamnesisForm.delete();
				}
				
	}
	
	

    def importSP(){
	    try{
			def jsonData = dataImportExportService.importSP(params);
			response.setCharacterEncoding("UTF-8");
			render text:jsonData,contentType:"application/json",encoding:"UTF-8"
		}catch(RuntimeException e){
		
			log.error "IMPORT ERROR",e
			def error = e.getMessage();
			response.setCharacterEncoding("UTF-8");
			render text:error,encoding:"UTF-8"

		}
	
    }


}
