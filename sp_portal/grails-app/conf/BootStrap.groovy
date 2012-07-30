import sp_portal.Role
import sp_portal.User

import grails.converters.JSON
import org.joda.time.*;

class BootStrap {

    def init = { servletContext ->
	    if (Role.list().size() == 0){
                def adminRole = new Role(id:1,roleName:"ADMIN_ROLE",roleDescription:"Administrate Users");
                adminRole.save();

                def userRole = new Role(id:2,roleName:"USER_ROLE",roleDescription:"Normal Users");
                userRole.save();
            

           }
		JSON.registerObjectMarshaller(LocalDate) {
			return it?.toString("yyyy-MM-dd")
		} 
		   
    }
    def destroy = { }
}
