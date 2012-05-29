package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

/**
 * This class contains common functionality for all controllers, e.g. security checking
 */
class MainController {

    
    def isLoggedIn(){
     def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))    
         if(session.user){
            return true;
         } else {
            redirect(controller:"authentication", action:"login")
            return false;
         }
    }
    
    def isLoggedInAsAdmin(){    
      boolean isLogin = false;
		  if(session.user){
		  		User user = session.user;
          def result = user.roles.findAll{ role -> role.roleName.contains(AuthenticationController.ADMIN_ROLE) }		  
					if ( result.size() > 0 ){
			            isLogin = true;
			    }else{
			    	 session.user = null;
			    }
		  }  
	     if(!isLogin){
	         redirect(controller:"authentication", action:"login")
	     }
       return isLogin;
    }
    
    def isLoggedInAsUser(){    
    
      boolean isLogin = false;
		  if(session.user){
		  		User user = session.user;
          def result = user.roles.findAll{ role -> role.roleName.contains(AuthenticationController.USER_ROLE) }		  
					if ( result.size() > 0 ){
			            isLogin = true;
			    }else{
			    	 session.user = null;
			    }
		  }  
      if(!isLogin){
         redirect(controller:"authentication", action:"login")
      }
        return isLogin;
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
