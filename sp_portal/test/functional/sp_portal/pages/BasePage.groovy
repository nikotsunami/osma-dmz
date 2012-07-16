package sp_portal.pages

import org.codehaus.groovy.grails.plugins.webdriver.WebDriverPage
import org.openqa.selenium.*
import static org.junit.Assert.*;

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


}
