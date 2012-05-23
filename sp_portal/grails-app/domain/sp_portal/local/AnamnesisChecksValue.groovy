package sp_portal.local
import local.*;

class AnamnesisChecksValue {
    static mapping = {
      datasources(['DEFAULT'])
      anamnesisCheck column: 'anamnesischeck'
      anamnesisForm column: 'anamnesisform'
   }


    Integer version
    Long id
    Long origId
    String anamnesisChecksValue
    String comment
    Boolean truth
    AnamnesisForm anamnesisForm
    AnamnesisCheck anamnesisCheck

//    static belongsTo = [AnamnesisCheck, AnamnesisForm]

  static hasMany = [anamnesisForm: AnamnesisForm,
                     anamnesisCheck: AnamnesisCheck]

    static mappedBy = [anamnesisForm: 'id',
                      anamnesisCheck: 'id'
                       ]

    static constraints = {
        anamnesisChecksValue nullable: true
        comment nullable: true
        truth nullable: true
        version nullable: true
        anamnesisForm nullable: true
        anamnesisCheck nullable: true

    }

}
