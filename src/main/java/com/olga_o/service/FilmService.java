package com.olga_o.service;

import com.olga_o.dto.FilmDto;
import com.olga_o.entity.Film;
import com.olga_o.entity.Store;
import com.olga_o.mapper.FilmMapper;
import com.olga_o.repository.FilmRepository;
import com.olga_o.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;
    private final FilmMapper filmMapper;
    private final InventoryService inventoryService;

    public FilmService(
            @Autowired FilmRepository filmRepository,
            @Autowired FilmMapper filmMapper,
            @Autowired StoreRepository storeRepository,
            @Autowired InventoryService inventoryService
    ) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
        this.storeRepository = storeRepository;
        this.inventoryService = inventoryService;
    }

    public List<Film> getAll() {
        return filmRepository.getAll();
    }

    @Transactional
    public FilmDto create(FilmDto filmDetails) {
        // add new film
        Film film = filmMapper.map(filmDetails);
        film = filmRepository.save(film);

        // add film to rent possibility
        // add it to inventory of all available stores
        for (Store store : this.storeRepository.getAll()) {
            this.inventoryService.addFilmToStoreInventory(store, film);
        }

        return filmMapper.map(film);
    }
}
