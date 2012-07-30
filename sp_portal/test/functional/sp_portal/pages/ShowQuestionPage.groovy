package sp_portal.pages
import org.junit.*
import grails.test.mixin.*
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ShowQuestionPage extends BasePage {
    static expectedTitle = "showPage"
    

    static elements = {
    }

    def ShowQuestionPage(driver){
        super(driver);

    }
	def clickSave(){
		
		clickButton("Save");
	}
	
	def clickNextBtn(){
		clickButton(">");
	}
	def clickPrevious(){
		
		clickButton("<");
	}
	
	def clickLast(){
		
		clickButton(">>|");
	}
	def clickFirst(){
		
		clickButton("|<<");
	}
	
	
	
	def clickRadio(id,str){
		
		clickRadioQuestion(id,str);
	}
	def clickCheckBox(id,str){
		
		clickCheckBoxQuestion(id,str);
	}
	

  



}
