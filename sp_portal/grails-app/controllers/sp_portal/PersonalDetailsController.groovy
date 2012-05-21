package sp_portal

import org.springframework.dao.DataIntegrityViolationException

class PersonalDetailsController  extends MainController {


    def index() {
         redirect(action: "show")
    }

    def show(){

    }

}
