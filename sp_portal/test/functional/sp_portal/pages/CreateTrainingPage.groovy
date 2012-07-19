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
      //  assertTextPresent(expectedHeading);
    }

    def createTraining(name, dateYear,dateMonth,dateDay,timeStartHour,timeStartMinute,timeEndHour,timeEndMinute ){
        enterName(name)
        selectDateFromCalendar("trainingDate-trigger", dateYear, dateMonth, dateDay)


        setStartHour(timeStartHour);
        setStartMinute(timeStartMinute)
        setEndHour(timeEndHour)
        setEndMinute(timeEndMinute)

        clickButton("Create");

    }

    def enterName(str){
        fillOutField("name",str)
    }

    def setStartHour(int timeStartHour){
         selectDropDown("timeStartHour", timeStartHour)
    }


    def setStartMinute(int timeStartMinute){
         selectDropDown("timeStartMin", timeStartMinute)
    }

    def setEndHour(int timeEndHour){
         selectDropDown("timeEndHour", timeEndHour)
    }


    def setEndMinute(int timeEndMinute){
         selectDropDown("timeEndMin", timeEndMinute)
    }




}
