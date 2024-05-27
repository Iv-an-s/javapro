package ru.isemenov.productscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isemenov.productscore.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{


}
