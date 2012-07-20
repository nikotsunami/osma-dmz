package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AdminPage extends BasePage {
    static expectedTitle = "Login"

    static elements = {
    }

    def AdminPage(driver){
        super(driver)
    }


    def clickLogout(){
        clickLink("Logout");
        return new LoginPage(driver)
    }

    def clickManageUsers(){
        clickLink("Manage Users");
        assertTextPresent("User List");
        return new UserListPage(driver)
    }

    def clickManageOsceDays(){
        clickLink("Manage Osce Days");
        assertTextPresent("Osce Day List");
    }

    def clickManageTrainingDays(){
        clickLink("Manage Training Days");
        assertTextPresent("Training List");
        return new ManageTrainingDaysPage(driver);
    }

    def clickSendEmails(){
        clickLink("Send Emails");
        assertTextPresent("Is Send");
        assertTextPresent("User Email");
        assertTextPresent("User Name");
    }

    def clickAcceptedDays(){
        clickLink("Accepted Days");
        assertTextPresent("Accepted Days List");
    }




}
