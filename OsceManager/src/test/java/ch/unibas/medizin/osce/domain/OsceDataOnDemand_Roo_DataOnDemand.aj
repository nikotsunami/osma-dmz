// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Osce;
import ch.unibas.medizin.osce.domain.Semester;
import ch.unibas.medizin.osce.domain.SemesterDataOnDemand;
import ch.unibas.medizin.osce.shared.StudyYears;
import java.lang.Boolean;
import java.lang.Integer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect OsceDataOnDemand_Roo_DataOnDemand {
    
    declare @type: OsceDataOnDemand: @Component;
    
    private Random OsceDataOnDemand.rnd = new SecureRandom();
    
    private List<Osce> OsceDataOnDemand.data;
    
    @Autowired
    private SemesterDataOnDemand OsceDataOnDemand.semesterDataOnDemand;
    
    public Osce OsceDataOnDemand.getNewTransientOsce(int index) {
        Osce obj = new Osce();
        setIsRepeOsce(obj, index);
        setMaxNumberStudents(obj, index);
        setNumberCourses(obj, index);
        setNumberPosts(obj, index);
        setPostLength(obj, index);
        setSemester(obj, index);
        setStudyYear(obj, index);
        return obj;
    }
    
    public void OsceDataOnDemand.setIsRepeOsce(Osce obj, int index) {
        Boolean isRepeOsce = Boolean.TRUE;
        obj.setIsRepeOsce(isRepeOsce);
    }
    
    public void OsceDataOnDemand.setMaxNumberStudents(Osce obj, int index) {
        Integer maxNumberStudents = new Integer(index);
        obj.setMaxNumberStudents(maxNumberStudents);
    }
    
    public void OsceDataOnDemand.setNumberCourses(Osce obj, int index) {
        Integer numberCourses = new Integer(index);
        obj.setNumberCourses(numberCourses);
    }
    
    public void OsceDataOnDemand.setNumberPosts(Osce obj, int index) {
        Integer numberPosts = new Integer(index);
        obj.setNumberPosts(numberPosts);
    }
    
    public void OsceDataOnDemand.setPostLength(Osce obj, int index) {
        Integer postLength = new Integer(index);
        obj.setPostLength(postLength);
    }
    
    public void OsceDataOnDemand.setSemester(Osce obj, int index) {
        Semester semester = semesterDataOnDemand.getRandomSemester();
        obj.setSemester(semester);
    }
    
    public void OsceDataOnDemand.setStudyYear(Osce obj, int index) {
        StudyYears studyYear = StudyYears.class.getEnumConstants()[0];
        obj.setStudyYear(studyYear);
    }
    
    public Osce OsceDataOnDemand.getSpecificOsce(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Osce obj = data.get(index);
        return Osce.findOsce(obj.getId());
    }
    
    public Osce OsceDataOnDemand.getRandomOsce() {
        init();
        Osce obj = data.get(rnd.nextInt(data.size()));
        return Osce.findOsce(obj.getId());
    }
    
    public boolean OsceDataOnDemand.modifyOsce(Osce obj) {
        return false;
    }
    
    public void OsceDataOnDemand.init() {
        data = Osce.findOsceEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Osce' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.Osce>();
        for (int i = 0; i < 10; i++) {
            Osce obj = getNewTransientOsce(i);
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
