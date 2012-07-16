package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserListPage extends BasePage {
    static expectedTitle = "Login"
    static expectedHeading = "User List"

    static elements = {
    }

    def UserListPage(driver){
        super(driver);
        assertTextPresent(expectedHeading);
    }

    def clickNewUser(){
        clickLink("New User")
        assertTextPresent("Create User");
        return new CreateUserPage(driver);
    }

    def clickImportData(){
        clickLink("Import Data")
    }

    def clickOnUserId(num){
        def href="/sp_portal/user/show/" + num;
        clickLinkWithHref(href)
        assertTextPresent("Show User");
    }



}
