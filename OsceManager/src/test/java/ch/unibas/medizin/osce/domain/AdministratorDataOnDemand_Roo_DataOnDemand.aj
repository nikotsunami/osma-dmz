// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Administrator;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect AdministratorDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AdministratorDataOnDemand: @Component;
    
    private Random AdministratorDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Administrator> AdministratorDataOnDemand.data;
    
    public Administrator AdministratorDataOnDemand.getNewTransientAdministrator(int index) {
        ch.unibas.medizin.osce.domain.Administrator obj = new ch.unibas.medizin.osce.domain.Administrator();
        setEmail(obj, index);
        setName(obj, index);
        setPreName(obj, index);
        return obj;
    }
    
    private void AdministratorDataOnDemand.setEmail(Administrator obj, int index) {
        java.lang.String email = "email_" + index;
        if (email.length() > 40) {
            email = email.substring(0, 40);
        }
        obj.setEmail(email);
    }
    
    private void AdministratorDataOnDemand.setName(Administrator obj, int index) {
        java.lang.String name = "name_" + index;
        if (name.length() > 40) {
            name = name.substring(0, 40);
        }
        obj.setName(name);
    }
    
    private void AdministratorDataOnDemand.setPreName(Administrator obj, int index) {
        java.lang.String preName = "preName_" + index;
        if (preName.length() > 40) {
            preName = preName.substring(0, 40);
        }
        obj.setPreName(preName);
    }
    
    public Administrator AdministratorDataOnDemand.getSpecificAdministrator(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Administrator obj = data.get(index);
        return Administrator.findAdministrator(obj.getId());
    }
    
    public Administrator AdministratorDataOnDemand.getRandomAdministrator() {
        init();
        Administrator obj = data.get(rnd.nextInt(data.size()));
        return Administrator.findAdministrator(obj.getId());
    }
    
    public boolean AdministratorDataOnDemand.modifyAdministrator(Administrator obj) {
        return false;
    }
    
    public void AdministratorDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.Administrator.findAdministratorEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Administrator' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.Administrator>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.Administrator obj = getNewTransientAdministrator(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
