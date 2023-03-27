package com.howechen.mudspringboot.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFunctionConfiguration {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(
      BeanFunctionConfiguration.class);

  @Value("${testValue}")
  public String testValue;

  @Bean("modifiedValue")
  public String getTestValue() {
    return modifyTestValue(testValue);
  }

  private String modifyTestValue(String testValue) {
    log.info("Haha I am here");

    return testValue + "haha";
  }

}
