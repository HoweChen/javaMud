package com.howechen.mudspringboot;

import com.howechen.mudspringboot.entity.CustomerDO;
import com.howechen.mudspringboot.repository.CustomerDORepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MudSpringbootApplication {

  public static final Logger log = LoggerFactory.getLogger(MudSpringbootApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MudSpringbootApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CustomerDORepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new CustomerDO("Jack", "Bauer"));
      repository.save(new CustomerDO("Chloe", "O'Brian"));
      repository.save(new CustomerDO("Kim", "Bauer"));
      repository.save(new CustomerDO("David", "Palmer"));
      repository.save(new CustomerDO("Michelle", "Dessler"));

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
