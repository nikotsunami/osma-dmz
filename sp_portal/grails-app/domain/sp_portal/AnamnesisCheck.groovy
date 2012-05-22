package sp_portal

class AnamnesisCheck {
    static mapping = {
      datasources(['DEFAULT', 'original'])
      title table: 'anamnesis_check' , column: 'id'
   }

    Long id
    Integer version
    Integer sortOrder
    String text
    Integer type
    String value
    //AnamnesisCheck title

    //static hasMany = [anamnesisChecksValues: AnamnesisChecksValue]

    static constraints = {
        sortOrder nullable: true
        text nullable: true
        type nullable: true
        value nullable: true
    }
}
