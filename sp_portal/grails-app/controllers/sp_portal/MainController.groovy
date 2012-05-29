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






}
