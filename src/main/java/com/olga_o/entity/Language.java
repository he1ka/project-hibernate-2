package com.olga_o.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte languageId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @CreationTimestamp
    private Date lastUpdate;

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
