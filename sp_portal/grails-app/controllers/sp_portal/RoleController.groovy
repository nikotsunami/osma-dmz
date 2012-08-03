package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class RoleController{

	def beforeInterceptor = [action:this.&isLoggedInAsAdmin]    
    private static final log = LogFactory.getLog(this)
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    

    def index() {
		log.info("index of role")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of role")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [roleInstanceList: Role.list(params), roleInstanceTotal: Role.count()]
    }

    def create() {
		log.info("create role")
        [roleInstance: new Role(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class RoleController Method save() with params : "+params)
		}
        def roleInstance = new Role(params)
        if (!roleInstance.save(flush: true)) {
            render(view: "create", model: [roleInstance: roleInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), roleInstance.id])
        redirect(action: "show", id: roleInstance.id)
    }

    def show() {
		if(log.isTraceEnabled()){
			log.trace(">> In class RoleController Method show() with params : "+params)
		}
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "list")
            return
        }

        [roleInstance: roleInstance]
    }

    def edit() {
		if(log.isTraceEnabled()){
			log.trace(">> In class RoleController Method edit() with params : "+params)
		}
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "list")
            return
        }

        [roleInstance: roleInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class RoleController Method update() with params : "+params)
		}
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (roleInstance.version > version) {
                roleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'role.label', default: 'Role')] as Object[],
                          "Another user has updated this Role while you were editing")
                render(view: "edit", model: [roleInstance: roleInstance])
                return
            }
        }

        roleInstance.properties = params

        if (!roleInstance.save(flush: true)) {
            render(view: "edit", model: [roleInstance: roleInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), roleInstance.id])
        redirect(action: "show", id: roleInstance.id)
    }

    def delete() {
		if(log.isTraceEnabled()){
			log.trace(">> In class RoleController Method delete() with params : "+params)
		}
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "list")
            return
        }

        try {
            roleInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'role.label', default: 'Role'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
