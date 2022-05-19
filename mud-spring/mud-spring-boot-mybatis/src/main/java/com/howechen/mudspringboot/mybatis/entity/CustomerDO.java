package com.howechen.mudspringboot.mybatis.entity;

public class CustomerDO {

  private Long id;

  private String firstName;

  private String lastName;

  private Integer version;

  public CustomerDO() {}

  public CustomerDO(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.version = 1;
  }

  @Override
  public String toString() {
    return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
