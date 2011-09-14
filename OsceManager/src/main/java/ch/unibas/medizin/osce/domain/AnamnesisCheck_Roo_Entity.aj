// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisCheck;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AnamnesisCheck_Roo_Entity {
    
    declare @type: AnamnesisCheck: @Entity;
    
    @PersistenceContext
    transient EntityManager AnamnesisCheck.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AnamnesisCheck.id;
    
    @Version
    @Column(name = "version")
    private Integer AnamnesisCheck.version;
    
    public Long AnamnesisCheck.getId() {
        return this.id;
    }
    
    public void AnamnesisCheck.setId(Long id) {
        this.id = id;
    }
    
    public Integer AnamnesisCheck.getVersion() {
        return this.version;
    }
    
    public void AnamnesisCheck.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void AnamnesisCheck.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void AnamnesisCheck.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AnamnesisCheck attached = AnamnesisCheck.findAnamnesisCheck(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void AnamnesisCheck.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void AnamnesisCheck.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public AnamnesisCheck AnamnesisCheck.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AnamnesisCheck merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager AnamnesisCheck.entityManager() {
        EntityManager em = new AnamnesisCheck().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long AnamnesisCheck.countAnamnesisChecks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AnamnesisCheck o", Long.class).getSingleResult();
    }
    
    public static List<AnamnesisCheck> AnamnesisCheck.findAllAnamnesisChecks() {
        return entityManager().createQuery("SELECT o FROM AnamnesisCheck o", AnamnesisCheck.class).getResultList();
    }
    
    public static AnamnesisCheck AnamnesisCheck.findAnamnesisCheck(Long id) {
        if (id == null) return null;
        return entityManager().find(AnamnesisCheck.class, id);
    }
    
    public static List<AnamnesisCheck> AnamnesisCheck.findAnamnesisCheckEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AnamnesisCheck o", AnamnesisCheck.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
