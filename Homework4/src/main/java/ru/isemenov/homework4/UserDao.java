package ru.isemenov.homework4;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private final HikariDataSource dataSource;

    public UserDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String findByIdQuery = "select id, username from users where id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            }

            resultSet.next();
            long userId = resultSet.getLong("id");
            String username = resultSet.getString("username");
            User user = new User(userId, username);

            return Optional.of(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String username) {
        try (Connection connection = dataSource.getConnection()) {
            String findByUsernameQuery = "select id, username from users where username = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(findByUsernameQuery);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            }

            resultSet.next();
            long userId = resultSet.getLong("id");
            String name = resultSet.getString("username");
            User user = new User(userId, name);

            return Optional.of(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String username) {
        try (Connection connection = dataSource.getConnection()) {
            String saveUserQuery = "INSERT INTO users (username) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveUserQuery);
            preparedStatement.setString(1, username);

            if (preparedStatement.executeUpdate() == 1) {
                System.out.printf("Пользователь с именем %s был сохранен в базу данных \n", username);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            String findAllQuery = "select id, username from users";
            PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return Collections.emptyList();
            }

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                long userId = resultSet.getLong("id");
                String username = resultSet.getString("username");
                result.add(new User(userId, username));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> deleteById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String findByIdQuery = "select id, username from users where id = (?)";
            String deleteByIdQuery = "delete from users where id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            }

            resultSet.next();
            long userId = resultSet.getLong("id");
            String username = resultSet.getString("username");
            User user = new User(userId, username);

            preparedStatement = connection.prepareStatement(deleteByIdQuery);
            preparedStatement.setLong(1, id);

            if (preparedStatement.executeUpdate() == 1) {
                System.out.printf("Пользователь с id = %d (username = %s) был удален из базы данных\n", id, username);
            }

            return Optional.of(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> deleteByUsername(String username) {
        try (Connection connection = dataSource.getConnection()) {
            String findByUsernameQuery = "select id, username from users where username = (?)";
            String deleteByUsernameQuery = "delete from users where id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(findByUsernameQuery);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return Optional.empty();
            }

            resultSet.next();
            long userId = resultSet.getLong("id");
            String name = resultSet.getString("username");
            User user = new User(userId, name);

            preparedStatement = connection.prepareStatement(deleteByUsernameQuery);
            preparedStatement.setLong(1, userId);

            if (preparedStatement.executeUpdate() == 1) {
                System.out.printf("Пользователь с username = %s был удален из базы данных\n", username);
            }

            return Optional.of(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try (Connection connection = dataSource.getConnection()) {
            String deleteAllQuery = "delete from users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteAllQuery);
            System.out.println("Таблица users очищена");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
