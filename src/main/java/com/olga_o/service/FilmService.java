package com.olga_o.service;

import com.olga_o.entity.Film;
import com.olga_o.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(@Autowired FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAll() {
        return filmRepository.getAll();
    }
}
