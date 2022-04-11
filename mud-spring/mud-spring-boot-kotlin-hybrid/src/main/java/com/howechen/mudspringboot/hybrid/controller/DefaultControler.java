package com.howechen.mudspringboot.hybrid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class DefaultControler {
  @GetMapping("/world")
  @ResponseBody
  public String helloWorld() {
    return "Hello";
  }
}
