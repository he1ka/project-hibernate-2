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
