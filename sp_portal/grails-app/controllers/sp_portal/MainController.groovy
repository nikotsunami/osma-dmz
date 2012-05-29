package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

/**
 * This class contains common functionality for all controllers, e.g. security checking
 */
class MainController {

    def isLoggedIn(){
    println(" at isLoggedIn method!");
         def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))
         if(session.user){
            return true;
         } else {
            redirect(controller:"authentication", action:"login")
            return false;
         }
    }


    private String hashPassword(String unhashedPW, String userName){
        return "hashed" + unhashedPW;
    }

    private void log(String msg){
        println(msg);
    }


    private void handleInboundPassword(params){


        log("In handleInboundPassword  " + params);

        if (params.passwordHash) {
            params.passwordHash = hashPassword(params.passwordHash,params.userName);
        } else {
            def userInstance = User.get(params.id);
            if (userInstance){
                params.passwordHash = userInstance.passwordHash;
            }
        }

        log("Leaving handleInboundPassword  " + params);

    }

    private void handleOutboundPassword(user){
        user.passwordHash = null;
    }






}
