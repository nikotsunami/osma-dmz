package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import sp_portal.pages.*;

class ManageTrainingTests {
    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper()


    @Test
    public void test001CreateTraining(){
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        def manageTrainingPage = adminPage.clickManageTrainingDays();
        def newTrainingPage = manageTrainingPage.clickNewTraining();


        // (name, dateYear,dateMonth,dateDay,timeStartHour,timeStartMinute,timeEndHour,timeEndMinute )
        newTrainingPage.createTraining( "training1", 2012, 5, 6, "9", "15", "14", "30")

        newTrainingPage.assertTextPresent("training1")
        newTrainingPage.assertTextPresent("2012-05-06")
        newTrainingPage.assertTextPresent("9:15:00")
        newTrainingPage.assertTextPresent("14:30:00")

        adminPage.clickLogout()

    }


    @Test
    public void test002ForBug659(){
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        def manageTrainingPage = adminPage.clickManageTrainingDays();
        def newTrainingPage = manageTrainingPage.clickNewTraining();

        newTrainingPage.enterName("training abc")

        newTrainingPage.selectDate(2012, 5, 6)

        newTrainingPage.setStartHour("9")

        newTrainingPage.setEndHour("15")
        newTrainingPage.setEndMinute("45")


        newTrainingPage.clickButton("Create");

       // newTrainingPage.assertTextPresent("Start time is empty")


// Try another with end time rmpty

/*
Some strangeness

        adminPage.clickManageTrainingDays();

        manageTrainingPage.clickNewTraining();


        newTrainingPage.enterName("training def")

        newTrainingPage.selectDate(2012, 5, 6)

        newTrainingPage.setStartHour("9")
        newTrainingPage.setEndMinute("30")
        newTrainingPage.setEndHour("15")



        newTrainingPage.clickButton("Create");
       // newTrainingPage.assertTextPresent("End time is empty")

*/



        adminPage.clickLogout()

    }



}
