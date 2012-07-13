package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserHomePage extends BasePage {
    static expectedTitle = "Login"

    static elements = {
    }

    def UserHomePage(driver){
        super(driver)
        assertTextPresent("Welcome   ");
    }


    def clickLogout(){
        clickLink("Logout");
        return new LoginPage(driver)
    }

    def clickMyAccount(){
        clickLink("My Account");
        assertTextPresent("Show User");
    }

    def clickPersonalDetails(){
        clickLink("Personal Details");
      //  assertTextPresent("Osce Day List");
    }

    def clickBanksDetails(){
        clickLink("Banks Details");
 //       assertTextPresent("Training List");
    }

    def clickQuestions(){
        clickLink("Questions");

    }

    def clickSelectAvailableDates(){
        clickLink("Select Available Dates");

    }


/*
    My Account
    Personal Details
    Banks Details
    Questions
    Select Available Dates

*/

}
