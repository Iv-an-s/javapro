package ru.isemenov.productscore.service;

import org.springframework.stereotype.Service;
import ru.isemenov.productscore.repository.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository repository;

    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    public Integer getQuantityByProductId(Long id) {
        return repository.getQuantityByProductId(id);
    }
}
