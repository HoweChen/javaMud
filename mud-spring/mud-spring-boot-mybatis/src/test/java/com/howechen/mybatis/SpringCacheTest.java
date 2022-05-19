package com.howechen.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
class SpringCacheTest {
  @Autowired CacheManager cacheManager;

  @Test
  void contextLoads() {
    Cache cache_manager = cacheManager.getCache("CACHE_MANAGER");
    System.out.println(cache_manager);
  }

  
}
