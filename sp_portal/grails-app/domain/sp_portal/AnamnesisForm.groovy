package sp_portal

class AnamnesisForm {
    static mapping = {
      datasources(['DEFAULT', 'original'])
//      anamnesisChecksValues column: 'anamnesisform'
      //scars table: 'scar'
   }


    Integer version
    Long id
    Date createDate

    static hasMany = [//anamnesisChecksValues: AnamnesisChecksValue,

                      //standardizedPatients: StandardizedPatient
                      ]

    static constraints = {
        createDate nullable: true, maxSize: 19
    }
}
