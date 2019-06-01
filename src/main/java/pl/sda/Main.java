package pl.sda;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        CustomerDaoImpl dao = (CustomerDaoImpl) context.getBean("customerDaoImpl");
        List<Customer> customers = dao.findAll();

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        System.out.println("----------------");
        List<Customer> allJdbcTemplate = dao.findAllJdbcTemplate();
        allJdbcTemplate.forEach(System.out::println);

    }
}
