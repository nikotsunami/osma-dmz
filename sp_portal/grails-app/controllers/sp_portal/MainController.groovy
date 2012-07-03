package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


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
                def newUser = User.findById(user.id);
                if(newUser!=null){
                     def result = newUser.roles.findAll{ role -> role.roleName.contains(AuthenticationController.ADMIN_ROLE) }
                         if ( result.size() > 0 ){
                        isLogin = true;
                   }else{
                            session.user = null;
                    }
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
                def newUser = User.findById(user.id);
                if(newUser!=null){
                    def result = newUser.roles.findAll{ role -> role.roleName.contains(AuthenticationController.USER_ROLE) }
                        if ( result.size() > 0 ){
                        isLogin = true;
                    }else{
                            session.user = null;
                    }
                }

          }
      if(!isLogin){
         redirect(controller:"authentication", action:"login")
      }
        return isLogin;
    }

     static encodePassword(String password,String userName) {
                MessageDigest md;
                try {
                       md = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                     // TODO Auto-generated catch block

                     e.printStackTrace();
                     return "";
                }
                if(password!=null && userName!=null){
                    md.update(password.getBytes());
                   md.update(userName.getBytes());
                }


                 return (new BASE64Encoder()).encode(md.digest());

    }

    private String hashPassword(String unhashedPW, String userName){

        return encodePassword(unhashedPW,userName);
    }



    private void log(String msg){
        println(msg);
    }


    private void handleInboundPassword(params){


        log("In handleInboundPassword  " + params);

        if (params.passwordHash) {
              if(session.user!=null){
              User user = session.user;
              user = User.findById(user.id);
              def result = user.roles.findAll{ role -> role.roleName.contains(ADMIN_ROLE) }
              if(result.size()>0){
                 params.passwordHash = hashPassword(params.passwordHash,params.userName);
            }
            else{
             params.passwordHash = hashPassword(params.passwordHash,session.user.userName);

                }
            }
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


  	private void setupDefaultData(){
           
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
                log("setup Default Data over" + admin)

           
    }
	








}
