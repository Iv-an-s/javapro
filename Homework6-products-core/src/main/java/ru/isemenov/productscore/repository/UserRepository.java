package ru.isemenov.productscore.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.exception.ResourceNotFoundException;
import ru.isemenov.productscore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    private final HikariDataSource dataSource;

    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Long save(String username) {
        try (Connection connection = dataSource.getConnection()) {
            Long id = findUserId(username);
            if (id > 0) {
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

    public User findById(Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            String findUserQuery = "SELECT id, username, user_account FROM users WHERE id = (?) ";
            PreparedStatement findUserPreparedStatement = connection.prepareStatement(findUserQuery);
            findUserPreparedStatement.setLong(1, userId);
            ResultSet resultSet = findUserPreparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new ResourceNotFoundException(String.format("User with id = %s is not present", userId));
            }
            resultSet.next();

            User user = new User();
            user.setId(userId);
            user.setUsername(resultSet.getString("username"));
            user.setAccount(resultSet.getString("user_account"));

            return user;

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
