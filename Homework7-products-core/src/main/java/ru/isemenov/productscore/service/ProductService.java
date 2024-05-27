package ru.isemenov.productscore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isemenov.productscore.dto.ProductConverter;
import ru.isemenov.productscore.dto.ProductInfoDto;
import ru.isemenov.productscore.entity.Product;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductConverter productConverter;

    public ProductInfoDto findProductById(Long id) {
        Product product = findById(id);
        return productConverter.convertProductToProductDto(product);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Продукт с id = %d не найден в БД", id)));
    }

    public List<ProductInfoDto> findAll() {
        return repository.findAll().stream()
                .map(productConverter::convertProductToProductDto)
                .collect(Collectors.toList());
    }
}
