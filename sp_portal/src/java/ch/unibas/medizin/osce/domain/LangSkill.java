package ch.unibas.medizin.osce.domain;

import java.util.List;


import javax.validation.constraints.NotNull;

import ch.unibas.medizin.osce.domain.StandardizedPatient;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import ch.unibas.medizin.osce.domain.SpokenLanguage;
import ch.unibas.medizin.osce.shared.LangSkillLevel;


public class LangSkill {

    @Enumerated
    @NotNull
    private LangSkillLevel skill;

    @ManyToOne
    private StandardizedPatient standardizedpatient;

    @ManyToOne
    private SpokenLanguage spokenlanguage;


}
