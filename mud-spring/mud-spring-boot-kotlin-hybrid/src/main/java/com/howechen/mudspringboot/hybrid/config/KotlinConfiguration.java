package com.howechen.mudspringboot.hybrid.config;

import com.howechen.mudspringboot.hybrid.KotlinBean;
import com.howechen.mudspringboot.hybrid.KotlinStaticFunctionKt;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KotlinConfiguration {

  final KotlinBean kotlinBean;

  public KotlinConfiguration(KotlinBean kotlinBean) {
    this.kotlinBean = kotlinBean;
  }

  @Bean
  public void kotlinStaticString() {
    KotlinStaticFunctionKt.main();
  }

  @PostConstruct
  public void afterConstruct() {
    System.out.println(kotlinBean.test());
  }
}
