package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;


class MyAccountController extends MainController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";

    def beforeInterceptor = [action:this.&isLoggedInAsUser]

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }

    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        log.info("index of myAccount")
        redirect(action: "show", params: params)
    }

    def save() {
        if(log.isTraceEnabled()){
            log.trace(">> In class MyAccountController Method save() with params : "+params)
        }
        handleInboundPassword(params);

        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label.user', default: 'Benutzer'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show() {
        log.info("user show myAccount")
        def user = User.findById(session.user.id)
        if(log.isDebugEnabled()){
            log.debug("find user : "+user)
        }
        def userInstance = user

        handleOutboundPassword(userInstance);

        [userInstance: userInstance]

    }

    def edit() {
        log.info("user edit myAccount")
        def user = User.findById(session.user.id)
        if(log.isDebugEnabled()){
            log.debug("find user : "+user)
        }
        def userInstance = user
        
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label.user', default: 'Benutzer'), params.id])
            redirect(action: "show")

        }
        
        [userInstance: userInstance]
    }

    def update() {
        if(log.isTraceEnabled()){
            log.trace(">> In class MyAccountController Method update() with params : "+params)
        }
        def user = User.findById(session.user.id)
        if(log.isDebugEnabled()){
            log.debug("find user : "+user)
        }
        def userInstance = user
        boolean passwordsMatch = comparePasswords(params.confirmPassword,params.passwordHash);

        if (!passwordsMatch){
            render(view: "edit", model: [userInstance: userInstance])
            return;
        }

        handleInboundPassword(params);

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label.user', default: 'Benutzer'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()

            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label.user', default: 'Benutzer')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance = User.get(user.id);
        user = userInstance;
        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label.user', default: 'Benutzer'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    private boolean comparePasswords(String p1,String p2){
        if(log.isTraceEnabled()){
            log.trace(">> In class MyAccountController Method comparePasswords() Comparing passwords " + p1 + "  and " + p2  )
        }
        if ( p1 != p2) {
             flash.message = message(code: 'default.password.match.message');
             log.info("Comparing passwords returning false");
             return false;
        }
        log.info("Comparing passwords returning true");
        return true;
    }




}