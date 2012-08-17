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
	
	def messageSource

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static finders = [ "StandardizedPatient": { id ->local.StandardizedPatient.findByOrigId(id)},
                       "StandardizedPatient.bankaccount":{ id ->local.Bankaccount.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm":{ id ->local.AnamnesisForm.findByOrigId(id)},
                       "StandardizedPatient.description":{ id ->local.Description.findByOrigId(id)},
                       "StandardizedPatient.profession":{ id ->local.Profession.findByOrigId(id)},
                       "StandardizedPatient.nationality":{ id ->local.Nationality.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id ->local.AnamnesisChecksValue.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id ->local.AnamnesisCheck.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.anamnesisCheckTitle":{ id ->local.AnamnesisCheckTitle.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.title":{ id ->local.AnamnesisCheck.findByOrigId(id)},

                       "StandardizedPatient.anamnesisForm.scars":{ id ->local.Scar.findByOrigId(id)},

                       ]
    static creators = [ "StandardizedPatient": { id,jsonData,context->
                                                            def x = new local.StandardizedPatient();
                                                            x.origId = id;
                                                            x.save(flush:true);
															if (x.hasErrors()) {			
																x.errors?.allErrors?.each{ 
																	try{
																		String msg = messageSource.getMessage(it, locale)
																		context.errors << msg;	
																	}catch(Exception e){
																		log.error "Get the sync field error: "+"{e.message}",e
																	}
																};
																	
															}	
                                                            createUser(x,jsonData);
                                                            return x},
                       "StandardizedPatient.bankaccount":{ id,jsonData,context -> def x = new local.Bankaccount(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm":{ id,jsonData,context -> def x = new local.AnamnesisForm(); x.origId = id; x.save(flush:true); return x},
                       "StandardizedPatient.description":{ id,jsonData,context -> def x = new local.Description(); x.origId = id; return x},
                       "StandardizedPatient.profession":{ id,jsonData,context -> def x = new local.Profession(); x.origId = id; return x},
                       "StandardizedPatient.nationality":{ id,jsonData,context -> def x = new local.Nationality(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id,jsonData,context -> def x = new local.AnamnesisChecksValue();
                                                                                                            x.origId = id;
                                                                                                            context.postHooks << {
                                                                                                                    def form = finders["StandardizedPatient.anamnesisForm"](context["StandardizedPatient.anamnesisForm"]);
                                                                                                                    x.anamnesisForm = form;
                                                                                                                }
                                                                                                            return x},

                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.anamnesisCheckTitle":{ id,jsonData,context -> def x = new local.AnamnesisCheckTitle(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id,jsonData,context -> def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.title":{ id,jsonData,context ->def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.scars":{ id,jsonData,context -> def x = new local.Scar(); x.origId = id; return x},
                        ]
    static def createUser(standardizedPatient,jsonData){
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method createUser entered standardizedPatient : "+standardizedPatient + "  jsonData : "+jsonData)
		}
        def x =new User();
        x.userName= jsonData.email;
        x.passwordHash=encodePassword(""+jsonData.socialInsuranceNo,x.userName);
        x.userEmail=jsonData.email;
        x.standardizedPatient=standardizedPatient;
        x.isActive=true;
        def roles = [];
        roles.add(Role.findByRoleName("USER_ROLE"));
        x.roles = roles;
        x.save(flush:true);
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n userName: ");
			sb.append(x?.userName);
			sb.append( "\n passwordHash: ");
			sb.append(x?.passwordHash);
			sb.append( "\n userEmail: ");
			sb.append(x?.userEmail);
			sb.append( "\n standardizedPatient: ");
			sb.append(x?.standardizedPatient);
			sb.append( "\n isActive: ");
			sb.append(x?.isActive);
			sb.append( "\n roles: ");
			sb.append(x?.roles);
			log.debug( "new user : " + sb.toString());
		}

    }


    static exclusions = ["hasOne","validationSkipMap","gormPersistentEntity",
                        "PersistentEntity","properties","metaClass","class","id","dirty",
                         "count","dirtyPropertyNames","validationErrorsMap","errors", "hasMany","gormDynamicFinders",
                         "all", "attached" ,"domainClass","constraints","mapping","belongsTo",
                         "descriptionId", "professionId","bankaccountId","mappedBy","nationalityId","anamnesisFormId","anamnesisCheckId"];


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
                 render patient as JSON;
            } else {
                render params.id +"not found"
            }
        }
    }

    def importSP(){
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method importSP with params.data : "+params.data)
		}
        if (params.data){
			
            String data = params.data;
            try{
                data = preProcessData(data);
                def jsonObject = JSON.parse(data);
				def jsonPatient = jsonObject["StandardizedPatient"];
				if(jsonPatient != JSONObject.NULL){
				if(log.isDebugEnabled()){
					log.debug( "parse data to jsonObject : "+jsonObject);
				}
					
					preProcessData(jsonPatient);
				
					def jsonData = syncData(new JSONObject(jsonObject)) as JSON;				
					def json =jsonData.toString();
					json = json.substring(1,json.length()-1);
					
					response.setCharacterEncoding("UTF-8");
					render text:jsonData,contentType:"application/json",encoding:"UTF-8"
				}
			}catch(JSONException e){
			 
			  render text:"Get Json Object Error: "+e.getMessage(), status:500
			}
	
            
        } else {
                render "No data"

        }
    }


    private String preProcessData(String data){
			log.info("preProcessData data called")
            data = data.replaceAll("bankAccount", "bankaccount");
            data = data.replaceAll("BIC", "bic");
            data = data.replaceAll("IBAN", "iban");
            data = data.replaceAll("anamnesischecksvalues", "anamnesisChecksValues");
            data = data.replaceAll("anamnesischeck", "anamnesisCheck");
            data = data.replaceAll("anamnesisform", "anamnesisForm");
            data = data.replaceAll("sort_order", "sortOrder");
            data = data.replaceAll("descriptions", "description");

			if(log.isTraceEnabled()){
				log.trace("<< In class DataImportExportController Method preProcessData(String data) return data : "+data)
			}
            return data;
    }

    private void preProcessData(jsonObject)throws JSONException{
			log.info("preProcessData jsonObject called")
            String gender = jsonObject.get("gender");
            if(jsonObject.containsKey("gender")){
                if(gender){
                    jsonObject.remove("gender");
                      if(gender.toLowerCase().equals("male")){
                            jsonObject.put("gender",0);
                    }else if(gender.toLowerCase().equals("female")){
                          jsonObject.put("gender",1);
                    }
                }
            }

            String maritalStatus = jsonObject.get("maritalStatus");
            if(jsonObject.containsKey("maritalStatus")){
                    if(maritalStatus){
                        jsonObject.remove("maritalStatus");
                      int status = -1;
                    if (maritalStatus.toUpperCase().equals("UNMARRIED")) {
                                status = 0;
                            } else if(maritalStatus.toUpperCase().equals("MARRIED")){
                                status = 1;
                            } else if(maritalStatus.toUpperCase().equals("CIVIL_UNION")){
                                status = 2;
                            } else if(maritalStatus.toUpperCase().equals("DIVORCED")){
                                status = 3;
                            } else if(maritalStatus.toUpperCase().equals("SEPARATED")){
                                status = 4;
                            } else if(maritalStatus.toUpperCase().equals("WIDOWED")){
                                status = 5;
                            }

                            if (status == -1){
                                jsonObject.put("maritalStatus",null);
                            } else {
                                jsonObject.put("maritalStatus",status);
                            }
                    }
             }

             String workPermission = jsonObject.get("workPermission");
            if (jsonObject.containsKey("workPermission")){
                    if(workPermission){
                        jsonObject.remove("workPermission");
                      int status = -1;
                        if (workPermission.toUpperCase().equals("B")) {
                                    status = 0;
                        } else if(workPermission.toUpperCase().equals("L")){
                            status = 1;
                        } else if(workPermission.toUpperCase().equals("C")){
                            status = 2;
                        }

                        if (status == -1){
                            jsonObject.put("workPermission",null);
                        } else {
                            jsonObject.put("workPermission",status);
                        }
                    }
             }
			if(log.isTraceEnabled()){
				log.trace("<< In class DataImportExportController Method preProcessData(jsonObject) end  jsonObject: "+jsonObject)
			}
    }

	def locale;
	
    /**
     * Syncs the data for everything below SP
     */
    private def syncData(jsonObject){
		log.info("sync jsonObject")
        def context = [:] ;
        context.postHooks = [];
		context.errors = [];
		
		locale = getLocale(jsonObject);
		def jsonPatient = jsonObject["StandardizedPatient"];
        syncOneClass(jsonPatient, "StandardizedPatient", context);

        context.postHooks.each{ hook -> hook() ;  }
		def errorMsg =[:]
		errorMsg.errors = [];
		for(String error : context.errors){
			errorMsg.errors << ["error":error];
		}

		return errorMsg

    }
	
	/**
	 * get the Locale from jsonObject
	 */
	private def getLocale(jsonObject){
		def locale = Locale.GERMANY;
		def jsonLanguage = jsonObject["languages"];
		if(jsonLanguage["language"] != JSONObject.NULL){	
			locale = new Locale(jsonLanguage["language"].toString())
		}
	}

    private int anamnesisChecksTypeTransformet(String type){

            if (type.toUpperCase().equals("QUESTION_OPEN")) {
                return 0;
            } else if(type.toUpperCase().equals("QUESTION_YES_NO")){
                return 1;
            } else if(type.toUpperCase().equals("QUESTION_MULT_S")){
                return 2;
            } else if(type.toUpperCase().equals("QUESTION_MULT_M")){
                return 3;
            }else {
                return -1;
            }
    }
    private int traitTypeTransformet(String type){

            if(type.toUpperCase().equals("SCAR")){
                return 0;
            }else if(type.toUpperCase().equals("TATTOO")){
                return 1;
            }else if(type.toUpperCase().equals("NOT_TO_EXAMINE")){
                return 2;
            }else{
                return -1;
            }

    }

    private def syncOneClass(jsonObject, datapath, contextIds)throws JSONException{
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method syncOneClass(jsonObject, datapath, contextIds ) with entered datapath : "+datapath + " contextIds : "+contextIds)
		}


        if (!jsonObject || (jsonObject == JSONObject.NULL)  || !jsonObject.id){
            return null;
        }

        // locate the class in the db
        def sp = finders[datapath](jsonObject.id);
		if(log.isDebugEnabled()){
			log.debug("locate the class sp in the db : "+sp)
		}
        contextIds[datapath] = jsonObject.id;

        if (!sp) {

            if (creators[datapath]){

                sp = creators[datapath](jsonObject.id, jsonObject, contextIds);

            } else {

                return null;
            }
			if(log.isDebugEnabled()){
				log.debug("sp not found and create sp : "+sp)
			}
        }


        // loop over all the proerties in the class
		log.info("loop over all the proerties in the class")
        sp.metaPropertyValues.each{ prop ->

            // avoid the read only properties
            if(exclusions.find {it == prop.name}) return


                // if it is a basic type
                if ([Long, String, Integer, Boolean, Short].contains( prop.type)){
					if(log.isDebugEnabled()){
						log.debug("it is a basic type proerties : "+prop.name)
					}
                    // if the json data contains this property
                    if(jsonObject.containsKey(prop.name)) {


                        if(prop.name.toUpperCase().equals("TRAITTYPE")){
                             int type = traitTypeTransformet(jsonObject.get(prop.name));
                             if(type!=-1){
                                sp[prop.name] = type;
                             }
                        }else if(prop.name.toUpperCase().equals("TYPE")){
                             int type = anamnesisChecksTypeTransformet(jsonObject.get(prop.name));
                             if(type!=-1){
                                sp[prop.name] = type;
                             }
                        }else{


                            // set the value to the one from JSON
                            if ( (jsonObject[prop.name] != JSONObject.NULL)){
                                sp[prop.name] = jsonObject.get(prop.name);
                            } else {

                                if (sp[prop.name]  && !jsonObject[prop.name]){


                                }

                            }
                            if (sp[prop.name].equals(jsonObject[prop])){

                            }else{

                            }
                        }
						if(log.isDebugEnabled()){
							log.debug("sp[prop.name] : "+sp[prop.name])
						}
					}else{
						if(log.isDebugEnabled()){
							log.debug("jsonObject not containsKey "+prop.name)
						}
					}
					
                } else {

                   // not a basic type
				   if(log.isDebugEnabled()){
						log.debug("it is not a basic type proerties name : "+prop.name)
						log.debug("proerties type : "+prop.type)
				   }
                   if (Date != prop.type && LocalDate != prop.type){
                        def fieldFinder = finders[datapath+"."+prop.name];
						if(log.isDebugEnabled()){
							log.debug("find field "+fieldFinder)
						}
                       if (fieldFinder){

							// known Entity relationship

                            if (prop.type != Set){
								log.info("proerties type is 1 to 1")
                                //  1 to 1 relationship
                                // Are the ids the same?
                                // Confirm the property value has an id field so it is a db entity
                                if ((jsonObject[prop.name] != JSONObject.NULL) && jsonObject[prop.name]?.id ){

                                    // Yes good so sync the fields


                                        def v = syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name, contextIds);

                                        sp[prop.name] = v;

                                } else {
                                    // No then overwrite
                                      def newValue = syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name, contextIds);

                                      if (newValue){

                                            sp[prop.name] = newValue;
                                            // do we need to delete the original // maybe cause difficult problems.
                                      }
                                }



                            } else {
									log.info("proerties type is 1 to m")
                                //  one to many relationship, so clear the current values and reinitialize from JSON data


                                    sp[prop.name] = []

                                    //
                                    def array = jsonObject[prop.name];

                                    if (array){
                                        array.each{ member ->

                                              // No then overwrite
                                              def newValue = syncOneClass(member,datapath+"."+prop.name, contextIds);

                                               if (newValue){

                                                    sp[prop.name].add(newValue);
                                                } else {


                                               }

                                        };
                                }
                            }

                        }else{							
								log.info("not founnd field")							
						}
                    } else {
                           
                            if(LocalDate == prop.type){
                                if(jsonObject[prop.name] != JSONObject.NULL){
                                    LocalDate localDate =null;
                                    if(jsonObject[prop.name].getClass() == Date){
                                        localDate = new LocalDate(jsonObject[prop.name].getTime());
                                    }else{
                                        Date date = convertStringToDate(jsonObject.get(prop.name),"yyyy-MM-dd");
                                        if(date){
                                            localDate = new LocalDate(date.getTime());
                                        }
                                    }
                                    sp[prop.name] = localDate;
                                }
                                return;
                            }

							if(jsonObject.get(prop.name).getClass() == Date){
                                    sp[prop.name] = jsonObject.get(prop.name);
                            }else{
                                sp[prop.name] = convertStringToDate(jsonObject.get(prop.name),"yyyy-MM-dd'T'HH:mm:ss'Z'");
                            }
							if(log.isDebugEnabled()){
								log.debug("sp[prop.name] : "+sp[prop.name])
							}
                    }

                }

        }    // end loop over all the proerties in the class

        sp.save(flush:true);
		addErrorMessages(sp,contextIds)
		
        return sp;

    }
	
	/**
     * Add error message which field of the synchronization fails
	 */
	private void addErrorMessages(instance,context){
			if(log.isTraceEnabled()){
				log.trace(">> In class DataImportExportController Method addErrorMessages entered instance : "+instance + " context : "+context)
			}
			if (instance.hasErrors()) {			
				instance.errors?.allErrors?.each{ 
					try{
						String msg = messageSource.getMessage(it, locale)
						context.errors << msg;
					}catch(Exception e){
						log.error "Get the sync field error: "+"{e.message}",e
					}
				};
					
			}	
	}
	 
	//"yyyy-MM-dd'T'HH:mm:ss'Z'"
	private Date convertStringToDate(String dateStr,String format){
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method convertStringToDate entered dateStr : "+dateStr)
		}
		DateFormat sdf=new SimpleDateFormat(format);

        Date date=null;
        try {
            if(dateStr){
                date = sdf.parse(dateStr);
            }
        } catch (ParseException e) {
            log.error "Date format in JSON string incorrect. Date string was :"+dateStr+" ${e.message}", e
        }
		if(log.isTraceEnabled()){
			log.trace(">> In class DataImportExportController Method convertStringToDate return date : "+date)
		}
        return date;
    }


    private def logIf(condition, message){
        if (condition){
            log(">" + message );
        }
    }

}
