package sp_portal

class ThankController extends MainController{

	 def beforeInterceptor = [action:this.&isLoggedInAsUser]

	def thank(){
	}
	def thankPatientInSemester(){
		session.semester=null;
	}
	
}
