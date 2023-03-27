package com.howechen.javaMud.spring.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Person {
  private String name = "Haha";

  public Person(String name) {
    this.name = name;
  }

  public Person() {
  }

  @Override
  public String toString() {
    return "MyTestBean{"
        + "name='"
        + name
        + '\''
        + '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
