package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;

class DataImportExportController extends MainController {

    //def beforeInterceptor = [action:this.&isLoggedInAsAdmin]
   

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static finders = [ "StandardizedPatient": { id ->local.StandardizedPatient.findByOrigId(id)},
                       "StandardizedPatient.bankaccount":{ id ->local.Bankaccount.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm":{ id ->local.AnamnesisForm.findByOrigId(id)},
                       "StandardizedPatient.description":{ id ->local.Description.findByOrigId(id)},
                       "StandardizedPatient.profession":{ id ->local.Profession.findByOrigId(id)},
                       "StandardizedPatient.nationality":{ id ->local.Nationality.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id ->local.AnamnesisChecksValue.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.anamnesisCheckTitle":{ id ->local.AnamnesisCheckTitle.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id ->local.AnamnesisCheck.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.scars":{ id ->local.Scar.findByOrigId(id)},
                       ]
    static creators = [ "StandardizedPatient": { id,jsonData-> 
    																					 				def x = new local.StandardizedPatient();
    																					 				x.origId = id; 
    																					 				x.save(); 
    																					 				createUser(x,jsonData);
    																					 				return x},
                       "StandardizedPatient.bankaccount":{ id,jsonData -> def x = new local.Bankaccount(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm":{ id,jsonData -> def x = new local.AnamnesisForm(); x.origId = id; return x},
                       "StandardizedPatient.description":{ id,jsonData -> def x = new local.Description(); x.origId = id; return x},
                       "StandardizedPatient.profession":{ id,jsonData -> def x = new local.Profession(); x.origId = id; return x},
                       "StandardizedPatient.nationality":{ id,jsonData -> def x = new local.Nationality(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id,jsonData -> def x = new local.AnamnesisChecksValue(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck.anamnesisCheckTitle":{ id,jsonData -> def x = new local.AnamnesisCheckTitle(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id,jsonData -> def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.scars":{ id,jsonData -> def x = new local.Scar(); x.origId = id; return x},
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
                         "all", "attached" ,"domainClass","constraints","mapping",
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



/*


{"class":"sp_portal.local.StandardizedPatient",
        "anamnesisForm":{"class":"sp_portal.local.AnamnesisForm",
                        "anamnesisChecksValues":[{"class":"sp_portal.local.AnamnesisChecksValue",
                                                    "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":3,"sortOrder":10,"text":"Nehmen Sie zurzeit regelmässig Medikamente ein?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":12,"sortOrder":9,"text":"Treatment history category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":"","anamnesisCheckTitle":null},
                                                            "anamnesisChecksValue":null,
                                                            "anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},
                                                            "comment":null,"id":9,"truth":false},
                                                 {"class":"sp_portal.local.AnamnesisChecksValue",
                                                     "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":101,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":"",
                                                            "anamnesisCheckTitle":null},
                                                            
                                                            "anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":8,"truth":true},
                                                 {"class":"sp_portal.local.AnamnesisChecksValue",
                                                 			
                                                     "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie 222?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":101,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":"JDJDJDDJDJDJ",
                                                            "anamnesisCheckTitle":null},
                                                            "anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":80,"truth":true}
                                                ],
                                                

        "createDate":new Date(1279382400000),
        "id":3,
        "scars":[],
        "standardizedPatients":[
                {"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},
                "bankaccount":{"class":"sp_portal.local.Bankaccount",
                                                "bankName":"Basler Kantonalbank",
                                                "bic":"GENODEF1JEV",
                                                "city":SHanghai,
                                                "iban":"CH46 3948 4853 2029 3",
                                                "id":51,
                                                "ownerName":null,
                                                "postalCode":null,
                                                "standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]
                                                },
        "birthday":new Date(-484560000000),
        "city":"Metz",
        "description":null,
        "email":"ppp@rrr.com",
        "gender":1,
        "height":162,
        "immagePath":null,
        "maritalStatus":null,
        "mobile":"078 427 24 85",
        "name":"Lamarie",
        "nationality":{"class":"sp_portal.local.Nationality",
                        "nationality":"Frankreich","id":7},
        "id":2,
        "postalCode":4057,
        "preName":"Marianne",
        "profession":{"class":"sp_portal.local.Profession","id":3,"profession":"Bauarbeiter/in"},
        "socialInsuranceNo":"qqq",
        "street":"Feldbergstrasse 145",
        "telephone":null,
        "telephone2":";kjlkjlj",
        "videoPath":"hello nick",
        "weight":57,
        "workPermission":null}

*/



// "birthday":new Date(-484560000000)
// "createDate":new Date(1279382400000)
		

    def importSP(){
 
        if (params.data){
             def jsonObject = JSON.parse(params.data);
            syncData(new JSONObject(jsonObject));
            render jsonObject.email;
        } else {
                render "No data"

        }
    }

    /**
     * Syncs the data for everything below SP
     */
    private boolean syncData(jsonObject){
        println( "syncData()");
        syncOneClass(jsonObject, "StandardizedPatient" );

    }


  
    


    private def syncOneClass(jsonObject, datapath ){
        println( "syncOneClass  " + datapath);

        if (!jsonObject || (jsonObject == JSONObject.NULL)  || !jsonObject.id){
            return false;
        }

        // locate the class in the db
        def sp = finders[datapath](jsonObject.id);

        if (!sp) {
            if (creators[datapath]){
                sp = creators[datapath](jsonObject.id, jsonObject);
              
            } else {
                return false;
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

                    // set the value to the one from JSON
                    if ( jsonObject[prop.name] && (jsonObject[prop.name] != JSONObject.NULL)){
                        sp[prop.name] = jsonObject.get(prop.name);
                    } else {

                        if (sp[prop.name]  && !jsonObject[prop.name]){

                        }

                    }



                    if (sp[prop.name].equals(jsonObject[prop])){
                        println( "5) eq<<<<<<<<<<<<<< property " + prop + "  <<<< patient: " + sp[prop.name]  + "<<<< data: " + jsonObject[prop] )
                    }else{
                       println( "6) not<<<<<<<<<<<<< property " + prop + "  <<<< patient: " + sp[prop.name]  + "<<<< data: " + jsonObject[prop] )
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
                            // Are the ids the same?
                            // Confirm the property value has an id field so it is a db entity
                            if ((jsonObject[prop.name] != JSONObject.NULL) && jsonObject[prop.name]?.id ){
                            		println(" syncOneClass pre-existing ");
                                // Yes good so sync the fields
                                sp[prop.name] =  syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name );
                            } else {
                                // No then overwrite
                                  def newValue = syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name );
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
                                      def newValue = syncOneClass(member,datapath+"."+prop.name );
                                       if (newValue){
                                            println("aDDING  " + datapath+"         "+newValue );
                                            sp[prop.name].add(newValue);
                                       } else {

                                            println("@@@@@@@@@@@@ Couldn't do " + datapath+"."+prop.name );
                                       }

                                };
                            }
                        }

                    } else {
                        println( "####################### No Finder for " + datapath+"."+prop.name);
                    }
                } else {

                    // handle date fields
                     if (jsonObject[prop.name].getClass() == Date){
                          sp[prop.name] = jsonObject.get(prop.name);
                     }

                }

            }

        }    // end loop over all the proerties in the class
        sp.save();
        return sp;

    }
/*

*/
}
