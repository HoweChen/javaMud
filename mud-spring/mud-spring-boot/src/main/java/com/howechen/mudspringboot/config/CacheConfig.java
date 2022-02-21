package com.howechen.mudspringboot.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuhaochen
 */
@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("CACHE_MANAGER");
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }

  @NonNull
  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder().recordStats();
  }
}
