package com.howechen.mudspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yuhaochen
 */
@SpringBootApplication
public class MudSpringbootApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(MudSpringbootApplication.class,
        args);

//    for (int i = 0; i < 10; i++) {
//
//      System.out.println(run.getBean("modifiedValue", String.class));
//    }
  }
}
