package ch.unibas.medizin.osce.domain;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import ch.unibas.medizin.osce.domain.AnamnesisForm;
import ch.unibas.medizin.osce.shared.TraitTypes;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.TypedQuery;


public class Scar {

    @Size(max = 60)
    private String bodypart;

    @Enumerated
    @NotNull
    private TraitTypes traitType;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "scars")
    private Set<AnamnesisForm> anamnesisForms = new HashSet<AnamnesisForm>();


}
