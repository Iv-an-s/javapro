package ru.isemenov.homework5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.isemenov.homework5.models.Product;
import ru.isemenov.homework5.models.ProductType;
import ru.isemenov.homework5.models.User;
import ru.isemenov.homework5.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products/{id}")
    public List<Product> findProductsByUserId(@PathVariable Long id) {
        return service.findProductsByUserId(id);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return service.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Product product) {
        System.out.println(product);
        User user = product.getUser();
        System.out.println(user);
        ProductType productType = product.getProductType();
        System.out.println(productType);
        service.save(product);
    }
}
