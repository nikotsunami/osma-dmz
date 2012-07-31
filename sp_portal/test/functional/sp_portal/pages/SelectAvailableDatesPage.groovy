package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SelectAvailableDatesPage extends BasePage {
    //static expectedTitle = "MyAccount"

    static elements = {
    }

    def SelectAvailableDatesPage(driver){
        super(driver)

    }
	
	def chooseDays(id){
		clickCheckDay(id);		
		assertTextPresent("Training Days");
		
		
	}
	
	def clickSave(){
		clickButton("Save");
	
	}



}
