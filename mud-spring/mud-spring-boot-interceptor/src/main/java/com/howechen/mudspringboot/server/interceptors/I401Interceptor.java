package com.howechen.mudspringboot.server.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class I401Interceptor implements HandlerInterceptor {

  public static final Logger log = LoggerFactory.getLogger(I401Interceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("Request URI: {}", request.getRequestURI());
    response.setStatus(401);
    return false;
  }
}
