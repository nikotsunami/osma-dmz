package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LoginPage extends BasePage {
    static expectedTitle = "Login"

    static elements = {
    }

    def LoginPage(driver){
        super(driver)
    }

    def submitLogin(userName, password){
        driver.get(baseUrl);
        fillOutField("userName",userName)
        fillOutField("passwordHash",password)
        clickButton("Login")
    }

    def loginAdmin(){
        submitLogin("admin", "123");
        assertTextPresent("Welcome   admin");
        return new AdminPage(driver)
    }

    def loginUser(userName, password){
        submitLogin(userName, password);
        assertTextPresent("Welcome   ${userName}");
        return new UserHomePage(driver)
    }


}
