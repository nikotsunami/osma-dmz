package sp_portal.tests

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.junit.After
import sp_portal.pages.*;

class ManageTrainingTests {
    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper()


	private static boolean initialized = false;
	
	@Before
	public void initialize(){
		if (!initialized){
			String dataStr = getTestData2();
			dataStr = dataStr.replaceAll("\n", "")
			dataStr = dataStr.replaceAll("  ", "")


			def dataImpExpPage  = webdriver.open('/osceSync/test',OsceSynPage)

			dataImpExpPage.submitData(dataStr);
			initialized = true;
		}
	}
	
	@After
	public void deleteOutputFile(){
		webdriver = null;
	}

    @Test
    public void test001CreateTraining(){
        LoginPage loginPage = webdriver.open('/', LoginPage)
        def adminPage = loginPage.loginAdmin();
        def manageTrainingPage = adminPage.clickManageTrainingDays();
        def newTrainingPage = manageTrainingPage.clickNewTraining();


        // (name, dateYear,dateMonth,dateDay,timeStartHour,timeStartMinute,timeEndHour,timeEndMinute )
        newTrainingPage.createTraining( "training1", 2012, 5, 6, "9", "15", "14", "30","2013 FS")

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
	
	
	 private String getTestData2(){
        def json  = """
        {
		   "semesters":[
				  {
					 "id":1,
					 "semester":1,
					 "calYear":2013,
					 "maximalYearEarnings":null,
					 "pricestatist":null,
					 "priceStandardizedPartient":null,
					 "preparationRing":null
				  }
		   ],
		   "osces":[
			  {
				 "id":1,
				 "studyYear":1,
				 "maxNumberStudents":130,
				 "name":"Test 1",
				 "shortBreak":1,
				 "LongBreak":15,
				 "lunchBreak":45,
				 "middleBreak":5,
				 "numberPosts":null,
				 "numberCourses":0,
				 "postLength":13,
				 "isRepeOsce":false,
				 "numberRooms":17,
				 "isValid":true,
				 "osceStatus":2,
				 "security":1,
				 "osceSecurityTypes":1,
				 "patientAveragePerPost":null,
				 "semester":1,
				 "shortBreakSimpatChange":3,
				 "copiedOsce":null
			  }
		   ],
		   "osceDay":[
			  {
				 "osceDate": "2010-08-01T08:00:00Z",
				 "timeStart":"2012-06-18T09:00:00Z",
				 "timeEnd":"2012-06-18T19:00:00Z",
				 "lunchBreakStart":null,
				 "lunchBreakAfterRotation":null,
				 "osce":1,
				 "value":null
			  },
			  {	"osceDate": "",
				"timeStart":null,
				 "timeEnd":null,
				 "lunchBreakStart":null,
				 "lunchBreakAfterRotation":null,
				 "osce":1,
				 "value":null}
		   ],
		   
            "trainings" : [ {"name": "",
                            "trainingDate": "2000-06-10T00:00:00Z",
                            "timeStart": "2000-06-10T09:20:00Z",
                            "timeEnd": "2000-06-10T11:00:00Z",
							"semester":1},
                            {"name": "test6",
                            "trainingDate": "",
                            "timeStart": "2000-05-10T09:20:00Z",
                            "timeEnd": "2000-05-10T11:00:00Z",
							"semester":1},
                            {"name": "test2",
                            "trainingDate": "2012-05-10T00:00:00Z",
                            "timeStart": "2012-05-10T09:20:00Z",
                            "timeEnd": "2012-05-10T11:00:00Z",
							"semester":1},
                            {"name": "test7",
                            "trainingDate": "2010-07-10T00:00:00Z",
                            "timeStart": "2010-07-10T05:15:00Z",
                            "timeEnd": "2010-07-10T09:00:00Z",
							"semester":1}],
				"language":"en"
            }

            """


        return json.toString();
    }
	



}
