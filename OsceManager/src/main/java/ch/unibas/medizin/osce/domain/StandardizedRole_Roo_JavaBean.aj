// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Doctor;
import ch.unibas.medizin.osce.domain.RoleTopic;
import java.lang.String;

privileged aspect StandardizedRole_Roo_JavaBean {
    
    public String StandardizedRole.getShortName() {
        return this.shortName;
    }
    
    public void StandardizedRole.setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String StandardizedRole.getLongName() {
        return this.longName;
    }
    
    public void StandardizedRole.setLongName(String longName) {
        this.longName = longName;
    }
    
    public String StandardizedRole.getCaseDescription() {
        return this.caseDescription;
    }
    
    public void StandardizedRole.setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }
    
    public String StandardizedRole.getRoleScript() {
        return this.roleScript;
    }
    
    public void StandardizedRole.setRoleScript(String roleScript) {
        this.roleScript = roleScript;
    }
    
    public String StandardizedRole.getRoleType() {
        return this.roleType;
    }
    
    public void StandardizedRole.setRoleType(String roleType) {
        this.roleType = roleType;
    }
    
    public RoleTopic StandardizedRole.getRoleTopic() {
        return this.roleTopic;
    }
    
    public void StandardizedRole.setRoleTopic(RoleTopic roleTopic) {
        this.roleTopic = roleTopic;
    }
    
    public Doctor StandardizedRole.getAuthor() {
        return this.author;
    }
    
    public void StandardizedRole.setAuthor(Doctor author) {
        this.author = author;
    }
    
    public Doctor StandardizedRole.getReviewer() {
        return this.reviewer;
    }
    
    public void StandardizedRole.setReviewer(Doctor reviewer) {
        this.reviewer = reviewer;
    }
    
}
