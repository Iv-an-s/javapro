package ru.isemenov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.isemenov.homework5.controllers.ProductController;
import ru.isemenov.homework5.models.Product;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        ProductController controller = context.getBean(ProductController.class);

        System.out.println("Клиент с Id = 1 имеет следующие продукты:");
        for (Product product : controller.findProductsByUserId(1L)) {
            System.out.println(product);
        }

        System.out.println("Продукт с id = 3: \n" + controller.findProductById(3L));
    }
}