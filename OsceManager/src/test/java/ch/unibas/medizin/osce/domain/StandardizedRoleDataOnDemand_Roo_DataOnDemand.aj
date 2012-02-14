// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.RoleTopic;
import ch.unibas.medizin.osce.domain.RoleTopicDataOnDemand;
import ch.unibas.medizin.osce.domain.StandardizedRole;
import ch.unibas.medizin.osce.shared.StudyYears;
import java.lang.Integer;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect StandardizedRoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StandardizedRoleDataOnDemand: @Component;
    
    private Random StandardizedRoleDataOnDemand.rnd = new SecureRandom();
    
    private List<StandardizedRole> StandardizedRoleDataOnDemand.data;
    
    @Autowired
    private RoleTopicDataOnDemand StandardizedRoleDataOnDemand.roleTopicDataOnDemand;
    
    public StandardizedRole StandardizedRoleDataOnDemand.getNewTransientStandardizedRole(int index) {
        StandardizedRole obj = new StandardizedRole();
        setCaseDescription(obj, index);
        setLongName(obj, index);
        setMainVersion(obj, index);
        setPreviousVersion(obj, index);
        setRoleScript(obj, index);
        setRoleTopic(obj, index);
        setRoleType(obj, index);
        setShortName(obj, index);
        setStudyYear(obj, index);
        setSubVersion(obj, index);
        return obj;
    }
    
    public void StandardizedRoleDataOnDemand.setCaseDescription(StandardizedRole obj, int index) {
        String caseDescription = "caseDescription_" + index;
        if (caseDescription.length() > 999) {
            caseDescription = caseDescription.substring(0, 999);
        }
        obj.setCaseDescription(caseDescription);
    }
    
    public void StandardizedRoleDataOnDemand.setLongName(StandardizedRole obj, int index) {
        String longName = "longName_" + index;
        if (longName.length() > 100) {
            longName = longName.substring(0, 100);
        }
        obj.setLongName(longName);
    }
    
    public void StandardizedRoleDataOnDemand.setMainVersion(StandardizedRole obj, int index) {
        Integer mainVersion = new Integer(index);
        obj.setMainVersion(mainVersion);
    }
    
    public void StandardizedRoleDataOnDemand.setPreviousVersion(StandardizedRole obj, int index) {
        StandardizedRole previousVersion = obj;
        obj.setPreviousVersion(previousVersion);
    }
    
    public void StandardizedRoleDataOnDemand.setRoleScript(StandardizedRole obj, int index) {
        String roleScript = "roleScript_" + index;
        if (roleScript.length() > 255) {
            roleScript = roleScript.substring(0, 255);
        }
        obj.setRoleScript(roleScript);
    }
    
    public void StandardizedRoleDataOnDemand.setRoleTopic(StandardizedRole obj, int index) {
        RoleTopic roleTopic = roleTopicDataOnDemand.getRandomRoleTopic();
        obj.setRoleTopic(roleTopic);
    }
    
    public void StandardizedRoleDataOnDemand.setRoleType(StandardizedRole obj, int index) {
        String roleType = "roleType_" + index;
        if (roleType.length() > 10) {
            roleType = roleType.substring(0, 10);
        }
        obj.setRoleType(roleType);
    }
    
    public void StandardizedRoleDataOnDemand.setShortName(StandardizedRole obj, int index) {
        String shortName = "shortName_" + index;
        if (shortName.length() > 20) {
            shortName = shortName.substring(0, 20);
        }
        obj.setShortName(shortName);
    }
    
    public void StandardizedRoleDataOnDemand.setStudyYear(StandardizedRole obj, int index) {
        StudyYears studyYear = StudyYears.class.getEnumConstants()[0];
        obj.setStudyYear(studyYear);
    }
    
    public void StandardizedRoleDataOnDemand.setSubVersion(StandardizedRole obj, int index) {
        Integer subVersion = new Integer(index);
        obj.setSubVersion(subVersion);
    }
    
    public StandardizedRole StandardizedRoleDataOnDemand.getSpecificStandardizedRole(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        StandardizedRole obj = data.get(index);
        return StandardizedRole.findStandardizedRole(obj.getId());
    }
    
    public StandardizedRole StandardizedRoleDataOnDemand.getRandomStandardizedRole() {
        init();
        StandardizedRole obj = data.get(rnd.nextInt(data.size()));
        return StandardizedRole.findStandardizedRole(obj.getId());
    }
    
    public boolean StandardizedRoleDataOnDemand.modifyStandardizedRole(StandardizedRole obj) {
        return false;
    }
    
    public void StandardizedRoleDataOnDemand.init() {
        data = StandardizedRole.findStandardizedRoleEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StandardizedRole' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.StandardizedRole>();
        for (int i = 0; i < 10; i++) {
            StandardizedRole obj = getNewTransientStandardizedRole(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
