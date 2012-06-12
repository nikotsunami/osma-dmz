// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.CourseDataOnDemand;
import ch.unibas.medizin.osce.domain.OscePostDataOnDemand;
import ch.unibas.medizin.osce.domain.OscePostRoom;
import ch.unibas.medizin.osce.domain.RoomDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect OscePostRoomDataOnDemand_Roo_DataOnDemand {
    
    declare @type: OscePostRoomDataOnDemand: @Component;
    
    private Random OscePostRoomDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<OscePostRoom> OscePostRoomDataOnDemand.data;
    
    @Autowired
    private RoomDataOnDemand OscePostRoomDataOnDemand.roomDataOnDemand;
    
    @Autowired
    private OscePostDataOnDemand OscePostRoomDataOnDemand.oscePostDataOnDemand;
    
    @Autowired
    private CourseDataOnDemand OscePostRoomDataOnDemand.courseDataOnDemand;
    
    public OscePostRoom OscePostRoomDataOnDemand.getNewTransientOscePostRoom(int index) {
        ch.unibas.medizin.osce.domain.OscePostRoom obj = new ch.unibas.medizin.osce.domain.OscePostRoom();
        setRoom(obj, index);
        setOscePost(obj, index);
        setCourse(obj, index);
        return obj;
    }
    
    private void OscePostRoomDataOnDemand.setRoom(OscePostRoom obj, int index) {
        ch.unibas.medizin.osce.domain.Room room = roomDataOnDemand.getRandomRoom();
        obj.setRoom(room);
    }
    
    private void OscePostRoomDataOnDemand.setOscePost(OscePostRoom obj, int index) {
        ch.unibas.medizin.osce.domain.OscePost oscePost = oscePostDataOnDemand.getRandomOscePost();
        obj.setOscePost(oscePost);
    }
    
    private void OscePostRoomDataOnDemand.setCourse(OscePostRoom obj, int index) {
        ch.unibas.medizin.osce.domain.Course course = courseDataOnDemand.getRandomCourse();
        obj.setCourse(course);
    }
    
    public OscePostRoom OscePostRoomDataOnDemand.getSpecificOscePostRoom(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        OscePostRoom obj = data.get(index);
        return OscePostRoom.findOscePostRoom(obj.getId());
    }
    
    public OscePostRoom OscePostRoomDataOnDemand.getRandomOscePostRoom() {
        init();
        OscePostRoom obj = data.get(rnd.nextInt(data.size()));
        return OscePostRoom.findOscePostRoom(obj.getId());
    }
    
    public boolean OscePostRoomDataOnDemand.modifyOscePostRoom(OscePostRoom obj) {
        return false;
    }
    
    public void OscePostRoomDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.OscePostRoom.findOscePostRoomEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'OscePostRoom' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.OscePostRoom>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.OscePostRoom obj = getNewTransientOscePostRoom(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
