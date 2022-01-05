package com.howechen.mudspringboot.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

  @GetMapping("hello")
  public String getStr() {
    return "Hello world";
  }

  @PostMapping("hello")
  public String getStrPost() {
    return "Hello world post";
  }
}
