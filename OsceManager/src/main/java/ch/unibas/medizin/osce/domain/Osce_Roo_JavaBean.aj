// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Course;
import ch.unibas.medizin.osce.domain.OsceDay;
import ch.unibas.medizin.osce.domain.Semester;
import ch.unibas.medizin.osce.domain.Student;
import ch.unibas.medizin.osce.shared.StudyYears;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.Set;

privileged aspect Osce_Roo_JavaBean {
    
    public StudyYears Osce.getStudyYear() {
        return this.studyYear;
    }
    
    public void Osce.setStudyYear(StudyYears studyYear) {
        this.studyYear = studyYear;
    }
    
    public Integer Osce.getMaxNumberStudents() {
        return this.maxNumberStudents;
    }
    
    public void Osce.setMaxNumberStudents(Integer maxNumberStudents) {
        this.maxNumberStudents = maxNumberStudents;
    }
    
    public Integer Osce.getNumberPosts() {
        return this.numberPosts;
    }
    
    public void Osce.setNumberPosts(Integer numberPosts) {
        this.numberPosts = numberPosts;
    }
    
    public Integer Osce.getNumberCourses() {
        return this.numberCourses;
    }
    
    public void Osce.setNumberCourses(Integer numberCourses) {
        this.numberCourses = numberCourses;
    }
    
    public Integer Osce.getPostLength() {
        return this.postLength;
    }
    
    public void Osce.setPostLength(Integer postLength) {
        this.postLength = postLength;
    }
    
    public Boolean Osce.getIsRepeOsce() {
        return this.isRepeOsce;
    }
    
    public void Osce.setIsRepeOsce(Boolean isRepeOsce) {
        this.isRepeOsce = isRepeOsce;
    }
    
    public Semester Osce.getSemester() {
        return this.semester;
    }
    
    public void Osce.setSemester(Semester semester) {
        this.semester = semester;
    }
    
    public Set<OsceDay> Osce.getOsce_days() {
        return this.osce_days;
    }
    
    public void Osce.setOsce_days(Set<OsceDay> osce_days) {
        this.osce_days = osce_days;
    }
    
    public Set<Course> Osce.getCourses() {
        return this.courses;
    }
    
    public void Osce.setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    
    public Set<Student> Osce.getStudents() {
        return this.students;
    }
    
    public void Osce.setStudents(Set<Student> students) {
        this.students = students;
    }
    
}
