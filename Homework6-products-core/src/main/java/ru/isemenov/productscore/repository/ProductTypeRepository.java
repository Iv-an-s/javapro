package ru.isemenov.productscore.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.model.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ProductTypeRepository {

    private final HikariDataSource dataSource;

    public ProductTypeRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<ProductType> findProductTypeById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT pt.title FROM product_type pt WHERE pt.id = :id";
            PreparedStatement findProductTypeStatement = connection.prepareStatement(query);
            findProductTypeStatement.setLong(1, id);
            ResultSet rs = findProductTypeStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                return Optional.empty();
            }
            rs.next();
            String title = rs.getString("title");

            return Optional.of(new ProductType(id, title));

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных:" + e.getMessage());
        }
    }
}
