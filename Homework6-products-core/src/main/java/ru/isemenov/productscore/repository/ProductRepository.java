package ru.isemenov.productscore.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.model.Product;
import ru.isemenov.productscore.model.ProductType;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository()
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Product> findProductById(Long id) {
        String query = "SELECT p.id, p.product_type_id, p.price, pt.title FROM products p " +
                "JOIN product_type pt ON p.product_type_id = pt.id " +
                "WHERE p.id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        try {
            Product product = jdbcTemplate.queryForObject(query, params, new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (DataAccessException e){
            throw new ResourceNotFoundException(
                    String.format("Продукт с id = %d не найден в БД", id)
            );
        }
    }

    public List<Product> findAll() {
        String query = "SELECT p.id, p.product_type_id, p.price, pt.title FROM products p " +
                "JOIN product_type pt ON p.product_type_id = pt.id ";

        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    private static final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setPrice(rs.getInt("price"));

            ProductType productType = new ProductType();
            productType.setId(rs.getLong("product_type_id"));
            productType.setTitle(rs.getString("title"));
            product.setProductType(productType);

            return product;
        }
    }
}
