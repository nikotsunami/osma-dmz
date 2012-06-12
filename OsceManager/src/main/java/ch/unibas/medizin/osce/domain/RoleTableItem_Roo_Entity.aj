// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.RoleTableItem;
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
import javax.persistence.Table;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RoleTableItem_Roo_Entity {
    
    declare @type: RoleTableItem: @Entity;
    
    declare @type: RoleTableItem: @Table(name = "role_table_item");
    
    @PersistenceContext
    transient EntityManager RoleTableItem.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long RoleTableItem.id;
    
    @Version
    @Column(name = "version")
    private Integer RoleTableItem.version;
    
    public Long RoleTableItem.getId() {
        return this.id;
    }
    
    public void RoleTableItem.setId(Long id) {
        this.id = id;
    }
    
    public Integer RoleTableItem.getVersion() {
        return this.version;
    }
    
    public void RoleTableItem.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void RoleTableItem.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RoleTableItem.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RoleTableItem attached = RoleTableItem.findRoleTableItem(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RoleTableItem.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RoleTableItem.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RoleTableItem RoleTableItem.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RoleTableItem merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager RoleTableItem.entityManager() {
        EntityManager em = new RoleTableItem().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RoleTableItem.countRoleTableItems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RoleTableItem o", Long.class).getSingleResult();
    }
    
    public static List<RoleTableItem> RoleTableItem.findAllRoleTableItems() {
        return entityManager().createQuery("SELECT o FROM RoleTableItem o", RoleTableItem.class).getResultList();
    }
    
    public static RoleTableItem RoleTableItem.findRoleTableItem(Long id) {
        if (id == null) return null;
        return entityManager().find(RoleTableItem.class, id);
    }
    
    public static List<RoleTableItem> RoleTableItem.findRoleTableItemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RoleTableItem o", RoleTableItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
