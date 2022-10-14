package com.howechen.mud.springbootaop.service;


import com.howechen.mud.springbootaop.pojo.TestPojo;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ParameterService.class);


  public void printParameter(TestPojo parameter) throws RuntimeException{
    log.info("Hello {}", parameter.getName());
  }

}

