package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ManageTrainingDaysPage extends BasePage {

    static expectedHeading = "Training List"

    static elements = {
    }

    def ManageTrainingDaysPage(driver){
        super(driver);
        assertTextPresent(expectedHeading);
    }

    def clickNewTraining(){
        clickLink("New Training")
        assertTextPresent("Create Training");
        return new CreateTrainingPage(driver);
    }




}
