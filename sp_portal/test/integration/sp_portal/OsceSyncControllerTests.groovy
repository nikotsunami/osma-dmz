package sp_portal

import static org.junit.Assert.*
import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.web.json.*;
import grails.converters.deep.JSON
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class OsceSyncControllerTests extends GroovyTestCase{

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testjsonToGroovy(){
		def controller = new OsceSyncController()

	
		
	
		def resp = controller.jsonToGroovy(getTestData());
		
		assertEquals "2010-07-18T16:00:00Z" , resp.osceDay[0].osceDate
	
	
	}


    void testSyncNotExistAtDB(){
		def patient =new local.StandardizedPatient();
		patient.origId = 5;
		patient.preName = "shen"
		patient.name = "yangsong"
		patient.save();
		
		initOsceDayData(new Date());
		initOsceDayData(convertToDate("2010-07-12T16:00:00Z"));
		
		initTrainingData("training1",new Date(2012,05,30),new Date(2012,05,30,10,30,30),new Date(2012,05,30,15,30,30))
		initTrainingData("test3",convertToDate("2000-05-10T00:00:00Z"),convertToDate("2000-05-10T09:20:00Z"),convertToDate("2000-05-10T11:00:00Z"))
		initTrainingData("test4",convertToDate("1990-05-10T00:00:00Z"),convertToDate("1990-05-10T09:20:00Z"),convertToDate("1990-05-10T11:00:00Z"))
		
		
		
		def list = local.OsceDay.list();
		def training = local.Training.list();
		assertEquals 2,list.size();
		assertEquals 3,training.size();

		def controller = new OsceSyncController()
		
		def response = controller.response;

		def jsonData = controller.jsonToGroovy(getTestData());
		def model = controller.sync(jsonData)
		
		def list2 = local.OsceDay.list();

		assertEquals 4,list2.size();
		Date exceptedDate = convertToDate("2010-07-18T16:00:00Z");
		Date exceptedDate1 = convertToDate("2010-07-10T16:00:00Z");

		def osceDay = local.OsceDay.findByOsceDate(exceptedDate);
		def osceDay1 = local.OsceDay.findByOsceDate(exceptedDate1);
		
		assertNotNull osceDay.osceDate;
		assertNotNull osceDay1.osceDate;
		assertEquals exceptedDate, osceDay.osceDate;
		assertEquals exceptedDate1, osceDay1.osceDate;
		
		def trainingList = local.Training.list();
		assertEquals 5,trainingList.size();
		
		def t1 = local.Training.findByTimeStart(convertToDate("2010-07-10T05:15:00Z"));
		def t2 = local.Training.findByTimeStart(convertToDate("2010-05-10T9:20:00Z"));
		
		assertNotNull t1;
		assertEquals "test1", t1.name;
		assertEquals convertToDate("2010-07-10T00:00:00Z"), t1.trainingDate;
		assertEquals convertToDate("2010-07-10T05:15:00Z"), t1.timeStart;
		assertEquals convertToDate("2010-07-10T14:00:00Z"), t1.timeEnd;
		
		assertNotNull t2;
		assertEquals "test2", t2.name;
		assertEquals convertToDate("2010-05-10T00:00:00Z"), t2.trainingDate;
		assertEquals convertToDate("2010-05-10T9:20:00Z"), t2.timeStart;
		assertEquals convertToDate("2010-05-10T11:00:00Z"), t2.timeEnd;
		
		def p = local.StandardizedPatient.list();
		assertNotNull p
		assertEquals 1,p.size();
		assertEquals 5,p[0].origId;
		assertEquals "shen",p[0].preName;
		assertEquals "yangsong",p[0].name;
		
   }

	private String getTestData(){
	 def json  = """
		{

			  osceDay : [ {osceDate: "2010-07-18T16:00:00Z"},
							{osceDate: "2010-07-12T16:00:00Z"}, //Test database data does not exist
							{osceDate: "2010-07-10T16:00:00Z"}
							],
			  trainings : [ {name: "test1",
							trainingDate: "2010-07-10T00:00:00Z",
							timeStart: "2010-07-10T05:15:00Z",
							timeEnd: "2010-07-10T14:00:00Z"},
							{name: "test2",
							trainingDate: "2010-05-10T00:00:00Z",
							timeStart: "2010-05-10T09:20:00Z",
							timeEnd: "2010-05-10T11:00:00Z"},
							{name: "test3",                              //Test the data sent a record in a database and the condition of the same data
							trainingDate: "2000-05-10T00:00:00Z",
							timeStart: "2000-05-10T09:20:00Z",
							timeEnd: "2000-05-10T11:00:00Z"},
							{name: "test4", 							 //	Test the data sent the database exist, and with the original record is different, modify the recorded data
							trainingDate: "2000-05-10T00:00:00Z",
							timeStart: "2000-05-10T09:20:00Z",
							timeEnd: "2000-05-10T11:00:00Z"}],
		   standardizedPatient: [ {id: 5,								//Test database having this record
										preName: "shen",
										name: "yangsong"
								},
								 {id: 10,
									preName: "sheng",
									name: "yangyang"
								},
								 {id: 1,
									preName: "sheng",
									name: "xiang"
								}
						]

			}

		
		""";
		return json;
	}
	
	
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
