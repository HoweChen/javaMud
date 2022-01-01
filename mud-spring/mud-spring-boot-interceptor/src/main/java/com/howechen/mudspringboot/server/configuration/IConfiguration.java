package com.howechen.mudspringboot.server.configuration;

import com.howechen.mudspringboot.server.interceptors.I401Interceptor;
import com.howechen.mudspringboot.server.interceptors.IDeferredResultInterceptor;
import com.howechen.mudspringboot.server.interceptors.IPassInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author yuhaochen */
@Configuration
public class IConfiguration implements WebMvcConfigurer {

  @Autowired private I401Interceptor i401Interceptor;
  @Autowired private IPassInterceptor passInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(passInterceptor).addPathPatterns("/**");
    registry
        .addInterceptor(i401Interceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/haha/pass/**")
        .excludePathPatterns("/**/error");
  }
}
