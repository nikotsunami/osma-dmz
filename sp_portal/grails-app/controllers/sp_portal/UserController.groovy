package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;


class UserController {

    public static String ADMIN_ROLE = "ADMIN_ROLE";
    public static String USER_ROLE = "USER_ROLE";

    static {
         ADMIN_ROLE = "ADMIN_ROLE";
         USER_ROLE = "USER_ROLE";
    }


    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

// Authentication Methods

    def login(){

    }

    def authenticate(){
        def user = User.findByUserNameAndPasswordHash(params.userName, hashPassword(params.passwordHash,params.userName))
        if(user){
          session.user = user
          flash.message = "Hello ${user.userName}!"
          applyPermissions(user);
        }else{
          flash.message = "Sorry, ${params.userName}. Please try again."
          redirect(action:"login")
        }
      }

     def logout() {
        flash.message = "Goodbye ${session.user.name}"
        session.user = null
        redirect(controller:"entry", action:"list")
     }


    private String hashPassword(String unhashedPW, String userName){
        return "hashed" + unhashedPW;
    }





    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
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
        handleInboundPassword(params);

        def userInstance = User.get(params.id)
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


    private void applyPermissions(User user){

        log(">>>>>>>>>>>>>>>>>" + ADMIN_ROLE)
        def result = user.roles.findAll{ role -> role.roleName.contains(ADMIN_ROLE) }
        if ( result.size() > 0 ){
            redirect(action: "list")
        } else {
            redirect(controller:"entry", action:"list")
        }
    }


    private void handleInboundPassword(params){


        log("In handleInboundPassword  " + params);

        if (params.passwordHash) {
            params.passwordHash = hashPassword(params.passwordHash,params.userName);
        } else {
            def userInstance = User.get(params.id);
            if (userInstance){
                params.passwordHash = userInstance.passwordHash;
            }
        }

        log("Leaving handleInboundPassword  " + params);

    }


    private void handleOutboundPassword(user){
        user.passwordHash = null;
    }

    private void log(String msg){
        println(msg);
    }

}
