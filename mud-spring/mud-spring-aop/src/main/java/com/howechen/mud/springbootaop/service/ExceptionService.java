package com.howechen.mud.springbootaop.service;


import com.howechen.mud.springbootaop.MethodAnnotation;
import com.howechen.mud.springbootaop.ParameterAnnotation;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionService.class);


  public void throwException() throws RuntimeException{
    throw new RuntimeException("Hello");
  }

}

