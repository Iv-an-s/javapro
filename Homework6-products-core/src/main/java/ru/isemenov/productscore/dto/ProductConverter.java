package ru.isemenov.productscore.dto;

import org.springframework.stereotype.Component;
import ru.isemenov.productscore.model.Product;

@Component
public class ProductConverter {
    public ProductInfoDto convertProductToProductDto(Product product){
        ProductInfoDto productInfoDto = new ProductInfoDto();
        productInfoDto.setId(product.getId());
        productInfoDto.setTitle(product.getProductType().getTitle());
        productInfoDto.setPrice(product.getPrice());
        return productInfoDto;
    }
}
