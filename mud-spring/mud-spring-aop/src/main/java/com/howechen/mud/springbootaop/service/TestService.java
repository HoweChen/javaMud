package com.howechen.mud.springbootaop.service;


import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestService.class);

  public String test(String name, String age) {
    log.info("Hello {}, {}", name, age);
    return "World";
  }

}

