package ru.isemenov.productscore.service;

import org.springframework.stereotype.Service;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.model.ProductType;
import ru.isemenov.productscore.repository.ProductTypeRepository;

import java.util.Optional;


@Service
public class ProductTypeService {

    private final ProductTypeRepository repository;

    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public ProductType findProductTypeById(Long id) {
        Optional<ProductType> productTypeOptional = repository.findProductTypeById(id);
        return productTypeOptional.orElseThrow(() -> new ResourceNotFoundException(
                String.format("ProductType с id = %d не найден в БД", id)
        ));
    }
}
