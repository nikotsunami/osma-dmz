package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
class StdPntController  extends MainController {

    //QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

    //  static final int TITLE_TYPE = 4;

    def index() {

         redirect(action: "welcome")
    }

    def welcome() {

//      def checkList = local.AnamnesisCheck.findAllByType(AnamnesisCheckTypes.QUESTION_TITLE.getTypeId());
//        println(" in welcome action");
    }

    def showQuestion(){

    //title
    //type
    //text
    }



}
