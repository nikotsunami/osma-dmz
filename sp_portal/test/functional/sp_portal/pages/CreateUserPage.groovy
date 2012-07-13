package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CreateUserPage extends BasePage {

    static expectedHeading = "Create User"

    static elements = {
    }

    def CreateUserPage(driver){
        super(driver);
        assertTextPresent(expectedHeading);
    }

    def createUser(userName, email, password, confirmPassword, role){
        enterPassword(password)
        enterConfirmPassword(confirmPassword)
        enterEmail(email)
        enterUserName(userName)
        selectRoleName(role)

        clickButton("Create");

        assertTextPresent("Show User");
        assertTextPresent(email);
        assertTextPresent(userName);
        assertTextPresent(role);


    }

    def enterPassword(str){
        fillOutField("passwordHash",str)
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
