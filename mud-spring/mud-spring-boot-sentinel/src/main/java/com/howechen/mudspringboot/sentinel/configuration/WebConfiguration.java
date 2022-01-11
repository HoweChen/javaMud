package com.howechen.mudspringboot.sentinel.configuration;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    SentinelWebMvcConfig config = new SentinelWebMvcConfig();

    // Depending on your situation, you can choose to process the BlockException via
    // the BlockExceptionHandler or throw it directly, then handle it
    // in Spring web global exception handler.

    // config.setBlockExceptionHandler((request, response, e) -> { throw e; });

    // Use the customized handler.
    config.setBlockExceptionHandler(new ISentinelBlockExceptionHandler());

    // Custom configuration if necessary
    config.setHttpMethodSpecify(true);
    // By default web context is true, means that unify web context(i.e. use the default context
    // name),
    // in most scenarios that's enough, and it could reduce the memory footprint.
    // If set it to false, entrance contexts will be separated by different URLs,
    // which is useful to support "chain" relation flow strategy.
    // We can change it and view different result in `Resource Chain` menu of dashboard.
    config.setWebContextUnify(true);
    config.setOriginParser(request -> request.getHeader("S-user"));

    // Add sentinel interceptor
    registry
        .addInterceptor(new ISentinelWebInterceptor(config))
        .addPathPatterns("/**")
        .excludePathPatterns("/rule/add");
  }
}