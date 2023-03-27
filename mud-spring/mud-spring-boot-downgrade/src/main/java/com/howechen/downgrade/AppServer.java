package com.howechen.downgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class AppServer {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppServer.class, args);

        ServerModeConfiguration.ServerMode bean = context.getBean(ServerModeConfiguration.ServerMode.class);
        System.out.println(bean.name());
    }
}