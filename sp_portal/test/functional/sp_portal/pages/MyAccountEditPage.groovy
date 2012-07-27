package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MyAccountEditPage extends BasePage {
    static expectedTitle = "MyAccountEdit"

    static elements = {
    }

    def MyAccountEditPage(driver){
        super(driver)
        assertTextPresent("Edit User");
    }
	
	def enterPassword(str){
		fillOutField("passwordHash",str);
			
	}
	
	def enterConfirmPassword(str){
        fillOutField("confirmPassword",str)
    }
	
	def clickUpdate(){
		clickButton("Update");
	}
	
	



}
