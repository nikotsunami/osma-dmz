package sp_portal

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory;

class PersonalDetailsController  extends MainController {

	  def beforeInterceptor = [action:this.&isLoggedInAsUser]
	  private static final log = LogFactory.getLog(this)
      static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		log.info("index of personalDetails")
        redirect(action: "list", params: params)
    }

    def list() {
		log.info("list of personalDetails")
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [standardizedPatientInstanceList: StandardizedPatient.list(params), standardizedPatientInstanceTotal: StandardizedPatient.count()]
    }

    def create() {
		log.info("user create personalDetails")
        [standardizedPatientInstance: new local.StandardizedPatient(params)]
    }

    def save() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PersonalDetailsController Method save() with params : "+params)
		}
        def standardizedPatientInstance = new local.StandardizedPatient(params)
		if(log.isDebugEnabled()){
			if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n origId: ");
			sb.append(standardizedPatientInstance?.origId);
			sb.append( "\n birthday: ");
			sb.append(standardizedPatientInstance?.birthday);
			sb.append( "\n city: ");
			sb.append(standardizedPatientInstance?.city);
			sb.append( "\n email: ");
			sb.append(standardizedPatientInstance?.email);
			sb.append( "\n gender: ");
			sb.append(standardizedPatientInstance?.gender);
			sb.append( "\n height: ");
			sb.append(standardizedPatientInstance?.height);
			sb.append( "\n immagePath: ");
			sb.append(standardizedPatientInstance?.immagePath);
			sb.append( "\n maritalStatus: ");
			sb.append(standardizedPatientInstance?.maritalStatus);
			sb.append( "\n mobile: ");
			sb.append(standardizedPatientInstance?.mobile);
			sb.append( "\n name: ");
			sb.append(standardizedPatientInstance?.name);
			sb.append( "\n postalCode: ");
			sb.append(standardizedPatientInstance?.postalCode);
			sb.append( "\n preName: ");
			sb.append(standardizedPatientInstance?.preName);			
			sb.append( "\n socialInsuranceNo: ");
			sb.append(standardizedPatientInstance?.socialInsuranceNo);
			sb.append( "\n street ");
			sb.append(standardizedPatientInstance?.street);
			sb.append( "\n telephone: ");
			sb.append(standardizedPatientInstance?.telephone);
			sb.append( "\n telephone2: ");
			sb.append(standardizedPatientInstance?.telephone2);
			sb.append( "\n videoPath: ");
			sb.append(standardizedPatientInstance?.videoPath);
			sb.append( "\n weight: ");
			sb.append(standardizedPatientInstance?.weight);
			sb.append( "\n workPermission: ");
			sb.append(standardizedPatientInstance?.workPermission);
			sb.append( "\n anamnesisForm: ");
			sb.append(standardizedPatientInstance?.anamnesisForm);
			sb.append( "\n description: ");
			sb.append(standardizedPatientInstance?.description);
			sb.append( "\n profession: ");
			sb.append(standardizedPatientInstance?.profession);
			sb.append( "\n nationality: ");
			sb.append(standardizedPatientInstance?.nationality);
			sb.append( "\n bankaccount: ");
			sb.append(standardizedPatientInstance?.bankaccount);
			log.debug( "find standardizedPatientInstance : " + sb.toString());
		}
		}
        if (!standardizedPatientInstance.save(flush: true)) {
            render(view: "create", model: [standardizedPatientInstance: standardizedPatientInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), standardizedPatientInstance.id])
        redirect(action: "show", id: standardizedPatientInstance.id)
    }

    def show() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PersonalDetailsController Method show() with params : "+params)
		}
        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
		if(log.isDebugEnabled()){
			log.debug("find standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (standardizedPatientInstance){
            if (!standardizedPatientInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
                redirect(action: "list")
                return
            }
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def edit() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PersonalDetailsController Method edit() with params : "+params)
		}
        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
		if(log.isDebugEnabled()){
			log.debug("find standardizedPatientInstance : "+standardizedPatientInstance)
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        [standardizedPatientInstance: standardizedPatientInstance]
    }

    def update() {
		if(log.isTraceEnabled()){
			log.trace(">> In class PersonalDetailsController Method update() with params : "+params)
		}
        def standardizedPatientInstance = User.findById(session.user.id).standardizedPatient;
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n origId: ");
			sb.append(standardizedPatientInstance?.origId);
			sb.append( "\n birthday: ");
			sb.append(standardizedPatientInstance?.birthday);
			sb.append( "\n city: ");
			sb.append(standardizedPatientInstance?.city);
			sb.append( "\n email: ");
			sb.append(standardizedPatientInstance?.email);
			sb.append( "\n gender: ");
			sb.append(standardizedPatientInstance?.gender);
			sb.append( "\n height: ");
			sb.append(standardizedPatientInstance?.height);
			sb.append( "\n immagePath: ");
			sb.append(standardizedPatientInstance?.immagePath);
			sb.append( "\n maritalStatus: ");
			sb.append(standardizedPatientInstance?.maritalStatus);
			sb.append( "\n mobile: ");
			sb.append(standardizedPatientInstance?.mobile);
			sb.append( "\n name: ");
			sb.append(standardizedPatientInstance?.name);
			sb.append( "\n postalCode: ");
			sb.append(standardizedPatientInstance?.postalCode);
			sb.append( "\n preName: ");
			sb.append(standardizedPatientInstance?.preName);			
			sb.append( "\n socialInsuranceNo: ");
			sb.append(standardizedPatientInstance?.socialInsuranceNo);
			sb.append( "\n street ");
			sb.append(standardizedPatientInstance?.street);
			sb.append( "\n telephone: ");
			sb.append(standardizedPatientInstance?.telephone);
			sb.append( "\n telephone2: ");
			sb.append(standardizedPatientInstance?.telephone2);
			sb.append( "\n videoPath: ");
			sb.append(standardizedPatientInstance?.videoPath);
			sb.append( "\n weight: ");
			sb.append(standardizedPatientInstance?.weight);
			sb.append( "\n workPermission: ");
			sb.append(standardizedPatientInstance?.workPermission);
			sb.append( "\n anamnesisForm: ");
			sb.append(standardizedPatientInstance?.anamnesisForm);
			sb.append( "\n description: ");
			sb.append(standardizedPatientInstance?.description);
			sb.append( "\n profession: ");
			sb.append(standardizedPatientInstance?.profession);
			sb.append( "\n nationality: ");
			sb.append(standardizedPatientInstance?.nationality);
			sb.append( "\n bankaccount: ");
			sb.append(standardizedPatientInstance?.bankaccount);
			log.debug( "find standardizedPatientInstance : " + sb.toString());
		}
        if (!standardizedPatientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (standardizedPatientInstance.version > version) {
                standardizedPatientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'standardizedPatient.label', default: 'StandardizedPatient')] as Object[],
                          "Another user has updated this StandardizedPatient while you were editing")
                render(view: "edit", model: [standardizedPatientInstance: standardizedPatientInstance])
                return
            }
        }

        standardizedPatientInstance.properties = params
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			sb.append( "\n origId: ");
			sb.append(standardizedPatientInstance?.origId);
			sb.append( "\n birthday: ");
			sb.append(standardizedPatientInstance?.birthday);
			sb.append( "\n city: ");
			sb.append(standardizedPatientInstance?.city);
			sb.append( "\n email: ");
			sb.append(standardizedPatientInstance?.email);
			sb.append( "\n gender: ");
			sb.append(standardizedPatientInstance?.gender);
			sb.append( "\n height: ");
			sb.append(standardizedPatientInstance?.height);
			sb.append( "\n immagePath: ");
			sb.append(standardizedPatientInstance?.immagePath);
			sb.append( "\n maritalStatus: ");
			sb.append(standardizedPatientInstance?.maritalStatus);
			sb.append( "\n mobile: ");
			sb.append(standardizedPatientInstance?.mobile);
			sb.append( "\n name: ");
			sb.append(standardizedPatientInstance?.name);
			sb.append( "\n postalCode: ");
			sb.append(standardizedPatientInstance?.postalCode);
			sb.append( "\n preName: ");
			sb.append(standardizedPatientInstance?.preName);			
			sb.append( "\n socialInsuranceNo: ");
			sb.append(standardizedPatientInstance?.socialInsuranceNo);
			sb.append( "\n street ");
			sb.append(standardizedPatientInstance?.street);
			sb.append( "\n telephone: ");
			sb.append(standardizedPatientInstance?.telephone);
			sb.append( "\n telephone2: ");
			sb.append(standardizedPatientInstance?.telephone2);
			sb.append( "\n videoPath: ");
			sb.append(standardizedPatientInstance?.videoPath);
			sb.append( "\n weight: ");
			sb.append(standardizedPatientInstance?.weight);
			sb.append( "\n workPermission: ");
			sb.append(standardizedPatientInstance?.workPermission);
			sb.append( "\n anamnesisForm: ");
			sb.append(standardizedPatientInstance?.anamnesisForm);
			sb.append( "\n description: ");
			sb.append(standardizedPatientInstance?.description);
			sb.append( "\n profession: ");
			sb.append(standardizedPatientInstance?.profession);
			sb.append( "\n nationality: ");
			sb.append(standardizedPatientInstance?.nationality);
			sb.append( "\n bankaccount: ");
			sb.append(standardizedPatientInstance?.bankaccount);
			log.debug( "update standardizedPatientInstance : " + sb.toString());
		}
        if (!standardizedPatientInstance.save(flush: true)) {
            render(view: "edit", model: [standardizedPatientInstance: standardizedPatientInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'standardizedPatient.label', default: 'StandardizedPatient'), standardizedPatientInstance.id])
        redirect(action: "show", id: standardizedPatientInstance.id)
    }

}
