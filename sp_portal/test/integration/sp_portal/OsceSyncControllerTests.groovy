package sp_portal

import static org.junit.Assert.*
import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.web.json.*;
import grails.converters.deep.JSON
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import grails.converters.deep.JSON;

class OsceSyncControllerTests extends GroovyTestCase{

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void _testjsonToGroovy(){
		def controller = new OsceSyncController()
		def resp = controller.jsonToGroovy(getTestData());
		
		assertEquals "2012-06-18T00:00:00Z" , resp.osceDay[0].osceDate
	
	
	}

   
   
   /**
	* test the json data not exist at DMZ database
    */
   void testSync(){
   
		def controller = new OsceSyncController()
		
		def jsonData = controller.jsonToGroovy(getTestData());
		def model = controller.sync(jsonData)
		
		def response = controller.response;
		
		
		def semesters = local.Semester.list();
		
		//TODO has another attribute need test
		assertNotNull semesters;
		assertEquals semesters[0].origId,1;
		assertEquals semesters[0].semester,0;
		assertEquals semesters[0].calYear,2011;
		
		def osces = local.Osce.list();
		assertNotNull osces;
		assertEquals osces[0].origId,1;
		assertEquals osces[0].studyYear,3;
		assertEquals osces[0].numberRooms,16;
		assertEquals osces[0].name,"Test 1";
		
		def osceDays =local.OsceDay.list();
		def osce=local.Osce.findByOrigId(1);
		 
		assertNotNull osceDays;
		assertEquals osceDays[0].osceDate,convertToDate("2012-06-18T00:00:00Z");
		assertEquals osceDays[0].timeStart,convertToDate("2012-06-18T09:00:00Z");
		assertEquals osceDays[0].timeEnd,convertToDate("2012-06-18T19:00:00Z");
		assertEquals osceDays[0].lunchBreakStart,null;
		assertEquals osceDays[0].lunchBreakAfterRotation,null;
		assertEquals osceDays[0].value,null;
		assertEquals osceDays[0].osce,osce;
		
		
		def trainings = local.Training.list();
		
		def semesterTraining= local.Semester.findByOrigId(1);
		assertNotNull trainings;
		assertEquals trainings[0].name,"Test 1";
		assertEquals trainings[0].timeStart,convertToDate("2012-09-28T08:59:46Z");
		assertEquals trainings[0].trainingDate,convertToDate("2012-09-28T10:59:54Z");
		assertEquals trainings[0].timeEnd,convertToDate("2012-09-28T10:59:37Z");
		assertEquals trainings[0].semester,semesterTraining;
		
   }
   
   /**
	* test the json data osce,training,semester exist at DMZ database
    */
   void testSyncExistOsceAndTrainingsAndsemester(){
   
		def controller = new OsceSyncController()
		
		def jsonData = controller.jsonToGroovy(getTestData());
		def model = controller.sync(jsonData)
		
		def existJsonData = controller.jsonToGroovy(getTestData2());
		controller.sync(existJsonData)
		
		def semesters = local.Semester.list();
		
		//TODO has another attribute need test
		assertNotNull semesters;
		assertEquals semesters[0].origId,1;
		assertEquals semesters[0].semester,1;
		assertEquals semesters[0].calYear,2013;
		
		def osces = local.Osce.list();
		assertNotNull osces;
		assertEquals osces[0].origId,1;
		assertEquals osces[0].studyYear,1;
		assertEquals osces[0].numberRooms,17;
		assertEquals osces[0].name,"Test 1";
		
		def osceDays =local.OsceDay.list();
		def osce=local.Osce.findByOrigId(1);
		 
		assertNotNull osceDays;
		assertEquals osceDays[0].osceDate,convertToDate("2012-06-18T00:00:00Z");
		assertEquals osceDays[0].timeStart,convertToDate("2012-06-18T09:00:00Z");
		assertEquals osceDays[0].timeEnd,convertToDate("2012-06-18T19:00:00Z");
		assertEquals osceDays[0].lunchBreakStart,null;
		assertEquals osceDays[0].lunchBreakAfterRotation,null;
		assertEquals osceDays[0].value,null;
		assertEquals osceDays[0].osce,osce;
		
		
		def trainings = local.Training.list();
		
		def semesterTraining= local.Semester.findByOrigId(1);
		assertNotNull trainings;
		assertEquals trainings[0].name,"Test 1";
		assertEquals trainings[0].timeStart,convertToDate("2012-09-28T08:59:46Z");
		assertEquals trainings[0].trainingDate,convertToDate("2012-09-28T10:59:54Z");
		assertEquals trainings[0].timeEnd,convertToDate("2012-09-28T10:59:37Z");
		assertEquals trainings[0].semester,semesterTraining;
		
		assertEquals trainings[1].name,"Test 1";
		assertEquals trainings[1].timeStart,convertToDate("2012-09-29T08:59:46Z");
		assertEquals trainings[1].trainingDate,convertToDate("2012-09-29T10:59:54Z");
		assertEquals trainings[1].timeEnd,convertToDate("2012-09-29T10:59:37Z");
		assertEquals trainings[1].semester,semesterTraining;
		
		
		
   }
   
   
   /**
	* test return Message of exist semesters,trainings,osces
    */
   void testReturnMessage2(){
   
		def controller = new OsceSyncController()
		
		def jsonData = controller.jsonToGroovy(getTestData());
		def model = controller.sync(jsonData)
		
		def response = controller.response;
		
		def notExistJson = JSON.parse(response.text);	
		
		def notExistmsg = notExistJson.message;
		
		assertNotNull notExistmsg;
		assertEquals "Semester for id 1 created in DMZ" ,notExistmsg[0].key.toString();
		assertEquals "Osce for id 1 created in DMZ" ,notExistmsg[1].key.toString();
		assertEquals "Osce for date 2012-06-18T00:00:00Z created in DMZ" ,notExistmsg[2].key.toString();
		assertEquals "Training for date 2012-09-28T08:59:46Z created in DMZ" ,notExistmsg[3].key.toString();

   }


	private String getTestData(){
	 def json = """
		{
		   "semesters":[
				  {
					 "id":1,
					 "semester":0,
					 "calYear":2011,
					 "maximalYearEarnings":null,
					 "pricestatist":null,
					 "priceStandardizedPartient":null,
					 "preparationRing":null
				  }
		   ],
		   "osces":[
			  {
				 "id":1,
				 "studyYear":3,
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
				 "numberRooms":16,
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
				 "osceDate":"2012-06-18T00:00:00Z",
				 "timeStart":"2012-06-18T09:00:00Z",
				 "timeEnd":"2012-06-18T19:00:00Z",
				 "lunchBreakStart":null,
				 "lunchBreakAfterRotation":null,
				 "osce":1,
				 "value":null
			  }
		   ],
		   "trainings":[
			  {
				 "name":"Test 1",
				 "trainingDate":"2012-09-28T10:59:54Z",
				 "timeStart":"2012-09-28T08:59:46Z",
				 "timeEnd":"2012-09-28T10:59:37Z",
				 "semester":1
              }

		   ],
		   "language":"en"
		}
	 """
		return json;
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
				 "osceDate":"2012-06-18T00:00:00Z",
				 "timeStart":"2012-06-18T09:00:00Z",
				 "timeEnd":"2012-06-18T19:00:00Z",
				 "lunchBreakStart":null,
				 "lunchBreakAfterRotation":null,
				 "osce":1,
				 "value":null
			  }
		   ],
		   "trainings":[
			  {
				 "name":"Test 1",
				 "trainingDate":"2012-09-29T10:59:54Z",
				 "timeStart":"2012-09-29T08:59:46Z",
				 "timeEnd":"2012-09-29T10:59:37Z",
				 "semester":1
              }

		   ],
		   "language":"en"
		}

		
		""";
		return json;
	}
	
	

	private String getExpectedReturnJsonData2(){
		def json  = """
		{
			{
				   "message":[
					  {
						 
					  },
					  {
						 
					  },
					  {
						 "key":"Osce for date 2012-06-18T00:00:00Z created in DMZ"
					  },
					  {
						 "key":"Training for date 2012-09-28T08:59:46Z created in DMZ"
					  }
				   ],
				   "osceDay":[
					  {
						 "osceDate":"2012-06-18T00:00:00Z",
						 "timeStart":"2012-06-18T09:00:00Z",
						 "timeEnd":"2012-06-18T19:00:00Z",
						 "lunchBreakStart":null,
						 "lunchBreakAfterRotation":null,
						 "osce":null,
						 "value":null
					  }
				   ],
				   "trainings":[
					  {
						 "name":"Test 1",
						 "trainingDate":"2012-09-28T10:59:54Z",
						 "timeStart":"2012-09-28T08:59:46Z",
						 "timeEnd":"2012-09-28T10:59:37Z",
						 "semester":1
					  }
				   ],
				   "patientInSemester":[

				   ]
		}
		
		""";
		return json;
	}
	
//	private String getTestData4(){
//	    def json  = """
//		{
//			  languages :[{language: "en"}],
//			  osceDay : [ {osceDate: "2010-07-18T16:00:00Z"},
//							{osceDate: "2010-07-12T16:00:00Z"}, //Test database data does not exist
//							{osceDate: "2010-07-10T16:00:00Z"}
//							],
//			  trainings : [ {name: "test1",
//							trainingDate: "2010-07-10T00:00:00Z",
//							timeStart: "2010-07-10T05:15:00Z",
//							timeEnd: "2010-07-10T14:00:00Z"},
//							{name: "test2",
//							trainingDate: "2010-05-10T00:00:00Z",
//							timeStart: "2010-05-10T09:20:00Z",
//							timeEnd: "2010-05-10T11:00:00Z"},
//							{name: "test3",                              //Test the data sent a record in a database and the condition of the same data
//							trainingDate: "2000-05-10T00:00:00Z",
//							timeStart: "2000-05-10T09:20:00Z",
//							timeEnd: "2000-05-10T11:00:00Z"},
//							{name: "test4", 							 //	Test the data sent the database exist, and with the original record is different, modify the recorded data
//							trainingDate: "2000-05-10T00:00:00Z",
//							timeStart: "2000-05-10T09:20:00Z",
//							timeEnd: "2000-05-10T11:00:00Z"}],
//		   standardizedPatient: [ {id: 5,								//Test database having this record
//										preName: "shen",
//										name: "yangsong"
//								},
//								 {id: 10,
//									preName: "sheng",
//									name: "yangyang"
//								},
//								 {id: 1,
//									preName: "sheng",
//									name: "xiang"
//								}
//						]
//
//			}
//
//		
//		""";
//		return json;
//	}

	
	//{"class":"sp_portal.local.OsceDay","id":1,"osceDate":"2012-07-12T08:15:47Z"},{"class":"sp_portal.local.OsceDay","id":2,"osceDate":"3912-07-04T16:00:00Z"}
	private void initOsceDayData(Date date){
		def osceDay = new local.OsceDay();
		osceDay.osceDate = date
		osceDay.save();
	}
	
	private void initTrainingData(String name,Date trainingDate,Date timeStart,Date timeEnd){
		def training = new local.Training();
		training.name = name;
		training.trainingDate = trainingDate;
		training.timeStart = timeStart;
		training.timeEnd = timeEnd;
		
		training.save();
	}
	
	
	private Date convertToDate(String dateStr){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


}
