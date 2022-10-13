package com.howechen.mud.springbootaop;

import com.howechen.mud.springbootaop.service.TestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopSpringbootApplication {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(
      AopSpringbootApplication.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(AopSpringbootApplication.class,
        args);

    TestService bean = run.getBean(TestService.class);
    bean.test("yuhaochen", "18");
    bean.testWithoutAnnotation("stupidsometing", "28");
  }

}
