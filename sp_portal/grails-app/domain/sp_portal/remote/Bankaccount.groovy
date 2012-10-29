package sp_portal.remote
import remote.*;

class Bankaccount {
    static mapping = {
      datasources(['original'])
   }

    Integer version
    Long id
    String bic
    String iban
    String bankName
    String city
    //String country
    String ownerName
    String postalCode

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
    }
}
