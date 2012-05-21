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

    }

    def authenticate(){
log(" auth " + params);

        def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))
        if(user){
          session.user = user
          flash.message = "Hello ${user.userName}!"
          applyPermissions(user);

        }else{
          setupDefaultData();

          flash.message = "Sorry, ${params.userName}. Please try again."
          redirect(action:"login")

        }
      }


     private def applyPermissions(User user){
        def result = user.roles.findAll{ role -> role.roleName.contains(ADMIN_ROLE) }
        log("In applyPermissions  " + result);
        if ( result.size() > 0 ){
            log("redirectING TO ACTION LIST");
            redirect(controller:"user", action: "list")
        } else {
            redirect(controller:"StdPnt", action:"index")
        }

    }

     def logout() {
        flash.message = "Goodbye ${session.user.userName}"
        session.user = null
        redirect(action:"login")
     }







    private void setupDefaultData(){
          //  if (Role.list().size() == 0){

                Role adminRole = new Role();
                adminRole.roleName = ADMIN_ROLE;
                adminRole.roleDescription = "Administrate Users";
                adminRole.save();

                Role userRole = new Role();
                userRole.roleName = USER_ROLE;
                userRole.roleDescription = "Normal Users";
                userRole.save();

                User admin = new User();

                log("1 " + admin);

                admin.userName = grailsApplication.config.sp_portal.admin.username;
                admin.userEmail = grailsApplication.config.sp_portal.admin.email;
                admin.passwordHash = hashPassword(""+grailsApplication.config.sp_portal.admin.password,admin.userName);
                admin.isActive = true;

                log("2 " + admin);
                log("2 " + admin.userEmail);

                def roles = [];
                roles.add(Role.findByRoleName(ADMIN_ROLE));

                admin.roles = roles;

                admin.save();

            // }
    }




    private boolean comparePasswords(String p1,String p2){
        log("Comparing passwords " + p1 + "  and " + p2  );
        if ( p1 != p2) {
             flash.message = "Passwords do not match";
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
