package com.olga_o.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @CreationTimestamp
    private Date lastUpdate;

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
