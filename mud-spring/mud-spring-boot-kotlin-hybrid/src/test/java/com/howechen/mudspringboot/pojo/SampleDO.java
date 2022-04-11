package com.howechen.mudspringboot.pojo;

public class SampleDO {
  public String username;
  public String password;

  @Override
  public String toString() {
    return "SampleDO{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
