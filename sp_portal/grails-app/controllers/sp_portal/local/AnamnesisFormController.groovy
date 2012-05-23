package sp_portal.local

import org.springframework.dao.DataIntegrityViolationException

class AnamnesisFormController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [anamnesisFormInstanceList: AnamnesisForm.list(params), anamnesisFormInstanceTotal: AnamnesisForm.count()]
    }

    def create() {
        [anamnesisFormInstance: new AnamnesisForm(params)]
    }

    def save() {
        def anamnesisFormInstance = new AnamnesisForm(params)
        if (!anamnesisFormInstance.save(flush: true)) {
            render(view: "create", model: [anamnesisFormInstance: anamnesisFormInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), anamnesisFormInstance.id])
        redirect(action: "show", id: anamnesisFormInstance.id)
    }

    def show() {
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        [anamnesisFormInstance: anamnesisFormInstance]
    }

    def edit() {
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        [anamnesisFormInstance: anamnesisFormInstance]
    }

    def update() {
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (anamnesisFormInstance.version > version) {
                anamnesisFormInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'anamnesisForm.label', default: 'AnamnesisForm')] as Object[],
                          "Another user has updated this AnamnesisForm while you were editing")
                render(view: "edit", model: [anamnesisFormInstance: anamnesisFormInstance])
                return
            }
        }

        anamnesisFormInstance.properties = params

        if (!anamnesisFormInstance.save(flush: true)) {
            render(view: "edit", model: [anamnesisFormInstance: anamnesisFormInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), anamnesisFormInstance.id])
        redirect(action: "show", id: anamnesisFormInstance.id)
    }

    def delete() {
        def anamnesisFormInstance = AnamnesisForm.get(params.id)
        if (!anamnesisFormInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
            return
        }

        try {
            anamnesisFormInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'anamnesisForm.label', default: 'AnamnesisForm'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
