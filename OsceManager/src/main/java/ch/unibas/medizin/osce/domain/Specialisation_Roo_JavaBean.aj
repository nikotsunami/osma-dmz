// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Doctor;
import ch.unibas.medizin.osce.domain.RoleTopic;
import java.lang.String;
import java.util.Set;

privileged aspect Specialisation_Roo_JavaBean {
    
    public String Specialisation.getName() {
        return this.name;
    }
    
    public void Specialisation.setName(String name) {
        this.name = name;
    }
    
    public Set<RoleTopic> Specialisation.getRoleTopics() {
        return this.roleTopics;
    }
    
    public void Specialisation.setRoleTopics(Set<RoleTopic> roleTopics) {
        this.roleTopics = roleTopics;
    }
    
    public java.util.Set<Doctor> Specialisation.getDoctors() {
        return this.doctors;
    }
    
    public void Specialisation.setDoctors(java.util.Set<Doctor> doctors) {
        this.doctors = doctors;
    }
    
}
