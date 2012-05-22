package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;




class UserController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";


    def beforeInterceptor = [action:this.&isLoggedIn, except:["login", "authenticate", "index"]]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        log("IN ACTION LIST");
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
          params.isActive=true
        [userInstance: new User(params)]

    }

    def importData() {
        log("Import Data Pressed");

         def patients = StandardizedPatient.original.list();
         for (StandardizedPatient patient : patients ){

            log( ">>>>>>>>>>> " + patient.name + " " + patient.profession.profession + " " + patient.bankaccount.bankName);
            //log( ">>>>>>>>>>> " + patient.anamnesisForm.anamnesisChecksValues.size() );

         }



        redirect(action: "list", params: params)



    }


    def save() {

        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);


        if(passwordsMatch){


         def userInstance = new User(params)
         handleInboundPassword(userInstance);

                if (!userInstance.save(flush: true)) {
                    render(view: "create", model: [userInstance: userInstance])
                    return
                }
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
            redirect(action: "show", id: userInstance.id)
        }else{
            flash.message = message(code: 'default.password.message')
            redirect(action: "create")
        }


    }

    def show() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        handleOutboundPassword(userInstance);
        [userInstance: userInstance]
    }

    def edit() {


        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }



        [userInstance: userInstance]
    }

    def update() {


        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);
        def userInstance = User.get(params.id)
        if (!passwordsMatch){
            render(view: "edit", model: [userInstance: userInstance])
            return;
        }


        handleInboundPassword(params);



        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()



            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def clearData(){
        User.deleteAll(User.list());
        Role.deleteAll(Role.list());
        render "ok"
    }

    private void setupDefaultData(){
          //  if (Role.list().size() == 0){

                Role adminRole = new Role();
                adminRole.roleName = ADMIN_ROLE;
                adminRole.roleDescription = "Administrate Users";
                adminRole.save();

                Role userRole = new Role();
                userRole.roleName = USER_ROLE;
                userRole.roleDescription = "Normal Users";
                userRole.save();

                User admin = new User();
                User user1 = new User();

                log("1 " + admin);

                admin.userName = grailsApplication.config.sp_portal.admin.username;
                admin.userEmail = grailsApplication.config.sp_portal.admin.email;
                admin.passwordHash = hashPassword(""+grailsApplication.config.sp_portal.admin.password,admin.userName);
                admin.isActive = true;




                log("2 " + admin);
                log("2 " + admin.userEmail);

                def roles = [];
                roles.add(Role.findByRoleName(ADMIN_ROLE));


                admin.roles = roles;

                admin.save();


                // test data
                user1.userName = "user1";
                user1.userEmail = "a@b";
                user1.passwordHash = hashPassword("user1");
                user1.isActive = true;


                def roles2 = [];
                roles2.add(Role.findByRoleName(USER_ROLE));

                user1.roles = roles2;

                user1.save();

            // }
    }





    private boolean comparePasswords(String p1,String p2){
        log("Comparing passwords " + p1 + "  and " + p2  );
        if ( p1 != p2) {
             flash.message = message(code: 'default.password.match.message');
             log(" returning false");
             return false;
        }
        log(" returning true");
        return true;
    }



}
