package ru.isemenov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.isemenov.homework4.UserService;

@ComponentScan
public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationRunner.class);

        UserService service = context.getBean(UserService.class);

        service.saveUser("Bill Gates");

        service.deleteAll();

        service.saveUser("Bill Gates");

        for (int i = 0; i < 3; i++) {
            service.saveUser("Username" + i);
        }

        try {
            System.out.println(service.findByUsername("Bill Gates"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(service.findById(1L));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        service.findAll().forEach(System.out::println);

        service.deleteByUsername("Bill Gates");

        for (int i = 0; i < 3; i++) {
            service.deleteByUsername("Username" + i);
        }
    }
}