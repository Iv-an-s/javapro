package ru.isemenov.homework5.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.isemenov.homework5.models.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductTypeRepository {

    private final HikariDataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductTypeRepository(HikariDataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(String title) {
        try (Connection connection = dataSource.getConnection()) {
            Long id = findProductTypeId(title);
            if (id > 0){
                return id;
            }

            String saveUserQuery = "INSERT INTO product_type (title) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveUserQuery);
            preparedStatement.setString(1, title);

            preparedStatement.executeUpdate();

            return findProductTypeId(title);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    private Long findProductTypeId(String title) {
        try (Connection connection = dataSource.getConnection()) {
            String findIndexQuery = "SELECT id FROM product_type WHERE title = (?)";
            PreparedStatement findIdPreparedStatement = connection.prepareStatement(findIndexQuery);
            findIdPreparedStatement.setString(1, title);
            ResultSet resultSet = findIdPreparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return -1L;
            }
            resultSet.next();
            return resultSet.getLong("id");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    public List<ProductType> findProductTypesByUserId(Long id) {
        String query = "SELECT PRODUCT_TYPE.ID, TITLE FROM product_type " +
                "JOIN products ON product_type.ID = products.PRODUCT_TYPE_ID WHERE USER_ID = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.query(query, params, new ProductTypeRowMapper());
    }

    private static final class ProductTypeRowMapper implements RowMapper<ProductType> {
        @Override
        public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductType product = new ProductType();
            product.setId(rs.getLong("id"));
            product.setTitle(rs.getString("title"));
            return product;
        }
    }
}
