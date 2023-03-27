package com.howechen.mudspringboot.mybatis.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.howechen.mudspringboot.mybatis.mapper")
public class DBConfiguration {

}
