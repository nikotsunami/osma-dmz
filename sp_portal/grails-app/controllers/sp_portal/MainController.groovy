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
				   
				
				// println("#^#^#^#^#^#^^#^#^#^#^#^#^ unhashedPW:"+password +"userName "+userName+" md "+(new BASE64Encoder()).encode(md.digest()));
				 return (new BASE64Encoder()).encode(md.digest());
				
	}
	
    private String hashPassword(String unhashedPW, String userName){
    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%% unhashedPW:"+unhashedPW +"userName "+userName);
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
   
		





}
