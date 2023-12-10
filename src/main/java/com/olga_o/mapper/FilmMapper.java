package com.olga_o.mapper;

import com.olga_o.dto.FilmDto;
import com.olga_o.dto.RentalDto;
import com.olga_o.entity.*;
import com.olga_o.repository.ActorRepository;
import com.olga_o.repository.CategoryRepository;
import com.olga_o.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmMapper {
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;

    public FilmMapper(
            @Autowired LanguageRepository languageRepository,
            @Autowired CategoryRepository categoryRepository,
            @Autowired ActorRepository actorRepository
    ) {
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
        this.actorRepository = actorRepository;
    }

    public Film map(FilmDto filmDto) {
        Film film = new Film();
        film.setTitle(filmDto.title);
        film.setDescription(filmDto.description);
        film.setReleaseYear(filmDto.releaseYear);
        film.setRentalDuration(filmDto.rentalDuration);
        film.setRentalRate(filmDto.rentalRate == null
                ? BigDecimal.valueOf(0.0)
                : filmDto.rentalRate
        );
        film.setLength(filmDto.length);
        film.setRating(filmDto.rating);
        film.setSpecialFeatures(filmDto.specialFeatures);
        film.setLanguage(languageRepository.getByName(filmDto.language));
        film.setOriginalLanguage(languageRepository.getByName(filmDto.originalLanguage));

        FilmText filmText = new FilmText();
        filmText.setTitle(filmDto.title);
        filmText.setDescription(filmDto.description);
        filmText.setFilm(film);

        film.setFilmText(filmText);

        film.setCategories(convertCategoriesToEntities(filmDto.categories));

        film.setActors(convertActorsToEntities(filmDto.actors));

        return film;
    }

    public FilmDto map(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.filmId = film.getFilmId();
        filmDto.title = film.getTitle();
        filmDto.description = film.getDescription();
        filmDto.releaseYear = film.getReleaseYear();
        filmDto.rentalDuration = film.getRentalDuration();
        filmDto.rentalRate = film.getRentalRate();
        filmDto.length = film.getLength();
        filmDto.replacementCost = film.getReplacementCost();
        filmDto.rating = film.getRating().toString();
        filmDto.specialFeatures = film.getSpecialFeatures();
        filmDto.language = film.getLanguage().getName();
        filmDto.originalLanguage = film.getOriginalLanguage().getName();
        filmDto.categories = film.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        filmDto.actors = film.getActors().stream().map(Actor::getLastName).collect(Collectors.toSet());

        return filmDto;
    }

    private Set<Actor> convertActorsToEntities(Set<String> actors) {
        return actors.stream()
                .map(actorRepository::getByLastName)
                .collect(Collectors.toSet());
    }

    private Set<Category> convertCategoriesToEntities(Set<String> categoryNames) {
        return categoryNames.stream()
                .map(categoryRepository::getByName)
                .collect(Collectors.toSet());
    }
}
