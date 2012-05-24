package sp_portal

import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.json.*;

class DataImportExportController extends MainController {

    def beforeInterceptor = [action:this.&isLoggedIn]
    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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

// {"class":"sp_portal.remote.StandardizedPatient","id":20,"anamnesisForm":{"class":"sp_portal.remote.AnamnesisForm","id":3,"createDate":"2010-07-17T16:00:00Z","standardizedPatients":[{"_ref":"../..","class":"sp_portal.remote.StandardizedPatient"}]},"bankaccount":{"class":"sp_portal.remote.Bankaccount","id":20,"bankName":"Basler Kantonalbank","bic":"GENODEF1JEV","city":null,"iban":"CH46 3948 4853 2029 3","ownerName":null,"postalCode":null,"standardizedPatients":[{"_ref":"../..","class":"sp_portal.remote.StandardizedPatient"}]},"birthday":"1954-08-24T16:00:00Z","city":"Metz","description":null,"email":"m.lamarie@unibas.ch","gender":1,"height":162,"immagePath":null,"maritalStatus":null,"mobile":"078 427 24 85","name":"Lamarie","nationality":{"class":"sp_portal.remote.Nationality","id":7,"nationality":"Frankreich"},"postalCode":4057,"preName":"Marianne","profession":{"class":"sp_portal.remote.Profession","id":3,"profession":"Bauarbeiter/in"},"socialInsuranceNo":null,"street":"Feldbergstrasse 145","telephone":null,"telephone2":null,"videoPath":null,"weight":57,"workPermission":null}

    def importSP(){

        if (params.data){
            def jsonObject = JSON.parse(params.data);
            syncData(jsonObject);
            render jsonObject.email;
        } else {
                render "No data"

        }
    }

    private boolean syncData(jsonObject){
        remote.StandardizedPatient sp = new remote.StandardizedPatient(jsonObject);

        remote.Bankaccount bank = new remote.Bankaccount(jsonObject.bankaccount)
        println(">>>>>>>>>>>. " + sp);
        println(">>>>>>>>>>>. " + bank);

    }
}
