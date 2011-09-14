// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisCheck;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect AnamnesisCheckDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AnamnesisCheckDataOnDemand: @Component;
    
    private Random AnamnesisCheckDataOnDemand.rnd = new SecureRandom();
    
    private List<AnamnesisCheck> AnamnesisCheckDataOnDemand.data;
    
    public AnamnesisCheck AnamnesisCheckDataOnDemand.getNewTransientAnamnesisCheck(int index) {
        AnamnesisCheck obj = new AnamnesisCheck();
        setText(obj, index);
        return obj;
    }
    
    public void AnamnesisCheckDataOnDemand.setText(AnamnesisCheck obj, int index) {
        String text = "text_" + index;
        if (text.length() > 255) {
            text = text.substring(0, 255);
        }
        obj.setText(text);
    }
    
    public AnamnesisCheck AnamnesisCheckDataOnDemand.getSpecificAnamnesisCheck(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        AnamnesisCheck obj = data.get(index);
        return AnamnesisCheck.findAnamnesisCheck(obj.getId());
    }
    
    public AnamnesisCheck AnamnesisCheckDataOnDemand.getRandomAnamnesisCheck() {
        init();
        AnamnesisCheck obj = data.get(rnd.nextInt(data.size()));
        return AnamnesisCheck.findAnamnesisCheck(obj.getId());
    }
    
    public boolean AnamnesisCheckDataOnDemand.modifyAnamnesisCheck(AnamnesisCheck obj) {
        return false;
    }
    
    public void AnamnesisCheckDataOnDemand.init() {
        data = AnamnesisCheck.findAnamnesisCheckEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'AnamnesisCheck' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.AnamnesisCheck>();
        for (int i = 0; i < 10; i++) {
            AnamnesisCheck obj = getNewTransientAnamnesisCheck(i);
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
