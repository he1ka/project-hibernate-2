package com.olga_o.entity;

import com.olga_o.model.RatingType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_year")
    private Short releaseYear;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "tinyint unsigned default 3")
    private Short rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2, columnDefinition = "default 4.99")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2, columnDefinition = "default 19.99")
    private BigDecimal replacementCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", columnDefinition = "default 'G'")
    private RatingType rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @CreationTimestamp
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id", insertable = false, updatable = false)
    private Language originalLanguage;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL)
    private FilmText filmText;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @ManyToMany(mappedBy = "films")
    private Set<Actor> actors;

    public Short getFilmId() {
        return filmId;
    }

    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public RatingType getRating() {
        return rating;
    }

    public void setRating(RatingType rating) {
        this.rating = rating;
    }

    public Set<String> getSpecialFeatures() {
        if (specialFeatures == null) {
            return Collections.emptySet();
        }

        return Collections.unmodifiableSet(
                new HashSet<String>(Arrays.asList(specialFeatures.split(",")))
        );
    }

    public void setSpecialFeatures(Set<String> specialFeatures) {
        if (specialFeatures == null) {
            this.specialFeatures = null;
        } else {
            this.specialFeatures = String.join(",", specialFeatures);
        }
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public FilmText getFilmText() {
        return filmText;
    }

    public void setFilmText(FilmText filmText) {
        this.filmText = filmText;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
