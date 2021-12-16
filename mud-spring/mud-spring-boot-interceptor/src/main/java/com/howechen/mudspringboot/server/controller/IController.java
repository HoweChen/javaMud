package com.howechen.mudspringboot.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/haha")
public class IController {

  public static final Logger log = LoggerFactory.getLogger(IController.class);

  @GetMapping("/pass")
  @ResponseBody
  public String getPass() {
    log.info("In the controller");
    return "PASS";
  }
}
