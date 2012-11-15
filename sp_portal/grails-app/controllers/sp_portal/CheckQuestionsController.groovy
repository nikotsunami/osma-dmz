package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.LogFactory;

//QUESTION_OPEN(0), QUESTION_YES_NO(1), QUESTION_MULT_S(2), QUESTION_MULT_M(3), QUESTION_TITLE(4);

class CheckQuestionsController  extends MainController {

    def beforeInterceptor = [action:this.&isLoggedInAsUser]
	
	private static final log = LogFactory.getLog(this)




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
		log.info("index of CheckQuestions")
		log.info("user show CheckQuestions")
        titleIndex = 0;
        session.titleIndex = titleIndex;
	    log.info(params)
        redirect(action: "show", params: params)
    }
    
	
	def titlesValues = []
	
    def titles() {
		if (titlesValues.size() == 0){
			titlesValues = local.AnamnesisCheckTitle.findAll() 
		} 
		if(log.isTraceEnabled()){
			log.trace("<< In class CheckQuestionsController Method titles() return titlesValues : "+titlesValues)
		}
		return titlesValues
	
	}
    

    def show() {
	   
	   log.info(params)
	   log.info("user showPage of CheckQuestions")
        def patient = getCurrentPatient();
		
        int index =  Math.min(params.index ? params.int('index') : 0, 100);
        def currentTitle = null;
        if(params.index == null){
             params.index = 0;
        }

        currentTitle = titles()[index];
		if(log.isDebugEnabled()){
			log.debug("get currentTitle : "+currentTitle)
		}
        if(currentTitle){

            def questions = [];
            def validValue = [];
            def checkValue = [];
            if (currentTitle != null){
			    questions = local.AnamnesisCheck.findAllByAnamnesisCheckTitle(currentTitle,[sort:"sortOrder"]);
                checkValue = local.AnamnesisChecksValue.findAllByAnamnesisForm(patient.standardizedPatient.anamnesisForm);
				if(log.isDebugEnabled()){
					log.debug("find questions : "+questions)
					log.debug("find checkValue : "+checkValue)
				}
            }
            if(checkValue!=null){

                [title: currentTitle , questions: questions,titleSize: titles().size(),checkValue: checkValue]

            }


        }else{
            render message(code: 'checkquestion.noneavailable');
        }
		


    }


    def showPage() {
		log.info("user showPage of CheckQuestions")
        def patient = getCurrentPatient();
		
        int index =  Math.min(params.index ? params.int('index') : 0, 100);
        def currentTitle = null;
        if(params.index == null){
             params.index = 0;
        }

        currentTitle = titles()[index];
		if(log.isDebugEnabled()){
			log.debug("get currentTitle : "+currentTitle)
		}
        if(currentTitle){

            def questions = [];
            def validValue = [];
            def checkValue = [];
            if (currentTitle != null){
			    questions = local.AnamnesisCheck.findAllByAnamnesisCheckTitle(currentTitle,[sort:"sortOrder"]);
                checkValue = local.AnamnesisChecksValue.findAllByAnamnesisForm(patient.standardizedPatient.anamnesisForm);
				if(log.isDebugEnabled()){
					log.debug("find questions : "+questions)
					log.debug("find checkValue : "+checkValue)
				}
            }
            if(checkValue!=null){

                [title: currentTitle , questions: questions,titleSize: titles().size(),checkValue: checkValue]

            }


        }else{
            render message(code: 'checkquestion.noneavailable');
        }
		
    }

    static int titleIndex = 0;

    private void setSessionTitleIndex(){
        session.titleIndex = titleIndex;
		if(log.isTraceEnabled()){
			log.trace(">> In class CheckQuestionsController Method setSessionTitleIndex : "+session.titleIndex)
		}
    }

    def showNext(){
		log.info("user show next of CheckQuestions")
        titleIndex++;
		if(log.isDebugEnabled()){
			log.debug("titleIndex : "+titleIndex)
		}
        if(titleIndex >= titles().size()){
            //titleIndex = titles().size()-1;
            saveData();
            redirect(controller:"thank", action:"thank")
        }else{
            setSessionTitleIndex();
            saveData();
            redirect(action: "show", params: [index: titleIndex])
        }

    }

    def showPreviou(){
		log.info("user show previou of CheckQuestions")
        titleIndex--;
        if(titleIndex < 0){
        titleIndex = 0;
        }
		if(log.isDebugEnabled()){
			log.debug("titleIndex : "+titleIndex)
		}
        setSessionTitleIndex();
        saveData();
        redirect(action: "show", params: [index: titleIndex])
    }

    def showFirst(){
		log.info("user show first of CheckQuestions")
        titleIndex = 0;
        setSessionTitleIndex();
        saveData();
        redirect(action: "show", params: [index: titleIndex])
    }

    def showEnd(){
		log.info("user show end of CheckQuestions")
        titleIndex = titles().size()-1;
		if(log.isDebugEnabled()){
			log.debug("titleIndex : "+titleIndex)
		}
        setSessionTitleIndex();
        saveData();
        redirect(action: "show", params: [index: titleIndex])
    }


    def save(){
		log.info("user save CheckQuestions")
		if(log.isDebugEnabled()){
			log.debug("titleIndex : "+titleIndex)
		}
		if(titleIndex >= titles().size()-1){
			saveData();
			redirect(controller:"thank", action:"thank")
		}else{
			saveData();
			redirect(action: "show",params: [index: titleIndex])
		}
    }

    private void saveData(){
		log.info("user save data ")
        def patient = getCurrentPatient();		
        def questionIdStrings = params.findAll({ key, value ->
													key.startsWith("question")
													});
		if(log.isDebugEnabled()){											
			log.debug("find questionIdStrings : "+questionIdStrings)
		}
        def questionIds = questionIdStrings.each({ key, value ->
            def components = key.split("\\.");

            // If there are 2 parts to the string then it is a valid "question".id format
            if (components.size() == 2){
                def questionId = components[1];

                def checkInstance = local.AnamnesisCheck.get(Long.valueOf(questionId));
				if(log.isTraceEnabled()){									
					log.trace("find checkInstance : "+checkInstance)
				}
    //            valueStr = "";
                setValueStr(checkInstance,questionId,value);
				 
            if(patient.standardizedPatient.anamnesisForm!=null || checkInstance!=null){

                   def checkValue = local.AnamnesisChecksValue.findByAnamnesisFormAndAnamnesisCheck(patient.standardizedPatient.anamnesisForm,checkInstance);
				   if(log.isTraceEnabled()){
						log.trace("find checkValue : "+checkValue)
					}
                   if(checkValue!=null){
						checkValue.anamnesisForm = patient.standardizedPatient.anamnesisForm;
						if(checkValue!=null){
						  checkValue.anamnesisCheck = checkInstance;
						}
						checkValue.anamnesisChecksValue=valueStr;
						
						checkValue.comment = null;
						
						if(checkInstance.type == AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()){
							if(valueStr.equals("0")){
								checkValue.truth = false;
							}else if(valueStr.equals("1")){
								checkValue.truth = true;
							}else{														
								checkValue.truth = null;
							}
						}else{
							checkValue.truth = null;
						}						
							
						checkValue.save();
						if(log.isDebugEnabled()){
							StringBuffer sb = new StringBuffer();
							sb.append( "\n checkValue.origId: ");
							sb.append(checkValue?.origId);
							sb.append( "\n checkValue.anamnesisChecksValue: ");
							sb.append(checkValue?.anamnesisChecksValue);
							sb.append( "\n checkValue.comment: ");
							sb.append(checkValue?.comment);
							sb.append( "\n checkValue.truth: ");
							sb.append(checkValue?.truth);
							sb.append( "\n checkValue.anamnesisForm: ");
							sb.append(checkValue?.anamnesisForm);
							sb.append( "\n checkValue.anamnesisCheck: ");
							sb.append(checkValue?.anamnesisCheck);
							log.debug( "find checkValue and update to : " + sb.toString());
						} 
                   }else{

						local.AnamnesisChecksValue checkValueInstance = new local.AnamnesisChecksValue();
						checkValueInstance.anamnesisForm = patient.standardizedPatient.anamnesisForm;
						if(checkInstance!=null){
						  checkValueInstance.anamnesisCheck = checkInstance;
						}
						checkValueInstance.anamnesisChecksValue=valueStr;
						
						checkValueInstance.comment = null;
						
						if(checkInstance.type == AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()){
							if(valueStr.equals("0")){
								checkValueInstance.truth = false;
							}else if(valueStr.equals("1")){
								checkValueInstance.truth = true;
							}else{
								
								checkValueInstance.truth = null;
							}
						}else{
							checkValueInstance.truth = null;
						}
						
						if(!valueStr.equals("")){
							checkValueInstance.save();
						}
						 
						if(log.isDebugEnabled()){
							StringBuffer sb = new StringBuffer();
							sb.append( "\n checkValueInstance.origId: ");
							sb.append(checkValueInstance?.origId);
							sb.append( "\n checkValueInstance.anamnesisChecksValue: ");
							sb.append(checkValueInstance?.anamnesisChecksValue);
							sb.append( "\n checkValueInstance.comment: ");
							sb.append(checkValueInstance?.comment);
							sb.append( "\n checkValueInstance.truth: ");
							sb.append(checkValueInstance?.truth);
							sb.append( "\n checkValueInstance.anamnesisForm: ");
							sb.append(checkValueInstance?.anamnesisForm);
							sb.append( "\n checkValueInstance.anamnesisCheck: ");
							sb.append(checkValueInstance?.anamnesisCheck);
							log.debug( "not found checkValue and new checkValueInstance : " + sb.toString());
						} 
                    }
                }

           }

        });

    }


    static String valueStr;

    //  set the submit answer
    def setValueStr(checkInstance,questionId,value){
		if(log.isTraceEnabled()){
			log.trace(">> In class CheckQuestionsController Method setValueStr entered checkInstance : "+checkInstance+"  "+"questionId : "+questionId+"  "+"value : "+value)
		}
        boolean isTrue = false;
		String [] possibleValues = []
		if(checkInstance.value){
			possibleValues = checkInstance.value.split("\\|");
		}

        def submittedValues = value;
		
        if(checkInstance.type == AnamnesisCheckTypes.QUESTION_YES_NO.getTypeId()){
			if(log.isTraceEnabled()){
				log.trace("checkInstance type is QUESTION_YES_NO ")
			}
            if(submittedValues.equals("true")){
                valueStr = "1-";
            }else{
                valueStr = "0-";
            }
        }


        if(checkInstance.type == AnamnesisCheckTypes.QUESTION_MULT_S.getTypeId()){
			if(log.isTraceEnabled()){
				log.trace("checkInstance type is QUESTION_MULT_S ")
			}

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
			if(log.isTraceEnabled()){
				log.trace("checkInstance type is QUESTION_MULT_M ")
			}

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
			if(log.isTraceEnabled()){s
				log.trace("checkInstance type is QUESTION_OPEN ")
			}
            if(submittedValues!=null){
                valueStr = submittedValues;
            }
        }

        if(checkInstance.type != AnamnesisCheckTypes.QUESTION_OPEN.getTypeId()){

            if(valueStr !=null && !valueStr.equals("")){
                valueStr = valueStr.substring(0,valueStr.length()-1);
            }
        }
		if(log.isTraceEnabled()){
			log.trace("<< In class CheckQuestionsController Method setValueStr(checkInstance,questionId,value) end valueStr : "+value)
		}
		
				
		if(valueStr.size() == 0){
			valueStr = null;
		}

        return checkInstance;
    }


    private getCurrentPatient(){
        def patient = User.findById(session.user.id);		
		if(log.isTraceEnabled()){
			log.trace("<< In class CheckQuestionsController Method getCurrentPatient return current patient : "+patient)
		}
        return patient;
    }


}
