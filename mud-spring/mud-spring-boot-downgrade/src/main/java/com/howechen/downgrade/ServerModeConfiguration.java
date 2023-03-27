package com.howechen.downgrade;

import com.alibaba.csp.sentinel.spi.SpiLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerModeConfiguration {

    public enum ServerMode {
        FULL, HALF, MINI
    }

    @Bean
    public ServerMode serverMode() {
        // read server mode file from resources/META-INF/server-mode
        return SpiLoader.of(ServerMode.class).loadFirstInstance();
    }
}
