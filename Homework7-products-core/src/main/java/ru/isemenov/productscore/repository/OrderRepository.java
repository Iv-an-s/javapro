package ru.isemenov.productscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
