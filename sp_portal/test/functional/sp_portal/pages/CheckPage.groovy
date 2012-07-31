package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CheckPage extends BasePage {
    static expectedTitle = "showPage"
    

    static elements = {
    }

    def CheckPage(driver){
        super(driver);

    }
	def find(){
		findIframe();
		//assertTextPresent("Konsumverhalten");
		return new ShowQuestionPage(driver)
	}


}
