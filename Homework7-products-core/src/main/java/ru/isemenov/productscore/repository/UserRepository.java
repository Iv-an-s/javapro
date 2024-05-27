package ru.isemenov.productscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
