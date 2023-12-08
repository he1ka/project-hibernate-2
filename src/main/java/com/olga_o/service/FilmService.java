package com.olga_o.service;

import com.olga_o.dto.FilmDto;
import com.olga_o.entity.Film;
import com.olga_o.mapper.FilmMapper;
import com.olga_o.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public FilmService(
            @Autowired FilmRepository filmRepository,
            @Autowired FilmMapper filmMapper
    ) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public List<Film> getAll() {
        return filmRepository.getAll();
    }

    public void create(FilmDto filmDetails) {
        // add new film
        Film film = this.filmMapper.map(filmDetails);
        film = filmRepository.save(film);

        // add film to rent possibility
    }
}
