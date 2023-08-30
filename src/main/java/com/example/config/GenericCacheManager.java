package com.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Arrays;
@Configuration
public class GenericCacheManager {
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(Arrays.asList(
                "employee"
        ));
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {"employee"})
    @Scheduled(fixedDelayString = "${service.cache.timeout}", initialDelay = 500)
    public void evictCache(){
        System.out.println("cahce removed at:"+ LocalDateTime.now());
    }
}
