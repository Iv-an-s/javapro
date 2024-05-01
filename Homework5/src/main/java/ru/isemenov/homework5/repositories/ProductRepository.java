package ru.isemenov.homework5.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.isemenov.homework5.models.Product;
import ru.isemenov.homework5.models.ProductType;
import ru.isemenov.homework5.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository()
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findProductsByUserId(Long id) {
        String query = "SELECT p.id, p.account, p.balance, p.user_id, u.username, p.product_type_id, pt.title FROM products p " +
                "JOIN users u ON p.user_id = u.id " +
                "JOIN product_type pt ON p.product_type_id = pt.id " +
                "WHERE u.id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.query(query, params, new ProductRowMapper());
    }

    public Product findProductById(Long id) {
        String query = "SELECT p.id, p.account, p.balance, p.user_id, u.username, p.product_type_id, pt.title FROM products p " +
                "JOIN users u ON p.user_id = u.id " +
                "JOIN product_type pt ON p.product_type_id = pt.id " +
                "WHERE p.id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.queryForObject(query, params, new ProductRowMapper());
    }




    public void save(String account, Integer balance, Long userId, Long productTypeId) {
        String query = "INSERT INTO products (account, balance, user_id, product_type_id) " +
                "VALUES(:account, :balance, :user_id, :product_type_id)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("account", account);
        parameters.addValue("balance", balance);
        parameters.addValue("user_id", userId);
        parameters.addValue("product_type_id", productTypeId);

        jdbcTemplate.update(query, parameters);
    }



    private static final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setAccount(rs.getString("account"));
            product.setBalance(rs.getInt("balance"));

            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            product.setUser(user);

            ProductType productType = new ProductType();
            productType.setId(rs.getLong("product_type_id"));
            productType.setTitle(rs.getString("title"));
            product.setProductType(productType);

            return product;
        }
    }
}
