package ru.isemenov.productscore.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WarehouseRepository {

    private final HikariDataSource dataSource;

    public WarehouseRepository(HikariDataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
    }

    public Integer getQuantityByProductId(Long productId) {

        try (Connection connection = dataSource.getConnection()) {
            String findQuantityQuery = "SELECT quantity FROM warehouse WHERE product_id = (?)";
            PreparedStatement findIdPreparedStatement = connection.prepareStatement(findQuantityQuery);
            findIdPreparedStatement.setLong(1, productId);
            ResultSet resultSet = findIdPreparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return -1;
            }
            resultSet.next();
            return resultSet.getInt("quantity");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
}
