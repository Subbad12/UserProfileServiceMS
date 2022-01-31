package com.user.profile.cache;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Subba
 *
 */
@Configuration
public class CacheConfig{
 
	/**
	 * @return cacheManager
	 */
	@Bean
    public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("userCache")));
        return cacheManager;
    }
}
