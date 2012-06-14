package sp_portal.local
import local.*;
class Bankaccount {
    static mapping = {
      datasources(['DEFAULT'])
   }

    Integer version
    Long id
    Long origId
    String bic
    String iban
    String bankName
    String city
    //String country
    String ownerName
    Integer postalCode

    static hasMany = [standardizedPatients: StandardizedPatient]

    static constraints = {
        bic nullable: true, maxSize: 40
        iban nullable: true, maxSize: 40
        bankName nullable: true, maxSize: 40
        city nullable: true, maxSize: 30
      //  country nullable: true, maxSize: 30
        ownerName nullable: true, maxSize: 40
        postalCode nullable: true
        version nullable: true
        origId nullable: true
    }

    public String toString(){
        return bankName;
    }
}
