package sp_portal

import org.springframework.dao.DataIntegrityViolationException

class StdPntController  extends MainController {


    def index() {
         //redirect(action: "welcome")
         //def checkList = local.AnamnesisCheck.findAllByType(4);
         // println(">>>start index page" + checkList);
         redirect(action: "welcome")
    }

    def welcome() {
      // println(">>>start welcome page" + checkList);
      def checkList = local.AnamnesisCheck.findAllByType(4);
              println(">>>In welcome action" + checkList);
      [checkList: checkList];

    }
    
   

}
