package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CreateTrainingPage extends BasePage {

    static expectedHeading = "Create Training"

    static elements = {
    }

    def CreateTrainingPage(driver){
        super(driver);
        assertTextPresent(expectedHeading);
    }

    def createTraining(name, dateYear,dateMonth,dateDay,timeStartHour,timeStartMinute,timeEndHour,timeEndMinute ){
        enterName(name)
        selectDateFromCalendar("trainingDate-trigger", "2006", "Dec", "05")

        clickButton("Create");

    }

    def enterName(str){
        fillOutField("name",str)
    }

    def enterConfirmPassword(str){
        fillOutField("confirmPassword",str)
    }

    def enterEmail(str){
        fillOutField("userEmail",str)
    }

    def enterUserName(str){
        fillOutField("userName",str)
    }

    def selectRoleName(str){
        clickOptionValue(str)
    }


}
