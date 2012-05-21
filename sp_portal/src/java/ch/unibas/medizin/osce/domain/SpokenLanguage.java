package ch.unibas.medizin.osce.domain;


import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import ch.unibas.medizin.osce.domain.LangSkill;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.TypedQuery;


public class SpokenLanguage {

    @Size(max = 40)
    private String languageName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spokenlanguage")
    private Set<LangSkill> langskills = new HashSet<LangSkill>();


}
