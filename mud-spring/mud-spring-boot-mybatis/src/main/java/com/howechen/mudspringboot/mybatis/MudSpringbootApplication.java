package com.howechen.mudspringboot.mybatis;

import com.howechen.mudspringboot.mybatis.entity.CustomerDO;
import com.howechen.mudspringboot.mybatis.repository.CustomerDORepository;
import java.util.List;
import java.util.Optional;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.howechen.mudspringboot.mybatis.repository")
public class MudSpringbootApplication {

  public static final Logger log = LoggerFactory.getLogger(MudSpringbootApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MudSpringbootApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CustomerDORepository repository) {
    return (args) -> {
      // save a few customers
      repository.insert(new CustomerDO("Jack", "Bauer"));
      repository.insert(new CustomerDO("Chloe", "O'Brian"));
      repository.insert(new CustomerDO("Kim", "Bauer"));
      repository.insert(new CustomerDO("David", "Palmer"));
      repository.insert(new CustomerDO("Michelle", "Dessler"));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (CustomerDO customer : repository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Optional<CustomerDO> customer = repository.findById(1L);
      customer.ifPresentOrElse(
          foundResult -> {
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");
          },
          () -> log.error("Cannot find customer"));

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      Optional<List<CustomerDO>> found = repository.findByLastName("Bauer");
      found.ifPresentOrElse(
          doList -> doList.forEach(customerDO -> log.info(customerDO.toString())),
          () -> log.error("Cannot find customers"));
    };
  }
}
