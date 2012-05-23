package sp_portal.local
import local.*;

class AnamnesisCheck {
    static mapping = {
      datasources(['DEFAULT'])
      title table: 'anamnesis_check' , column: 'id'
   }

    Long id
    Long origId
    Integer version
    Integer sortOrder
    String text
    Integer type
    String value
    //AnamnesisCheck title

    //static hasMany = [anamnesisChecksValues: local.AnamnesisChecksValue]

    static constraints = {
        sortOrder nullable: true
        text nullable: true
        type nullable: true
        value nullable: true
        version nullable: true
    }
}
