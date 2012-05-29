package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;

class DataImportExportController extends MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsAdmin]

    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static finders = [ "StandardizedPatient": { id ->local.StandardizedPatient.findByOrigId(id)},
                       "StandardizedPatient.bankaccount":{ id ->local.Bankaccount.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm":{ id ->local.AnamnesisForm.findByOrigId(id)},
                       "StandardizedPatient.description":{ id ->local.Description.findByOrigId(id)},
                       "StandardizedPatient.profession":{ id ->local.Profession.findByOrigId(id)},
                       "StandardizedPatient.nationality":{ id ->local.Nationality.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id ->local.AnamnesisChecksValue.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id ->local.AnamnesisCheck.findByOrigId(id)},
                       "StandardizedPatient.anamnesisForm.scars":{ id ->local.Scar.findByOrigId(id)},
                       ]
    static creators = [ "StandardizedPatient": { id -> def x = new local.StandardizedPatient(); x.origId = id; return x},
                       "StandardizedPatient.bankaccount":{ id -> def x = new local.Bankaccount(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm":{ id -> def x = new local.AnamnesisForm(); x.origId = id; return x},
                       "StandardizedPatient.description":{ id -> def x = new local.Description(); x.origId = id; return x},
                       "StandardizedPatient.profession":{ id -> def x = new local.Profession(); x.origId = id; return x},
                       "StandardizedPatient.nationality":{ id -> def x = new local.Nationality(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues":{ id -> def x = new local.AnamnesisChecksValue(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.anamnesisChecksValues.anamnesisCheck":{ id -> def x = new local.AnamnesisCheck(); x.origId = id; return x},
                       "StandardizedPatient.anamnesisForm.scars":{ id -> def x = new local.Scar(); x.origId = id; return x},
                       ]

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


// {"class":"sp_portal.local.StandardizedPatient","anamnesisForm":{"class":"sp_portal.local.AnamnesisForm","anamnesisChecksValues":[{"class":"sp_portal.local.AnamnesisChecksValue","anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":3,"sortOrder":10,"text":"Nehmen Sie zurzeit regelmässig Medikamente ein?","title":{"class":"sp_portal.local.AnamnesisCheck","id":12,"sortOrder":9,"text":"Treatment history category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},"type":1,"userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":9,"truth":false},{"class":"sp_portal.local.AnamnesisChecksValue","anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie?","title":{"class":"sp_portal.local.AnamnesisCheck","id":10,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},"type":1,"userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":8,"truth":true}],"createDate":new Date(1279382400000),"id":3,"scars":[],"standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},"bankaccount":{"class":"sp_portal.local.Bankaccount","bankName":"Basler Kantonalbank","bic":"GENODEF1JEV","city":null,"iban":"CH46 3948 4853 2029 3","id":20,"ownerName":null,"postalCode":null,"standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},"birthday":new Date(-484560000000),"city":"Metz","description":null,"email":"paul 111 m.lamarie@unibas.ch","gender":1,"height":162,"immagePath":null,"maritalStatus":null,"mobile":"078 427 24 85","name":"Lamarie","nationality":{"class":"sp_portal.local.Nationality","nationality":"Frankreich","id":7},"id":20,"postalCode":4057,"preName":"Marianne","profession":{"class":"sp_portal.local.Profession","id":3,"profession":"Bauarbeiter/in"},"socialInsuranceNo":null,"street":"Feldbergstrasse 145","telephone":null,"telephone2":null,"videoPath":null,"weight":57,"workPermission":null}

// {"class":"sp_portal.local.StandardizedPatient","anamnesisForm":{"class":"sp_portal.local.AnamnesisForm","anamnesisChecksValues":[{"class":"sp_portal.local.AnamnesisChecksValue","anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":3,"sortOrder":10,"text":"Nehmen Sie zurzeit regelmässig Medikamente ein?","title":{"class":"sp_portal.local.AnamnesisCheck","id":12,"sortOrder":9,"text":"Treatment history category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},"type":1,"userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":9,"truth":false},{"class":"sp_portal.local.AnamnesisChecksValue","anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie?","title":{"class":"sp_portal.local.AnamnesisCheck","id":10,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},"type":1,"userSpecifiedOrder":null,"value":""},"anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":8,"truth":true}],"createDate":new Date(1279382400000),"id":3,"scars":[],"standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},"bankaccount":{"class":"sp_portal.local.Bankaccount","bankName":"Basler Kantonalbank","bic":"GENODEF1JEV","city":null,"iban":"CH46 3948 4853 2029 3","id":20,"ownerName":null,"postalCode":null,"standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},"birthday":new Date(-484560000000),"city":"Metz","description":null,"email":"paul 111 m.lamarie@unibas.ch","gender":1,"height":162,"immagePath":null,"maritalStatus":null,"mobile":"078 427 24 85","name":"Lamarie","nationality":{"class":"sp_portal.local.Nationality","nationality":"Frankreich","id":7},"id":20,"postalCode":4057,"preName":"Marianne","profession":{"class":"sp_portal.local.Profession","id":3,"profession":"Bauarbeiter/in"},"socialInsuranceNo":null,"street":"Feldbergstrasse 145","telephone":null,"telephone2":null,"videoPath":"hello nick","weight":57,"workPermission":null}

/*

{"class":"sp_portal.local.StandardizedPatient",
        "anamnesisForm":{"class":"sp_portal.local.AnamnesisForm",
                        "anamnesisChecksValues":[{"class":"sp_portal.local.AnamnesisChecksValue",
                                                    "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":3,"sortOrder":10,"text":"Nehmen Sie zurzeit regelmässig Medikamente ein?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":12,"sortOrder":9,"text":"Treatment history category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":""},
                                                            "anamnesisChecksValue":null,
                                                            "anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},
                                                            "comment":null,"id":9,"truth":false},
                                                 {"class":"sp_portal.local.AnamnesisChecksValue",
                                                     "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":10,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":""},
                                                            "anamnesisChecksValue":null,"anamnesisForm":{"_ref":"../..","class":"sp_portal.local.AnamnesisForm"},"comment":null,"id":8,"truth":true},
                                                 {"class":"sp_portal.local.AnamnesisChecksValue",
                                                     "anamnesisCheck":{"class":"sp_portal.local.AnamnesisCheck","id":1,"sortOrder":2,"text":"Rauchen Sie 222?",
                                                            "title":{"class":"sp_portal.local.AnamnesisCheck","id":10,"sortOrder":1,"text":"Personal lifestyle category","title":null,"type":4,"userSpecifiedOrder":null,"value":""},
                                                            "type":1,"userSpecifiedOrder":null,"value":"JDJDJDDJDJDJ"},
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
                                                "city":null,
                                                "iban":"CH46 3948 4853 2029 3",
                                                "id":200,
                                                "ownerName":null,
                                                "postalCode":null,
                                                "standardizedPatients":[{"_ref":"../..","class":"sp_portal.local.StandardizedPatient"}]},
        "birthday":new Date(-484560000000),
        "city":"Metz",
        "description":null,
        "email":"paul 111 m.lamarie@unibas.ch",
        "gender":1,
        "height":162,
        "immagePath":null,
        "maritalStatus":null,
        "mobile":"078 427 24 85",
        "name":"Lamarie",
        "nationality":{"class":"sp_portal.local.Nationality",
                        "nationality":"Frankreich","id":7},
        "id":20,
        "postalCode":4057,
        "preName":"Marianne",
        "profession":{"class":"sp_portal.local.Profession","id":3,"profession":"Bauarbeiter/in"},
        "socialInsuranceNo":null,
        "street":"Feldbergstrasse 145",
        "telephone":null,
        "telephone2":null,
        "videoPath":"hello nick",
        "weight":57,
        "workPermission":null}

*/



// "birthday":new Date(-484560000000)
// "createDate":new Date(1279382400000)


    def importSP(){
 println( "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
            println( "1 No JSON data for field  " + datapath);
            return false;
        }

        // locate the class in the db
        def sp = finders[datapath](jsonObject.id);

        if (!sp) {
            if (creators[datapath]){
                sp = creators[datapath](jsonObject.id);
            } else {
                return false;
            }
        }


        // loop over all the proerties in the class
        sp.metaPropertyValues.each{ prop ->

         //   println("3) -----------property: " + prop.name + "-----" + prop.type)

            // avoid the read only properties
            if(exclusions.find {it == prop.name}) return


            // if it is a basic type
            if ([Long, String, Integer, Boolean, Short].contains( prop.type)){

                // if the json data contains this property
                if(jsonObject.containsKey(prop.name)) {

                    // set the value to the one from JSON
                    if ( jsonObject[prop.name] && (jsonObject[prop.name] != JSONObject.NULL)){
                        println( "4)  Set property " + prop.name + " with type " + prop.type + " to  " + jsonObject[prop.name] + " of type" + jsonObject[prop.name].class )
                        sp[prop.name] = jsonObject.get(prop.name);
                    } else {

                        if (sp[prop.name]  && !jsonObject[prop.name]){

                        }

                    }



                    if (sp[prop.name].equals(jsonObject[prop])){
           //             println( "5) eq<<<<<<<<<<<<<< property " + prop + "  <<<< patient: " + sp[prop.name]  + "<<<< data: " + jsonObject[prop] )
                    }else{
           //            println( "6) not<<<<<<<<<<<<< property " + prop + "  <<<< patient: " + sp[prop.name]  + "<<<< data: " + jsonObject[prop] )
                    }
                }

            } else {

               // not a basic type
               println( "7) Field  " + prop.name + " " + prop.type );
               if (Date != prop.type){

                   def fieldFinder = finders[datapath+"."+prop.name];
                   if (fieldFinder){
                        // known Entity relationship
                        if (prop.type != Set){
                            println("//  1 to 1 relationship");

                            // Are the ids the same?
                            if ((jsonObject[prop.name] != JSONObject.NULL) && jsonObject[prop.name].id ){
                                // Yes good so sync the fields
                                syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name );
                            } else {
                                // No then overwrite
                                  def newValue = syncOneClass(jsonObject[prop.name] ,datapath+"."+prop.name );
                                  if (newValue){
                                        sp[prop.name] = newValue;
                                  }
                            }



                        } else {
                            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~// one to many relationship");
                            //  one to many relationship, so clear the current values and reinitialize from JSON data
                            sp[prop.name] = []

                            //
                            def array = jsonObject[prop.name];

                            if (array){
                                array.each{ member ->

                                      // No then overwrite
                                      def newValue = syncOneClass(member,datapath+"."+prop.name );
                                       println("===============================================================");
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
                     println( "Date ccccc " +jsonObject[prop.name].getClass() );
                     if (jsonObject[prop.name].getClass() == Date){
                          sp[prop.name] = jsonObject.get(prop.name);
                     }

                }

            }


        }    // end loop over all the proerties in the class

        println(".............................................................");
        sp.save();
        println(".............................................................");
        return sp;

    }
/*

*/
}
