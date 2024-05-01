package ru.isemenov.homework5.services;

import org.springframework.stereotype.Service;
import ru.isemenov.homework5.models.ProductType;
import ru.isemenov.homework5.repositories.ProductTypeRepository;

@Service
public class ProductTypeService {

    private final ProductTypeRepository repository;

    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public ProductType save(ProductType productType){
        Long id = repository.save(productType.getTitle());
        productType.setId(id);
        return productType;
    }
}
