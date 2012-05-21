package ch.unibas.medizin.osce.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.util.StringUtils;



import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;
import ch.unibas.medizin.osce.shared.Comparison;
import ch.unibas.medizin.osce.shared.Gender;
import ch.unibas.medizin.osce.shared.LangSkillLevel;
import ch.unibas.medizin.osce.shared.MaritalStatus;
import ch.unibas.medizin.osce.shared.PossibleFields;
import ch.unibas.medizin.osce.shared.Sorting;
import ch.unibas.medizin.osce.shared.StandardizedPatientSearchField;
import ch.unibas.medizin.osce.shared.WorkPermission;



class StandardizedPatient {

    public StandardizedPatient(){
    }

    public Integer version;

    @Enumerated
    public Gender gender;

    @Size(max = 40)
    public String name;

    @Size(max = 40)
    public String preName;

    @Size(max = 60)
    public String street;

    @Size(max = 30)
    public String city;

    public Integer postalCode;

    @Size(max = 30)
    public String telephone;

    @Size(max = 30)
    public String telephone2;

    @Size(max = 30)
    public String mobile;

    public Integer height;

    public Integer weight;

    @Size(max = 255)
    public String immagePath;

    @Size(max = 255)
    public String videoPath;

    @ManyToOne
    public Nationality nationality;

    @ManyToOne
    public Profession profession;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    public Date birthday;

    @Size(max = 40)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")
    public String email;

    @OneToOne(cascade = CascadeType.ALL)
    public Description descriptions;

    @OneToOne(cascade = CascadeType.ALL)
    public Bankaccount bankAccount;

    @Enumerated
    public MaritalStatus maritalStatus;

    @Enumerated
    public WorkPermission workPermission;

    @Size(max = 13)
    @Pattern(regexp = "^[0-9]{13,13}$")
    public String socialInsuranceNo;

    @OneToOne(cascade = CascadeType.ALL)
    public AnamnesisForm anamnesisForm;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "standardizedpatient")
    public Set<LangSkill> langskills = new HashSet<LangSkill>();

}
