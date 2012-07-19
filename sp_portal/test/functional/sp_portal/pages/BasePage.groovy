package sp_portal.pages

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverPage
import org.openqa.selenium.*
import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.apache.commons.io.FileUtils;

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
Thread.sleep(1000);
        screenshot("CalendarOpened");

        // next press the year drop down
        def yearSelector = driver.findElement(By.xpath("div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][1]"))
        def yearLabel = driver.findElement(By.xpath("div[@class='label' and .=" + year +"]"))


        Actions builder = new Actions(driver);

        Action choseYear = builder.clickAndHold(yearSelector)
           .moveToElement(yearLabel)
           .release(yearLabel)
           .build();

        choseYear.perform();

        // next press the year drop down
        def monthSelector = driver.findElement(By.xpath("div[@class='calendar']//tr[@class='headrow']//td[@class='button nav'][1]"))
        def monthLabel = driver.findElement(By.xpath("div[@class='label' and .=" + month +"]"))


        Actions builder2 = new Actions(driver);

        Action choseMonth = builder2.clickAndHold(monthSelector)
           .moveToElement(monthLabel)
           .release(monthLabel)
           .build();

        choseMonth.perform();

        driver.findElement(By.xpath("div[@class='day' and .=" + day + "]")).click()


    }


    def screenshot(name){

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("." +name));



    }


}
