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
		log.info("user login")
        setupDefaultData();
	 
    }

    def authenticate(){
		if(log.isTraceEnabled()){
			log.trace(">> In class AuthenticationController Method authenticate() with params : "+params)
		}

        def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))
		
        if(log.isDebugEnabled()){
			log.debug("find user : "+user)
		}
        if(user && user.isActive){
			session.user = user
			log("user   " +user?.standardizedPatient);
			flash.message = "${message(code: 'default.login.success.message')} ${user.userName}!"
			applyPermissions(user);


        }else{
            flash.message = message(code: 'default.lgoin.unsuccess.message',args: [params.userName])
            setupDefaultData();
            redirect(action:"login")
        }
    }


     private def applyPermissions(User user){
		if(log.isTraceEnabled()){
			log.trace(">> In class AuthenticationController Method applyPermissions entered user : "+user)
		}
        def result = user.roles.findAll{ role -> role.roleName.contains(ADMIN_ROLE) }
		 if(log.isDebugEnabled()){
			log.debug("find user roles: "+result)
		}
        log("In applyPermissions  " + result);
        if ( result.size() > 0 ){
            log.info("redirectING TO ACTION LIST");
            redirect(controller:"user", action: "index")
        } else {
          redirect(controller:"StdPnt", action:"index")
        }

    }

     def logout() {
		log.info("user logout");
        flash.message = "${message(code: 'default.logout.message')} ${session.user.userName}"
        session.user=null
		session.titleIndex= null;
        redirect(action:"login")
     }
     

    private boolean comparePasswords(String p1,String p2){
		if(log.isTraceEnabled()){
			log.trace(">> In class comparePasswords Method comparePasswords compare p1 :"+p1+" with p2: "+p2)
		}
        if ( p1 != p2) {
             flash.message = "${message(code: 'default.password.match.message')}";
             log.trace("<< In class comparePasswords Method comparePasswords returning false");
             return false;
        }
        log.trace("<< In class comparePasswords Method comparePasswords returning true");
        return true;
    }


    private void handleOutboundPassword(user){
		log.info("handle Outbound Password");
        user.passwordHash = null;
    }
	


}
