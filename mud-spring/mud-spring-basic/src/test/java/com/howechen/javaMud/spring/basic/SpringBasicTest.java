package com.howechen.javaMud.spring.basic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

public class SpringBasicTest {

  @Test
  public void createBeanThroughXML() {
    BeanFactory bf = new XmlBeanFactory( new ClassPathResource("spring-config.xml"));
    Person person = (Person) bf.getBean("person");
    System.out.println(person.getName());
  }

  @Test
  public void createBeanThroughXMLNewWay() {
    DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
    reader.loadBeanDefinitions(new ClassPathResource("spring-config.xml"));


    Person person = (Person) bf.getBean("person");
    System.out.println(person.getName());
  }

}
