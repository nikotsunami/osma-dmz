// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisForm;
import ch.unibas.medizin.osce.domain.AnamnesisFormDataOnDemand;
import ch.unibas.medizin.osce.domain.Bankaccount;
import ch.unibas.medizin.osce.domain.BankaccountDataOnDemand;
import ch.unibas.medizin.osce.domain.Description;
import ch.unibas.medizin.osce.domain.DescriptionDataOnDemand;
import ch.unibas.medizin.osce.domain.Nationality;
import ch.unibas.medizin.osce.domain.NationalityDataOnDemand;
import ch.unibas.medizin.osce.domain.Profession;
import ch.unibas.medizin.osce.domain.ProfessionDataOnDemand;
import ch.unibas.medizin.osce.domain.StandardizedPatient;
import ch.unibas.medizin.osce.shared.Gender;
import java.lang.Integer;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect StandardizedPatientDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StandardizedPatientDataOnDemand: @Component;
    
    private Random StandardizedPatientDataOnDemand.rnd = new SecureRandom();
    
    private List<StandardizedPatient> StandardizedPatientDataOnDemand.data;
    
    @Autowired
    private AnamnesisFormDataOnDemand StandardizedPatientDataOnDemand.anamnesisFormDataOnDemand;
    
    @Autowired
    private BankaccountDataOnDemand StandardizedPatientDataOnDemand.bankaccountDataOnDemand;
    
    @Autowired
    private DescriptionDataOnDemand StandardizedPatientDataOnDemand.descriptionDataOnDemand;
    
    @Autowired
    private NationalityDataOnDemand StandardizedPatientDataOnDemand.nationalityDataOnDemand;
    
    @Autowired
    private ProfessionDataOnDemand StandardizedPatientDataOnDemand.professionDataOnDemand;
    
    public StandardizedPatient StandardizedPatientDataOnDemand.getNewTransientStandardizedPatient(int index) {
        StandardizedPatient obj = new StandardizedPatient();
        setAnamnesisForm(obj, index);
        setBankAccount(obj, index);
        setBirthday(obj, index);
        setCity(obj, index);
        setDescriptions(obj, index);
        setEmail(obj, index);
        setGender(obj, index);
        setMobile(obj, index);
        setName(obj, index);
        setNationality(obj, index);
        setPostalCode(obj, index);
        setPreName(obj, index);
        setProfession(obj, index);
        setStreet(obj, index);
        setTelephone(obj, index);
        return obj;
    }
    
    public void StandardizedPatientDataOnDemand.setAnamnesisForm(StandardizedPatient obj, int index) {
        AnamnesisForm anamnesisForm = anamnesisFormDataOnDemand.getSpecificAnamnesisForm(index);
        obj.setAnamnesisForm(anamnesisForm);
    }
    
    public void StandardizedPatientDataOnDemand.setBankAccount(StandardizedPatient obj, int index) {
        Bankaccount bankAccount = bankaccountDataOnDemand.getSpecificBankaccount(index);
        obj.setBankAccount(bankAccount);
    }
    
    public void StandardizedPatientDataOnDemand.setBirthday(StandardizedPatient obj, int index) {
        Date birthday = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setBirthday(birthday);
    }
    
    public void StandardizedPatientDataOnDemand.setCity(StandardizedPatient obj, int index) {
        String city = "city_" + index;
        if (city.length() > 30) {
            city = city.substring(0, 30);
        }
        obj.setCity(city);
    }
    
    public void StandardizedPatientDataOnDemand.setDescriptions(StandardizedPatient obj, int index) {
        Description descriptions = descriptionDataOnDemand.getSpecificDescription(index);
        obj.setDescriptions(descriptions);
    }
    
    public void StandardizedPatientDataOnDemand.setEmail(StandardizedPatient obj, int index) {
        String email = "email_" + index;
        if (email.length() > 40) {
            email = email.substring(0, 40);
        }
        obj.setEmail(email);
    }
    
    public void StandardizedPatientDataOnDemand.setGender(StandardizedPatient obj, int index) {
        Gender gender = Gender.class.getEnumConstants()[0];
        obj.setGender(gender);
    }
    
    public void StandardizedPatientDataOnDemand.setMobile(StandardizedPatient obj, int index) {
        String mobile = "mobile_" + index;
        if (mobile.length() > 30) {
            mobile = mobile.substring(0, 30);
        }
        obj.setMobile(mobile);
    }
    
    public void StandardizedPatientDataOnDemand.setName(StandardizedPatient obj, int index) {
        String name = "name_" + index;
        if (name.length() > 40) {
            name = name.substring(0, 40);
        }
        obj.setName(name);
    }
    
    public void StandardizedPatientDataOnDemand.setNationality(StandardizedPatient obj, int index) {
        Nationality nationality = nationalityDataOnDemand.getRandomNationality();
        obj.setNationality(nationality);
    }
    
    public void StandardizedPatientDataOnDemand.setPostalCode(StandardizedPatient obj, int index) {
        Integer postalCode = new Integer(index);
        obj.setPostalCode(postalCode);
    }
    
    public void StandardizedPatientDataOnDemand.setPreName(StandardizedPatient obj, int index) {
        String preName = "preName_" + index;
        if (preName.length() > 40) {
            preName = preName.substring(0, 40);
        }
        obj.setPreName(preName);
    }
    
    public void StandardizedPatientDataOnDemand.setProfession(StandardizedPatient obj, int index) {
        Profession profession = professionDataOnDemand.getRandomProfession();
        obj.setProfession(profession);
    }
    
    public void StandardizedPatientDataOnDemand.setStreet(StandardizedPatient obj, int index) {
        String street = "street_" + index;
        if (street.length() > 60) {
            street = street.substring(0, 60);
        }
        obj.setStreet(street);
    }
    
    public void StandardizedPatientDataOnDemand.setTelephone(StandardizedPatient obj, int index) {
        String telephone = "telephone_" + index;
        if (telephone.length() > 30) {
            telephone = telephone.substring(0, 30);
        }
        obj.setTelephone(telephone);
    }
    
    public StandardizedPatient StandardizedPatientDataOnDemand.getSpecificStandardizedPatient(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        StandardizedPatient obj = data.get(index);
        return StandardizedPatient.findStandardizedPatient(obj.getId());
    }
    
    public StandardizedPatient StandardizedPatientDataOnDemand.getRandomStandardizedPatient() {
        init();
        StandardizedPatient obj = data.get(rnd.nextInt(data.size()));
        return StandardizedPatient.findStandardizedPatient(obj.getId());
    }
    
    public boolean StandardizedPatientDataOnDemand.modifyStandardizedPatient(StandardizedPatient obj) {
        return false;
    }
    
    public void StandardizedPatientDataOnDemand.init() {
        data = StandardizedPatient.findStandardizedPatientEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'StandardizedPatient' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.StandardizedPatient>();
        for (int i = 0; i < 10; i++) {
            StandardizedPatient obj = getNewTransientStandardizedPatient(i);
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
