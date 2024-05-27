package ru.isemenov.productscore.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isemenov.productscore.dto.ProductInfoDto;
import ru.isemenov.productscore.dto.ProductResponseDto;
import ru.isemenov.productscore.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

    @GetMapping("/{id}")
    public ProductInfoDto findProductById(@PathVariable Long id) {
        return service.findProductById(id);
    }

    @GetMapping("/products")
    public ProductResponseDto findAll() {
        return new ProductResponseDto(service.findAll(), HttpStatus.OK.toString());
    }
}
