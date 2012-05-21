package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;


class MyAccountController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";


    def beforeInterceptor = [action:this.&isLoggedIn]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]



    def index() {
        redirect(action: "show", params: params)
    }




    def save() {
        handleInboundPassword(params);

        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show() {

        def userInstance = session.user

        handleOutboundPassword(userInstance);
        [userInstance: userInstance]
    }

    def edit() {


        def userInstance = session.user
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "show")
            return
        }



        [userInstance: userInstance]
    }

    def update() {
        def userInstance = session.user

        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);

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

        userInstance = User.get(session.user.id);
        session.user = userInstance;
        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
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