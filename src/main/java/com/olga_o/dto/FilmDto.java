package com.olga_o.dto;

import java.math.BigDecimal;
import java.util.Set;

public class FilmDto {
    public Short filmId;
    public String title;
    public String description;
    public Short releaseYear;
    public Short rentalDuration;
    public BigDecimal rentalRate;
    public Short length;
    public BigDecimal replacementCost;
    public String rating;
    public Set<String> specialFeatures;
    public String language;
    public String originalLanguage;
    public Set<String> categories;
    public Set<String> actors;
}
