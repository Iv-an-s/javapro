package ru.isemenov.productscore.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.exception.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountRepository {

    private final HikariDataSource dataSource;

    public AccountRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getBalance(String account) {

        try (Connection connection = dataSource.getConnection()) {
            String findBalanceQuery = "SELECT balance FROM accounts WHERE account = (?)";
            PreparedStatement findBalancePreparedStatement = connection.prepareStatement(findBalanceQuery);
            findBalancePreparedStatement.setString(1, account);
            ResultSet resultSet = findBalancePreparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new ResourceNotFoundException(String.format("account with name = %s is not present", account));
            }
            resultSet.next();
            return resultSet.getInt("balance");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
}
