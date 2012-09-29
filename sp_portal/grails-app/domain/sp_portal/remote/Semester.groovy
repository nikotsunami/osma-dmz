package sp_portal.remote
import remote.*;
import java.util.Calendar;
import org.apache.log4j.Logger;

class Semester {
   static mapping = {
      datasources(['original'])

   }
    Long id
    Integer calYear
    Double maximalYearEarnings
	Integer preparationRing
	Double pricestatist
	Double priceStandardizedPartient
	Integer semester
	Integer version
	
	

    static constraints = {
		calYear nullable:true
		version nullable:true
		maximalYearEarnings nullable:true
		preparationRing nullable:true
		pricestatist nullable:true
		priceStandardizedPartient nullable:true
    }
	
}
