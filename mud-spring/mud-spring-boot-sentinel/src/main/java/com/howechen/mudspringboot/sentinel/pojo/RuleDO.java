package com.howechen.mudspringboot.sentinel.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;

/** @author yuhaochen */
@Data
@Entity
public class RuleDO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "resource", nullable = false)
  private String resource;

  @Column(name = "control_behavior", nullable = false)
  private Integer controlBehavior;

  @Column(name = "count", nullable = false)
  private Double count;

  @Column(name = "grade", nullable = false)
  private Integer grade;

  @Column(name = "strategy", nullable = false)
  private Integer strategy;
}
