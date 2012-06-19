package sp_portal.local
import local.*;

class AnamnesisCheck {
    static mapping = {
      datasources(['DEFAULT'])
      title column: 'title'
   }

    Long id
    Long origId
    Integer version
    Integer sortOrder
    String text
    Integer type
    String value
    Integer userSpecifiedOrder;
    AnamnesisCheck title
   
    static mappedBy = [title: 'id'
                       ]
                       
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
        origId nullable: true
    }
}
