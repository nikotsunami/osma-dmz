package sp_portal

import org.springframework.dao.DataIntegrityViolationException

class StdPntController  extends MainController {


    def index() {
         redirect(action: "welcome")
    }

    def welcome() {
        println("In welcome action")
    }

}
