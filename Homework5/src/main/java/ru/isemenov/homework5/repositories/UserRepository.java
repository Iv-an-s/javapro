package ru.isemenov.homework5.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {
    private final HikariDataSource dataSource;

    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Long save(String username) {
        try (Connection connection = dataSource.getConnection()) {
            Long id = findUserId(username);
            if (id > 0){
                return id;
            }

            String saveUserQuery = "INSERT INTO users (username) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveUserQuery);
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();

            return findUserId(username);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    private Long findUserId(String username) {
        try (Connection connection = dataSource.getConnection()) {
            String findIndexQuery = "SELECT id FROM users WHERE username = (?)";
            PreparedStatement findIdPreparedStatement = connection.prepareStatement(findIndexQuery);
            findIdPreparedStatement.setString(1, username);
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
}
