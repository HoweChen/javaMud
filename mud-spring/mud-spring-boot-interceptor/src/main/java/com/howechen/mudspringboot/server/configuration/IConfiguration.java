package com.howechen.mudspringboot.server.configuration;

import com.howechen.mudspringboot.server.interceptors.I401Interceptor;
import com.howechen.mudspringboot.server.interceptors.IDeferredResultInterceptor;
import com.howechen.mudspringboot.server.interceptors.IPassInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author yuhaochen */
@Configuration
public class IConfiguration implements WebMvcConfigurer {

  @Bean
  public I401Interceptor getInterceptor() {
    return new I401Interceptor();
  }

  @Bean
  public IPassInterceptor getPassInterceptor() {
    return new IPassInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getPassInterceptor()).addPathPatterns("/**");
    registry
        .addInterceptor(getInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/**/haha/pass/**")
        .excludePathPatterns("/**/error");
  }
}
