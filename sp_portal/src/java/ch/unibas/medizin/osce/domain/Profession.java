package ch.unibas.medizin.osce.domain;


import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import ch.unibas.medizin.osce.domain.StandardizedPatient;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.TypedQuery;


public class Profession {

    @Size(max = 60)
    private String profession;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profession")
    private Set<StandardizedPatient> standardizedpatients = new HashSet<StandardizedPatient>();


}
