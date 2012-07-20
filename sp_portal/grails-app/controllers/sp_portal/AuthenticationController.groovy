package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;


class AuthenticationController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";


    def beforeInterceptor = [action:this.&isLoggedIn, except:["login", "authenticate", "index"]]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

// Authentication Methods

    def login(){
      setupDefaultData();
	 
    }

    def authenticate(){
        log(" auth " + params);

        def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))
		

        log(" user " + user);
          if(user && user.isActive){
         session.user = user
		 log("user   " +user.standardizedPatient);
          flash.message = "${message(code: 'default.login.success.message')} ${user.userName}!"
         applyPermissions(user);


        }else{
          flash.message = message(code: 'default.lgoin.unsuccess.message',args: [params.userName])
          setupDefaultData();
          redirect(action:"login")

        }
      }


     private def applyPermissions(User user){
        def result = user.roles.findAll{ role -> role.roleName.contains(ADMIN_ROLE) }
        log("In applyPermissions  " + result);
        if ( result.size() > 0 ){
            log("redirectING TO ACTION LIST");
            redirect(controller:"user", action: "index")
        } else {
          redirect(controller:"StdPnt", action:"index")
        }

    }

     def logout() {
        flash.message = "${message(code: 'default.logout.message')} ${session.user.userName}"
        session.user=null
		session.titleIndex= null;
        redirect(action:"login")
     }
     

    private boolean comparePasswords(String p1,String p2){
        log("Comparing passwords " + p1 + "  and " + p2  );
        if ( p1 != p2) {
             flash.message = "${message(code: 'default.password.match.message')}";
             log(" returning false");
             return false;
        }
        log(" returning true");
        return true;
    }


    private void handleOutboundPassword(user){
        user.passwordHash = null;
    }
	


}
