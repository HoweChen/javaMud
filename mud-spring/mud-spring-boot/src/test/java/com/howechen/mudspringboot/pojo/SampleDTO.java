package com.howechen.mudspringboot.pojo;

public class SampleDTO {
  public String username;
  public String password;

  @Override
  public String toString() {
    return "SampleDTO{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
