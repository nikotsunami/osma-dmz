package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import sp_portal.pages.*;

class LoginTests {
    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper()

    @Test
    public void test001LoginAdmin() {
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        def userListPage = adminPage.clickManageUsers();
        userListPage.clickOnUserId(1);

        adminPage.clickManageOsceDays()
        adminPage.clickManageTrainingDays()
        adminPage.clickSendEmails()
        adminPage.clickAcceptedDays()
        adminPage.clickLogout()

    }

    @Test
    public void test002CreateUser() {
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        def userListPage = adminPage.clickManageUsers();
        def createUserPage = userListPage.clickNewUser();

        createUserPage.createUser("user1", "user1@home.cn", "pass123", "pass123", "USER_ROLE")

        adminPage.clickLogout()

        UserHomePage userHomePage = loginPage.loginUser("user1", "pass123")

        userHomePage.clickMyAccount()
        // userHomePage.clickPersonalDetails()  // Bug
        // userHomePage.clickBanksDetails() // Bug
       // userHomePage.clickQuestions()
        //userHomePage.clickSelectAvailableDates()
       // userHomePage.clickLogout()

    }


}
