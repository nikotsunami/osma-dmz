import static org.junit.Assert.*
import BootStrap
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

    def instance = null;

    @Before
    void setUp() {
        instance = new BootStrap()
    }

    @After
    void tearDown() {
        instance = null;
    }


    /**
     * Test that if there are no roles in the database 2 are created for ADMIN and USER
     */
    void testRoleCreatedInEmptyDb() {

        def rolesAtStart = sp_portal.Role.findAll();
        assert rolesAtStart.size() == 0

        instance.init(null);

        def rolesAtEnd = sp_portal.Role.findAll();
        assert rolesAtEnd.size() == 2

        def roleAdmin = sp_portal.Role.findByRoleName("ADMIN_ROLE");
        assertNotNull roleAdmin;
        assertEquals "Administrate Users",roleAdmin.roleDescription

        def roleUser = sp_portal.Role.findByRoleName("USER_ROLE");
        assertNotNull roleUser;
        assertEquals "Normal Users",roleUser.roleDescription

    }

    /**
     * Test that if there are already roles nothing happens
     */
    void testNothingHappensIfRolesAlreadyThere() {

        def fakeRole = new sp_portal.Role()
        fakeRole.roleDescription = "Paul's Fake Role"
        fakeRole.roleName = "FAKE_ROLE"
        fakeRole.save();

        def rolesAtStart = sp_portal.Role.findAll();
        assert rolesAtStart.size() == 1

        instance.init(null);

        def rolesAtEnd = sp_portal.Role.findAll();
        assert rolesAtEnd.size() == 1

        def roleAdmin = sp_portal.Role.findByRoleName("FAKE_ROLE");
        assertNotNull roleAdmin;
        assertEquals "Paul's Fake Role",roleAdmin.roleDescription


    }

}
