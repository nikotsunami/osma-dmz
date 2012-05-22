package sp_portal

class AnamnesisForm {
    static mapping = {
      datasources(['DEFAULT', 'original'])
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
    }
}
