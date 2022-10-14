package com.howechen.mud.springbootaop.service;

import org.slf4j.Logger;

public abstract class AbstractService {

  private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

  public void test() {
    System.out.println("Hello");
  }

}
