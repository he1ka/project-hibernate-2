package com.olga_o.controller;

import com.olga_o.dto.FilmDto;
import com.olga_o.entity.Film;
import com.olga_o.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(@Autowired FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public List<Film> getAll() {
        List<Film> films = filmService.getAll();
        return films.stream().collect(Collectors.toList());
    }

    @PostMapping
    public FilmDto create(FilmDto filmDetails) {
        this.filmService.create(filmDetails);

        return new FilmDto();
    }
}