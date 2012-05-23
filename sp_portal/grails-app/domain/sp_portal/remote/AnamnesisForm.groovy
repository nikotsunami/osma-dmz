package sp_portal.remote
import remote.*;

class AnamnesisForm {
    static mapping = {
      datasources([ 'original'])
    }

    Date createDate
    Integer version
    Long id


    static hasMany = [//anamnesisChecksValues: AnamnesisChecksValue,
                      //scars: Scar,
                        standardizedPatients: StandardizedPatient
                       ]

    static constraints = {
        createDate nullable: true, maxSize: 19
        version nullable: true
    }
}
