package sp_portal

class AnamnesisChecksValue {
    static mapping = {
      datasources(['DEFAULT', 'original'])
  //    anamnesisCheck column: 'id'
   }


    Integer version
    Long id
    String anamnesisChecksValue
    String comment
    Boolean truth
//    AnamnesisForm anamnesisForm
  //  AnamnesisCheck anamnesisCheck

//    static belongsTo = [AnamnesisCheck, AnamnesisForm]

    static constraints = {
        anamnesisChecksValue nullable: true
        comment nullable: true
        truth nullable: true
    }

}
