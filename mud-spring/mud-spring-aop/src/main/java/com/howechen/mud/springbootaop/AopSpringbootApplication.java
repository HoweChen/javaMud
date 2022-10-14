package com.howechen.mud.springbootaop;

import com.howechen.mud.springbootaop.pojo.TestPojo;
import com.howechen.mud.springbootaop.service.ChildService;
import com.howechen.mud.springbootaop.service.ExceptionService;
import com.howechen.mud.springbootaop.service.ParameterService;
import com.howechen.mud.springbootaop.service.TestService;
import com.howechen.mud.springbootaop.service.ThreadingService;
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
    ConfigurableApplicationContext context = SpringApplication.run(AopSpringbootApplication.class,
        args);

//    TestService bean = context.getBean(TestService.class);
//    bean.test("yuhaochen", "18");
//    bean.testWithoutAnnotation("stupidsometing", "28");
//
//    ChildService childService = context.getBean(ChildService.class);
//    childService.test();
//
//    try {
//
//      ExceptionService exceptionService = context.getBean(ExceptionService.class);
//      exceptionService.throwException();
//    } catch (Exception ignored) {
//
//    }
//
//    ParameterService parameterService = context.getBean(ParameterService.class);
//    parameterService.printParameter(new TestPojo("yuhaochen"));

    // threading test
    ThreadingService threadingService = context.getBean(ThreadingService.class);
    for (int i = 0; i < 10; i++) {
      new Thread(threadingService::printThread).start();
    }
  }

}
