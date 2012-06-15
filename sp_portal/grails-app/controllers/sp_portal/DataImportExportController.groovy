package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class DataImportExportController extends MainController {

    //def beforeInterceptor = [action:this.&isLoggedInAsAdmin]


    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static finders = [ "StandardizedPatient": { id ->local.StandardizedPatient.findByOrigId(id)},
                       "StandardizedPatient.bankaccount":{ id ->local.Bankaccount.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm":{ id ->local.AnamnesisForm.findByOrigId(id)},
                       "StandardizedPatient.description":{ id ->local.Description.findByOrigId(id)},
                       "StandardizedPatient.profession":{ id ->local.Profession.findByOrigId(id)},
                       "StandardizedPatient.nationality":{ id ->local.Nationality.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id ->local.AnamnesisChecksValue.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id ->local.AnamnesisCheck.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisForm":{ id ->local.AnamnesisForm.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.title":{ id ->local.AnamnesisCheck.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.scars":{ id ->local.Scar.findByOrigId(id)},

                       ]
    static creators = [ "StandardizedPatient": { id,jsonData,context->
                                                            def x = new local.StandardizedPatient();
                                                            x.origId = id;
                                                            x.save();
                                                            createUser(x,jsonData);
                                                            return x},
                       "StandardizedPatient.bankaccount":{ id,jsonData,context -> def x = new local.Bankaccount(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm":{ id,jsonData,context -> def x = new local.AnamnesisForm(); x.origId = id; x.save(); return x},
                       "StandardizedPatient.description":{ id,jsonData,context -> def x = new local.Description(); x.origId = id; return x},
                       "StandardizedPatient.profession":{ id,jsonData,context -> def x = new local.Profession(); x.origId = id; return x},
                       "StandardizedPatient.nationality":{ id,jsonData,context -> def x = new local.Nationality(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id,jsonData,context -> def x = new local.AnamnesisChecksValue();
                                                                                                            x.origId = id;

                                                                                                            println("adding post hooks")
                                                                                                            context.postHooks << {

                                                                                                                    def form = finders["StandardizedPatient.anamnesisForm"](context["StandardizedPatient.anamnesisForm"]);
                                                                                                                    x.anamnesisForm = form;

                                                                                                                }



                                                                                                            return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id,jsonData,context -> def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.title":{ id,jsonData,context ->def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.scars":{ id,jsonData,context -> def x = new local.Scar(); x.origId = id; return x},
                       ]
    static def createUser(standardizedPatient,jsonData){
         def x =new User();
         x.userName= jsonData.email;
         x.passwordHash=encodePassword(""+jsonData.socialInsuranceNo,x.userName);
         x.userEmail=jsonData.email;
         x.standardizedPatient=standardizedPatient;
         x.isActive=true;
         def roles = [];
       roles.add(Role.findByRoleName("USER_ROLE"));
       x.roles = roles;
         x.save();
         //return x;


    }


    static exclusions = ["hasOne","validationSkipMap","gormPersistentEntity",
                        "PersistentEntity","properties","metaClass","class","id","dirty",
                         "count","dirtyPropertyNames","validationErrorsMap","errors", "hasMany","gormDynamicFinders",
                         "all", "attached" ,"domainClass","constraints","mapping","belongsTo",
                         "descriptionId", "professionId","bankaccountId","mappedBy","nationalityId","anamnesisFormId","anamnesisCheckId"];



    def exportSP(){
           if (params.id){
              local.StandardizedPatient patient = local.StandardizedPatient.findByOrigId(params.id);
           // remote.StandardizedPatient patient = remote.StandardizedPatient.findById(params.id);
            if (patient){
                render patient as JSON;
            } else {
                render params.id +"not found"
            }
        }
    }





    def importSP(){
                println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (params.data){
            String data = params.data;
            data = preProcessData(data);
            def jsonObject = JSON.parse(data);
                preProcessData(jsonObject);
            syncData(new JSONObject(jsonObject));
            render jsonObject.email;
        } else {
                render "No data"

        }
    }


    private String preProcessData(String data){
            data = data.replaceAll("bankAccount", "bankaccount");
            data = data.replaceAll("BIC", "bic");
            data = data.replaceAll("IBAN", "iban");
            data = data.replaceAll("anamnesischecksvalues", "anamnesisChecksValues");
            data = data.replaceAll("anamnesischeck", "anamnesisCheck");
            data = data.replaceAll("anamnesisform", "anamnesisForm");


            return data;
    }

    private void preProcessData(jsonObject){

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
                      int status = 0;
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
                            jsonObject.put("maritalStatus",status);
                    }
             }

             String workPermission = jsonObject.get("workPermission");
            if(jsonObject.containsKey("workPermission")){
                    if(workPermission){
                        jsonObject.remove("workPermission");
                      int status = 0;
                    if (workPermission.toUpperCase().equals("B")) {
                                status = 0;
                            } else if(workPermission.toUpperCase().equals("L")){
                                status = 1;
                            } else if(workPermission.toUpperCase().equals("C")){
                                status = 2;
                            }
                            jsonObject.put("workPermission",status);
                    }
             }

    }




    /**
     * Syncs the data for everything below SP
     */
    private boolean syncData(jsonObject){
        println( "syncData()");
        def context = [:] ;
        context.postHooks = [];

        syncOneClass(jsonObject, "StandardizedPatient", context);

         context.postHooks.each{ hook -> hook() ; println("called") }

    }


         private int anamnesisChecksTypeTransformet(String type){

                    if (type.toUpperCase().equals("QUESTION_MULT_M")) {
                        return 0;
                    } else if(type.toUpperCase().equals("QUESTION_MULT_S")){
                        return 1;
                    } else if(type.toUpperCase().equals("QUESTION_OPEN")){
                        return 2;
                    } else if(type.toUpperCase().equals("QUESTION_TITLE")){
                        return 3;
                    } else if(type.toUpperCase().equals("QUESTION_YES_NO")){
                        return 4;
                    }  else {
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

    private def syncOneClass(jsonObject, datapath, contextIds ){



        if (!jsonObject || (jsonObject == JSONObject.NULL)  || !jsonObject.id){
            return null;
        }

        // locate the class in the db
        def sp = finders[datapath](jsonObject.id);

        contextIds[datapath] = jsonObject.id;

        if (!sp) {

            if (creators[datapath]){

                sp = creators[datapath](jsonObject.id, jsonObject, contextIds);

            } else {

                return null;
            }
        }


        // loop over all the proerties in the class
        sp.metaPropertyValues.each{ prop ->


            // avoid the read only properties
            if(exclusions.find {it == prop.name}) return


                // if it is a basic type
                if ([Long, String, Integer, Boolean, Short].contains( prop.type)){

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
                            if ( jsonObject[prop.name] && (jsonObject[prop.name] != JSONObject.NULL)){
                                sp[prop.name] = jsonObject.get(prop.name);
                            } else {

                                if (sp[prop.name]  && !jsonObject[prop.name]){

                                }

                            }

                            if (sp[prop.name].equals(jsonObject[prop])){

                            }else{

                            }
                        }
                  }

                } else {


                   // not a basic type
                   if (Date != prop.type){

                       def fieldFinder = finders[datapath+"."+prop.name];

                       if (fieldFinder){
                            // known Entity relationship

                            if (prop.type != Set){
                                                            //  1 to 1 relationship
                                logIf(debugCase() , " 1 to 1 case")
                                // Are the ids the same?
                                // Confirm the property value has an id field so it is a db entity
                                if ((jsonObject[prop.name] != JSONObject.NULL) && jsonObject[prop.name]?.id ){

                                    // Yes good so sync the fields



                                        def v = syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name, contextIds );

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
                                //  one to many relationship, so clear the current values and reinitialize from JSON data


                                    sp[prop.name] = []

                                    //
                                    def array = jsonObject[prop.name];

                                    if (array){
                                        array.each{ member ->

                                              // No then overwrite
                                              def newValue = syncOneClass(member,datapath+"."+prop.name, contextIds );

                                               if (newValue){

                                                    sp[prop.name].add(newValue);
                                                } else {


                                               }

                                        };
                                }
                            }

                        } else {

                        }
                    } else {

                            DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                            Date date=null;
                            try {
                                date = sdf.parse(jsonObject.get(prop.name));

                                sp[prop.name] = date;
                            } catch (ParseException e) {
                                    e.printStackTrace();
                            }


                    }

                }

        }    // end loop over all the proerties in the class
        sp.save();
        return sp;

    }


    private def logIf(condition, message){
        if (condition){
            printf(">" + message );
        }
    }

}
