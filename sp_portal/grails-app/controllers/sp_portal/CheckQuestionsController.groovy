package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes

//QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

class CheckQuestionsController  extends MainController {

    def test() {

				local.AnamnesisCheck question1 = new local.AnamnesisCheck(); 
				
				question1.type = AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()
				question1.text = "Do you like cats?";

				local.AnamnesisCheck question2 = new local.AnamnesisCheck(); 
				
				question2.type = AnamnesisCheckTypes.QUESTION_MULT_M.getTypeId()
				question2.text = "What Type Of Cigarettes Have you Smoked";
				question2.value = "Strike|Awesomesauce|Winfail";
				
				
    
    
    
    //Integer sortOrder
    //String text
   // Integer type
   // String value
   // Integer userSpecifiedOrder;
   // AnamnesisCheck title


				[question1:question1, question2:question2]         
    }

}
