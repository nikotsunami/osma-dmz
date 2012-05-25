package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import java.util.ArrayList;
import java.util.List;

//QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

class CheckQuestionsController  extends MainController {

    

    def test() {

        local.AnamnesisCheck questionTitle = new local.AnamnesisCheck();  
				
				questionTitle.type = AnamnesisCheckTypes.QUESTION_TITLE.getTypeId()
				questionTitle.text = "Personal lifestyle category";

				local.AnamnesisCheck question1 = new local.AnamnesisCheck(); 
				
				question1.type = AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()
				question1.text = "Do you like cats?";

				local.AnamnesisCheck question2 = new local.AnamnesisCheck(); 
				
				question2.type = AnamnesisCheckTypes.QUESTION_MULT_S.getTypeId()
				question2.text = "What Type Of Cigarettes Have you Smoked?";
				question2.value = "Strike|Awesomesauce|Winfail";
				
				
				
				local.AnamnesisCheck questionMum = new local.AnamnesisCheck(); 
				
				questionMum.type = AnamnesisCheckTypes.QUESTION_MULT_M.getTypeId()
				questionMum.text = "Nehmen Sie eines der aufgelisteten Medikamete und wenn ja, welche?";
				questionMum.value = "Prozac|Ritalin|Aspirin|Ethanol";
				
				
				local.AnamnesisCheck questionOpen = new local.AnamnesisCheck(); 
				
				questionOpen.type = AnamnesisCheckTypes.QUESTION_OPEN.getTypeId()
				questionOpen.text = "Welche Medikamente nehmen Sie ein?";
				
				
    
    
    
    //Integer sortOrder
    //String text
   // Integer type
   // String value
   // Integer userSpecifiedOrder;
   // AnamnesisCheck title


				[questionTitle:questionTitle,question1:question1, question2:question2,questionMum:questionMum,questionOpen: questionOpen]         
    }


    def index() {
        redirect(action: "show", params: params)
    }
    
    def titles = local.AnamnesisCheck.findAllByType(AnamnesisCheckTypes.QUESTION_TITLE.getTypeId(),[sort:"id"]) // TODO order by sequence num

    def show() {

        int index =  Math.min(params.index ? params.int('index') : 0, 100);
    
        def currentTitle = null;
        if(params.index == titles.size){
           params.index = lastTitlesIndex;
        }
            
       currentTitle = titles[index];
        
        if(currentTitle){
              
		        def questions = [];
		        if (currentTitle != null){
		        		questions = local.AnamnesisCheck.findAllByTitle(currentTitle,[sort:"sortOrder"]);
		        		params.id = questions.id;
		        }
		       
		        [title: currentTitle , questions: questions,titleSize: titles.size]
        }else{
            redirect(action: "show")
        }      
    }
    
    static int titleIndex = 0;
    
    def showNext(){
    		titleIndex++; 
    		if(titleIndex >= titles.size){      
	    		titleIndex = titles.size-1;  
        }
        redirect(action: "show", params: [index: titleIndex])
    }
    
    def showPreviou(){
    		titleIndex--; 
    		if(titleIndex < 0){      
	    		titleIndex = 0;  
        }
        redirect(action: "show", params: [index: titleIndex])
    }
     
    def showFirst(){
    		titleIndex = 0;
        redirect(action: "show", params: [index: titleIndex])
    }
    
    def showEnd(){
    		titleIndex = titles.size-1;  
        redirect(action: "show", params: [index: titleIndex])
    }
    
    
    def save(){
    println("-----------------------------------------------------------------------------------");
   		 println("??????????save the answer params: "+ params);
 	  	 //session.user.userName;
   
    	 // def patient = local.StandardizedPatient.findById(session.user.standardizedPatient.id);
      println("BBBBBBBBBBBBBBBBBBBBBBBBB");
   		def questionIdStrings = params.findAll({ key, value ->
   																										println("shshshs key" + key);
   																										key.startsWith("question")
   																										});
   																										
			println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCc" + questionIdStrings);   																										
   		def questionIds = questionIdStrings.each({ key, value -> 
   		
   					println("XXXX " + key + " - " + value); 
   		
   			def components = key.split("\\.");
   			println("components.size() " + components.size());
   			if (components.size() == 2){
   				def questionId = components[1];
   			  println(" Process Question with id = " + questionId + " and answer = " + value);
   			  
   				
   			
   			
   			
   		def checkInstance = local.AnamnesisCheck.get(Long.valueOf(questionId));	
      String valueStr =" ";
      boolean isTrue = false;
      String [] possibleValues = checkInstance.value.split("\\|"); 
      println("----------------"+possibleValues.length);     
      
      
      
     // true false type question
      if(possibleValues.length == 0){
	       if(value.equals("true")){
	          valueStr = "1-";
	       }else{
	          valueStr = "0-";
	       }
      } else{
      
      // multi select 
      println("++++++++++++++: "+ possibleValues);
		      println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+value.size());
		      
		      def submittedValues = value;
		      
		      for(String currentPossibleValue : possibleValues){
		      
		      	 def found = false;
		      println("Possible value " + currentPossibleValue);
		         for(String currentSubmittedValue : submittedValues){
		         println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+currentSubmittedValue);
		            if(currentSubmittedValue.equals(currentPossibleValue)){
		            	
		            		found = true;
		            }
		         }
		         
		         
		         
		         if(!found){
		        	 valueStr+="0-";
		        	 isTrue = false;
		         } else {
		         	 valueStr+="1-";
		         }
		      }
      }
    
      
      valueStr = valueStr.substring(0,valueStr.length()-1);
    println("AAAAAAAAAAA the value of valueStr: "+valueStr);
   
       
    local.AnamnesisChecksValue checkValueInstance = new local.AnamnesisChecksValue();
   // 
   // println(">>>> show the answer instance: "+checkInstance);
   
 //  checkValueInstance.anamnesisForm = patient.anamnesisForm;
   // checkValueInstance.anamnesischeck=null;
      // checkValueInstance.origId = 10;
             
    //         checkValueInstance.comment = null;
     //        checkValueInstance.truth = null;
       //      checkValueInstance.anamnesisChecksValue=null;
            /* log("Anamnesis form " + checkValue.anamnesisForm);
             if(checkValue.anamnesisForm){
               newCheckValue.anamnesisForm = local.AnamnesisForm.findByOrigId(checkValue.anamnesisForm.id);//problem
             }
             if(checkValue.anamnesisCheck){
               newCheckValue.anamnesisCheck = local.AnamnesisCheck.findByOrigId(checkValue.anamnesisCheck.id);
             }
             newCheckValue.save();
             */
    //checkValueInstance.save();
   
			
   			
   			
   			}
   		
   			
   		
   		});
   		
   		
   
    redirect(action: "show", params: params)
    
    }
}
