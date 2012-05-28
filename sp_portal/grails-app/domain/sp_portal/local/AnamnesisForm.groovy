package sp_portal.local
import local.*;

class AnamnesisForm {
    static mapping = {
      datasources(['DEFAULT'])
    }

    Date createDate
    Integer version
    Long id
    Long origId

    static hasMany = [anamnesisChecksValues: AnamnesisChecksValue,
                      scars: Scar,
                        standardizedPatients: StandardizedPatient
                       ]

    static constraints = {
        createDate nullable: true, maxSize: 19
        version nullable: true
    }
}
