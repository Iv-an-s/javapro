package ru.isemenov.homework5.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isemenov.homework5.models.Product;
import ru.isemenov.homework5.models.ProductType;
import ru.isemenov.homework5.models.User;
import ru.isemenov.homework5.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final UserService userService;
    private final ProductTypeService productTypeService;

    public ProductService(ProductRepository repository, UserService userService, ProductTypeService productTypeService) {
        this.repository = repository;
        this.userService = userService;
        this.productTypeService = productTypeService;
    }

    public List<Product> findProductsByUserId(Long id) {
        return repository.findProductsByUserId(id);
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id);
    }

    @Transactional
    public void save(Product product) {
        User user = userService.save(product.getUser());
        ProductType productType = productTypeService.save(product.getProductType());
        repository.save(
                product.getAccount(),
                product.getBalance(),
                user.getId(),
                productType.getId()
        );
    }
}
