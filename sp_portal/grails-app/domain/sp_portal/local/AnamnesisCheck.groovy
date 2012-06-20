package sp_portal.local
import local.*;

class AnamnesisCheck {
    static mapping = {
      datasources(['DEFAULT'])
      title column: 'title'
      anamnesisCheckTitle column: 'anamnesis_check_title'
      
   }

    Long id
    Long origId
    Integer version
    Integer sortOrder
    String text
    Integer type
    String value
    Integer userSpecifiedOrder
    AnamnesisCheck title
    AnamnesisCheckTitle anamnesisCheckTitle
    static mappedBy = [title: 'id',
                       anamnesisCheckTitle: 'id']
                       
    static hasMany = [anamnesisChecksValues: AnamnesisChecksValue
    ]
   
    static constraints = {
        sortOrder nullable: true
        text nullable: true
        type nullable: true
        value nullable: true
        version nullable: true
        title nullable: true
        userSpecifiedOrder nullable: true
        anamnesisCheckTitle nullable:true
        origId nullable: true
    }
}
