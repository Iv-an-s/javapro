package ru.isemenov.productscore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.isemenov.productscore.dto.ProductConverter;
import ru.isemenov.productscore.dto.ProductInfoDto;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.model.Product;
import ru.isemenov.productscore.repository.ProductRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final UserService userService;
    private final WarehouseService warehouseService;
    private final ProductTypeService productTypeService;
    private final ProductConverter productConverter;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());

    public ProductService(ProductRepository repository, UserService userService, WarehouseService warehouseService, ProductTypeService productTypeService, ObjectMapper objectMapper, ProductConverter productConverter) {
        this.repository = repository;
        this.userService = userService;
        this.warehouseService = warehouseService;
        this.productTypeService = productTypeService;
        this.productConverter = productConverter;
    }

    public ProductInfoDto findProductById(Long id) {
        Optional<Product> productOptional = repository.findProductById(id);
        Product product = productOptional.orElseThrow (() -> new ResourceNotFoundException(
                String.format("Продукт с id = %d не найден в БД", id)));

        Integer quantity = warehouseService.getQuantityByProductId(id);

        return new ProductInfoDto(
                product.getId(),
                product.getProductType().getTitle(),
                product.getPrice(),
                quantity
        );
    }

    public List<ProductInfoDto> findAll() {
        return repository.findAll().stream()
                .map(productConverter::convertProductToProductDto)
                .collect(Collectors.toList());
    }
}
