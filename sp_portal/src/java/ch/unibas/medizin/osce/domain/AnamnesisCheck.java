package ch.unibas.medizin.osce.domain;

import org.apache.log4j.Logger;
import javax.validation.constraints.Size;


import ch.unibas.medizin.osce.domain.AnamnesisChecksValue;
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.TypedQuery;
import javax.persistence.ManyToOne;


public class AnamnesisCheck {

    @Size(max = 255)
    private String text;

    @Size(max = 255)
    private String value;

    private Integer sort_order;

    @Enumerated
    private AnamnesisCheckTypes type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anamnesischeck")
    private Set<AnamnesisChecksValue> anamnesischecksvalues = new HashSet<AnamnesisChecksValue>();

    @ManyToOne
    private ch.unibas.medizin.osce.domain.AnamnesisCheck title;


    private Integer userSpecifiedOrder;
}
