package ru.isemenov.productscore.dto;

import org.springframework.stereotype.Component;
import ru.isemenov.productscore.entity.Product;

@Component
public class ProductConverter {
    public ProductInfoDto convertProductToProductDto(Product product){

        return new ProductInfoDto(
                product.getId(),
                product.getTitle(),
                product.getCategory().getTitle(),
                product.getPrice(),
                product.getAvailableCount()
        );
    }
}
