package com.howechen.mudspringboot.hybrid.config;

import com.howechen.mudspringboot.hybrid.KotlinStaticFunctionKt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfiguration {
  @Bean
  public String getSampleString() {
    return "hello";
  }
}
