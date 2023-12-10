package com.olga_o.service;

import com.olga_o.entity.Film;
import com.olga_o.entity.Inventory;
import com.olga_o.entity.Store;
import com.olga_o.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(@Autowired InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addFilmToStoreInventory(Store store, Film film) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);

        return this.inventoryRepository.save(inventory);
    }
}
