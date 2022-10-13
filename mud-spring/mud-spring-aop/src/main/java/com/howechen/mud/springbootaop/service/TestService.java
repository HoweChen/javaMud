package com.howechen.mud.springbootaop.service;


import com.howechen.mud.springbootaop.MethodAnnotation;
import com.howechen.mud.springbootaop.ParameterAnnotation;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestService.class);

  @MethodAnnotation
  public String test(@ParameterAnnotation String name, String age) {
    log.info("Hello {}, {}", name, age);
    return "World";
  }

  public String testWithoutAnnotation(String name, String age) {
    log.info("Hello {}, {}", name, age);
    return "World";
  }

}

