package sp_portal.local
import local.*;

class AnamnesisCheckTitle{

   static mapping = {
      datasources(['DEFAULT'])

   }


    Integer version
    Long id
    Long origId
	  Integer sortOrder
	  String text


   

    static constraints = {
		sortOrder nullable:true,maxsize:11
        text maxsize:255
     }

  



}
