package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import org.apache.commons.logging.LogFactory;

class StdPntController  extends MainController {

    //QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

    //  static final int TITLE_TYPE = 4;
    
    def beforeInterceptor = [action:this.&isLoggedInAsUser]
	private static final log = LogFactory.getLog(this)

    def index() {
		log.info("index of StdPntController")
         redirect(action: "welcome")
    }

    def welcome() {

    }

    def showQuestion(){

    }



}
