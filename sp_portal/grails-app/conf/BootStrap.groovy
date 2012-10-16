import sp_portal.Role
import sp_portal.User

import org.codehaus.groovy.grails.commons.ApplicationAttributes
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

	def ctx=servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
	def dataSource = ctx.dataSourceUnproxied

	dataSource.setMinEvictableIdleTimeMillis(1000 * 60 * 30)
	dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30)
	dataSource.setNumTestsPerEvictionRun(3)
 
	dataSource.setTestOnBorrow(true)
	dataSource.setTestWhileIdle(false)
	dataSource.setTestOnReturn(false)
	dataSource.setValidationQuery("SELECT 1")
		   
    }
    def destroy = { }
}
