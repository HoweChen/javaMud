package com.howechen.mudspringboot.hybrid.config;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuhaochen
 */
@Configuration
@ConfigurationProperties("customized")
public class CustomizeConfiguration {

  private static final Logger log = LoggerFactory.getLogger(CustomizeConfiguration.class);
  private boolean booleanValue = false;
  private Set<String> setValue = new HashSet<>();

  @PostConstruct
  public void post() {
    log.info("boolean value :{}", booleanValue);
    log.info("Set value: {}", setValue);
  }

  public boolean isBooleanValue() {
    return booleanValue;
  }

  public void setBooleanValue(boolean booleanValue) {
    this.booleanValue = booleanValue;
  }

  public Set<String> getSetValue() {
    return setValue;
  }

  public void setSetValue(Set<String> setValue) {
    this.setValue = setValue;
  }
}
