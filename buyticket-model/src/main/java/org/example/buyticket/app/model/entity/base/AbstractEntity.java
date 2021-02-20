package org.example.buyticket.app.model.entity.base;

import org.example.buyticket.app.model.entity.person.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Base class for all business entities
 *
 * @author Gulyamov Rustam
 */
@MappedSuperclass
public abstract class AbstractEntity {
    public static final String FIELD_CREATED_AT = "createdAt";

    /**
     * Unique entity identifier
     */
    private int id;

    /**
     * Timestamp of entity creation
     */
    private LocalDateTime creationDate;

    /**
     *Timestamp of entity last modification
     */
    private LocalDateTime lastModificationDate;

    /**
     *Person who created specified entity
     */
    private Account createdBy;

    /**
     * Person who last modified entity
     */
    private Account modifiedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="CREATED_AT", nullable = false, updatable = false)
    public LocalDateTime getCreatedAt() {
        return creationDate;
    }

    public void setCreatedAt(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @PrePersist
    public void prePersist() {
        if(getId() == 0) {
            setCreatedAt(LocalDateTime.now());
        }
    }

    @Column(name="MODIFIED_AT", insertable = false)
    public LocalDateTime getModifiedAt() {
        return lastModificationDate;
    }

    public void setModifiedAt(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name="CREATED_BY", updatable = false)
    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name="MODIFIED_BY", updatable = false)
    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AbstractEntity another = (AbstractEntity) obj;
        return this.id == another.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
}
