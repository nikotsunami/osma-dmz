package ch.unibas.medizin.osce.domain;

import java.util.List;

import javax.validation.constraints.Size;
import ch.unibas.medizin.osce.domain.AnamnesisForm;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import ch.unibas.medizin.osce.domain.AnamnesisCheck;

public class AnamnesisChecksValue {

    private Boolean truth;

    @Size(max = 255)
    private String comment;

    @Size(max = 255)
    private String anamnesisChecksValue;

    @ManyToOne
    private AnamnesisForm anamnesisform;

    @ManyToOne
    private AnamnesisCheck anamnesischeck;

}
