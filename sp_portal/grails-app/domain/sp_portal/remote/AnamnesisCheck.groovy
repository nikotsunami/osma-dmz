package sp_portal.remote
import remote.*;

class AnamnesisCheck {
    static mapping = {
      datasources(['original'])
      title column: 'title'
   }

    Long id

    Integer version
    Integer sortOrder
    String text
    Integer type
    String value
    Integer userSpecifiedOrder;
//    static hasMany = [title: AnamnesisCheck ]
AnamnesisCheck title;
    
    static mappedBy = [title: 'id',
                       ]

    static constraints = {
        sortOrder nullable: true
        text nullable: true
        type nullable: true
        value nullable: true
        version nullable: true
        title nullable: true
        userSpecifiedOrder nullable: true
    }
}
