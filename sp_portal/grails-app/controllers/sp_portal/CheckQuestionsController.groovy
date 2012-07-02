package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import java.util.ArrayList;
import java.util.List;

//QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

class CheckQuestionsController  extends MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsUser]





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


                [questionTitle:questionTitle,question1:question1, question2:question2,questionMum:questionMum,questionOpen: questionOpen]
    }


    def index() {
        redirect(action: "show", params: params)
    }
    
    def titles = local.AnamnesisCheckTitle.findAll() 
    

    def show() {
       titleIndex = 0;
       session.titleIndex = titleIndex;


    }


    def showPage() {
         def patient = getCurrentPatient();
         int index =  Math.min(params.index ? params.int('index') : 0, 100);
            def currentTitle = null;
           if(params.index == null){
                params.index = 0;
           }

           currentTitle = titles[index];

            if(currentTitle){

                    def questions = [];
                    def validValue = [];
                    def checkValue = [];
                    if (currentTitle != null){
			        		questions = local.AnamnesisCheck.findAllByAnamnesisCheckTitle(currentTitle,[sort:"sortOrder"]);
                              checkValue = local.AnamnesisChecksValue.findAllByAnamnesisForm(patient.standardizedPatient.anamnesisForm);

                    }
                    if(checkValue!=null){

                            [title: currentTitle , questions: questions,titleSize: titles.size,checkValue: checkValue]

                    }


            }else{
                render message(code: 'checkquestion.noneavailable');
            }

    }

    static int titleIndex = 0;

    private void setSessionTitleIndex(){
       session.titleIndex = titleIndex;
    }

    def showNext(){
            titleIndex++;
            if(titleIndex >= titles.size){
                //titleIndex = titles.size-1;
                saveData();
                redirect(controller:"thank", action:"thank")
        }else{
            setSessionTitleIndex();
            saveData();
            redirect(action: "showPage", params: [index: titleIndex])
        }

    }

    def showPreviou(){
            titleIndex--;
            if(titleIndex < 0){
                titleIndex = 0;
        }
        setSessionTitleIndex();
        saveData();
        redirect(action: "showPage", params: [index: titleIndex])
    }

    def showFirst(){
            titleIndex = 0;
            setSessionTitleIndex();
            saveData();
        redirect(action: "showPage", params: [index: titleIndex])
    }

    def showEnd(){
            titleIndex = titles.size-1;
            setSessionTitleIndex();
            saveData();
        redirect(action: "showPage", params: [index: titleIndex])
    }


    def save(){
                saveData();
                redirect(action: "showPage",params: [index: titleIndex])
    }

    private void saveData(){
        def patient = getCurrentPatient();
        def questionIdStrings = params.findAll({ key, value ->
													key.startsWith("question")
													});

        def questionIds = questionIdStrings.each({ key, value ->


            def components = key.split("\\.");


            // If there are 2 parts to the string then it is a valid "question".id format
            if (components.size() == 2){
                    def questionId = components[1];

                 def checkInstance = local.AnamnesisCheck.get(Long.valueOf(questionId));

                 valueStr = "";
                 setValueStr(checkInstance,questionId,value);

           if(patient.standardizedPatient.anamnesisForm!=null || checkInstance!=null){

                   def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(patient.standardizedPatient.anamnesisForm,checkInstance);
                   if(checkValue!=null){
						checkValue.anamnesisForm = patient.standardizedPatient.anamnesisForm;
						if(checkValue!=null){
						  checkValue.anamnesisCheck = checkInstance;
						}
						checkValue.anamnesisChecksValue=valueStr;
						
						checkValue.comment = null;
						System.out.println("????????????text = "+checkInstance.getText());
						System.out.println("????????????type = "+checkInstance.getType());
						System.out.println("????????????valueStr = "+valueStr);
						if(checkInstance.getType()==1){
							System.out.println("type == 1");
							if(valueStr.equals("0")){
								checkValue.truth = false;
							}else if(valueStr.equals("1")){
								checkValue.truth = true;
							}else{
								System.out.println("????????????");
								checkValue.truth = null;
							}
						}else{
							System.out.println("type != 1");
							checkValue.truth = null;
						}
						if(!valueStr.equals("")){
							checkValue.save();
						}
                   }else{

						local.AnamnesisChecksValue checkValueInstance = new local.AnamnesisChecksValue();
						checkValueInstance.anamnesisForm = patient.standardizedPatient.anamnesisForm;
						if(checkInstance!=null){
						  checkValueInstance.anamnesisCheck = checkInstance;
						}
						checkValueInstance.anamnesisChecksValue=valueStr;
						
						checkValueInstance.comment = null;
						System.out.println("????????????text = "+checkInstance.getText());
						System.out.println("????????????type = "+checkInstance.getType());
						System.out.println("????????????valueStr = "+valueStr);
						if(checkInstance.getType()==1){
							if(valueStr.equals("0")){
								checkValueInstance.truth = false;
							}else if(valueStr.equals("1")){
								checkValueInstance.truth = true;
							}else{
								System.out.println("????????????");
								checkValueInstance.truth = null;
							}
						}else{
							checkValueInstance.truth = null;
						}
						
						if(!valueStr.equals("")){
							checkValueInstance.save();
						 }
                         }
                     }

           }

        });

    }


    static String valueStr =" ";

    /*
     * set the submit answer
     */
    def setValueStr(checkInstance,questionId,value){


          boolean isTrue = false;
          String [] possibleValues = checkInstance.value.split("\\|");

          def submittedValues = value;

          if(checkInstance.type == AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()){
             if(submittedValues.equals("true")){
                  valueStr = "1-";
               }else{
                  valueStr = "0-";
               }
          }


          if(checkInstance.type == AnamnesisCheckTypes.QUESTION_MULT_S.getTypeId()){


                  for(String currentPossibleValue : possibleValues){

                     def found = false;
                 if(submittedValues.equals(currentPossibleValue)){

                        found = true;
                 }

                     if(!found){
                         valueStr+="0-";
                         isTrue = false;
                     } else {
                         valueStr+="1-";
                     }
                  }
          }

          if(checkInstance.type == AnamnesisCheckTypes.QUESTION_MULT_M.getTypeId()){


                 for(String currentPossibleValue : possibleValues){
                    def found = false;

                        if(!(submittedValues instanceof String)){
                             for(String currentSubmittedValue : submittedValues){
                                     if(currentSubmittedValue.equals(currentPossibleValue)){
                                            found = true;
                                     }
                                 }



                         }else{
                                 if(submittedValues.equals(currentPossibleValue)){
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

          if(checkInstance.type == AnamnesisCheckTypes.QUESTION_OPEN.getTypeId()){
                    if(submittedValues!=null){
                      valueStr = submittedValues;
                    }
          }

          if(checkInstance.type != AnamnesisCheckTypes.QUESTION_OPEN.getTypeId()){

              if(valueStr !=null && !valueStr.equals("")){
                valueStr = valueStr.substring(0,valueStr.length()-1);
              }
          }


            return checkInstance;
    }


    private getCurrentPatient(){
        def patient = User.findById(session.user.id);
        return patient;
    }


}
