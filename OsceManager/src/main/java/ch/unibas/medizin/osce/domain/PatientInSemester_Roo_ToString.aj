// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect PatientInSemester_Roo_ToString {
    
    public String PatientInSemester.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Semester: ").append(getSemester()).append(", ");
        sb.append("StandardizedPatient: ").append(getStandardizedPatient()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
