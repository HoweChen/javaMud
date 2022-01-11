package com.howechen.mudspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfiguration {
  @Bean
  public String getSampleString() {
    return "hello";
  }
}
