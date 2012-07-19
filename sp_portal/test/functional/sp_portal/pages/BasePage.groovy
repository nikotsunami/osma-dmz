package sp_portal.pages

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverPage
import org.openqa.selenium.*
import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.Select;

abstract class BasePage extends WebDriverPage {
    def baseUrl = "http://localhost:8090/sp_portal"


    def BasePage(){
        super();
    }

    def BasePage(driver){
        this.driver = driver;
    }

    def clickButton(text){
        driver.findElement(By.xpath("//input[@value='" + text + "']")).click();
        confirmNoErrors()
    }

    def fillOutField(id,value){
        driver.findElement(By.xpath("//*[@id='" + id + "']")).sendKeys(value);
    }


    def clickLink(text){
        driver.findElement(By.linkText(text)).click();
        confirmNoErrors()
    }

    def clickOptionValue(text){
        driver.findElement(By.xpath("//select/option[.='" + text + "']")).click();

    }


    def clickLinkWithHref(href){
        driver.findElement(By.xpath("//a[@href='" + href + "']")).click();
        confirmNoErrors()
    }

    boolean isTextPresent(text){
        driver.findElement(By.tagName("body")).getText().contains(text)

    }

    def assertTextPresent(text){
        assertTrue("<" + text + "> Not found ", isTextPresent(text) );
    }

    def confirmNoErrors(){
        assertFalse("Error Page encountered at " + driver.getCurrentUrl() , isTextPresent("Error 500"));
    }


    /**
     * id is the trigger button e.g. trainingDate-trigger
     */
    def selectDateFromCalendar(id, year, month, day){
        // first press the calendar button
        driver.findElement(By.xpath("//*[@id='" + id + "']")).click();

        // next press the year drop down
        def yearSelectorBK = driver.findElement(By.xpath("//div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][1]"))
        def yearSelectorFWD = driver.findElement(By.xpath("//div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][4]"))
        def monthSelectorBK = driver.findElement(By.xpath("//div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][2]"))
        def monthSelectorFWD = driver.findElement(By.xpath("//div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][3]"))

        int currentYear = getYear();
        int currentMonth = getMonth();


        if (year < currentYear){

            for (int i = year; i < currentYear; i++){

                yearSelectorBK.click();
            }

        } else {

            for (int i = currentYear; i < year; i++){
                yearSelectorFWD.click();
            }
        }

        if (month < currentMonth){
            for (int i = month; i < currentMonth; i++){
                monthSelectorBK.click();
            }

        } else {
            for (int i = currentMonth; i < month; i++){
                monthSelectorFWD.click();
            }
        }

        driver.findElement(By.xpath("//td[contains(@class ,'day') and .='" + day + "']")).click()


    }


    def selectDropDown(id, test){

        new Select(driver.findElement(By.id(id))).selectByVisibleText(""+test);

    }

    def screenshot(name){

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("." +name));



    }


    private int getDay(){

        //
        // Get various information from the Date object.
        //
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);

        return day;
    }


    private int getMonth(){

        //
        // Get various information from the Date object.
        //
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;

        return month;
    }

    private int getYear(){

          //
        // Get various information from the Date object.
        //
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return year;
    }



}
