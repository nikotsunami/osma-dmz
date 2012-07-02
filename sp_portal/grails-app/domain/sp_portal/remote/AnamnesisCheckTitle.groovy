package sp_portal.remote
import remote.*;

class AnamnesisCheckTitle{

   static mapping = {
       datasources(['original'])

   }


    Integer version
    Long id
	  Integer sortOrder
	  String text


   

    static constraints = {
		sortOrder nullable:true,maxsize:11
        text maxsize:255
     }

  






}
