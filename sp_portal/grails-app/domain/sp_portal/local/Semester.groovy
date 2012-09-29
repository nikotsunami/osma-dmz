package sp_portal.local
import local.*;

class Semester {
   static mapping = {
      datasources(['DEFAULT'])

   }
    Long id
	Long origId
    Integer calYear
    Double maximalYearEarnings
	Integer preparationRing
	Double pricestatist
	Double priceStandardizedPartient
	Integer semester
	Integer version
	
	

    static constraints = {
		origId nullable:true
		calYear nullable:true
		version nullable:true
		maximalYearEarnings nullable:true
		preparationRing nullable:true
		pricestatist nullable:true
		priceStandardizedPartient nullable:true
    }
	
	public String showSemester(){
		String retsemester = "";
		if(this.semester == 0){
			retsemester = "HS";
		}else if(this.semester == 1){
			retsemester = "FS";
		}
		
		return this.calYear +"   "+ retsemester;
	}
	
	public String toString (){
		return showSemester(); 
	}
}