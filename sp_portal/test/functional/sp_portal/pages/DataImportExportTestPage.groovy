package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DataImportExportTestPage extends BasePage {
    static expectedHeading = ""

    static elements = {
    }

    def DataImportExportTestPage(){
        super()
    }

    def submitData(json){
        fillOutField("data",json)
        clickButton("Send")
    }


}
