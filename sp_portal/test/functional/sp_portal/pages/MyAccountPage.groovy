package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MyAccountPage extends BasePage {
    static expectedTitle = "MyAccount"

    static elements = {
    }

    def MyAccountPage(driver){
        super(driver)

    }
	
	def clickEdit(){
		clickLink("Edit");
		
		assertTextPresent("Edit User");
		return new MyAccountEditPage(driver);
		
	}



}
