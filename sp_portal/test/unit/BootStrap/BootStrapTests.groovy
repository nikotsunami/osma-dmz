package BootStrap

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import sp_portal.DataSetupHelper;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock(sp_portal.Role)
class BootStrapTests {
	//def dase =new DataSetupHelper();
	
	@Before
    void setUp() {
        // Setup logic here
		  // dase.getDataSetA();
    }
	
	@After
    void tearDown() {
        // Tear down logic here
		//dase=null;
    }
	

    void testSomething() {
	      assert true
		  def role=new sp_portal.Role();
			assert role.save()!=null;
			assert role !=null;
			
      // fail "Implement me"
	  
    }
}
