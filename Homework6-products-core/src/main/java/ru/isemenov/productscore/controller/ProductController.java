package ru.isemenov.productscore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.isemenov.productscore.dto.ProductInfoDto;
import ru.isemenov.productscore.dto.ProductResponseDto;
import ru.isemenov.productscore.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ProductInfoDto findProductById(@PathVariable Long id) {
        return service.findProductById(id);
    }

    @GetMapping("/products")
    public ProductResponseDto findAll() {
        return new ProductResponseDto(service.findAll(), HttpStatus.OK.toString());
    }
}
