package com.guruji.auth.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.guruji.auth.enums.CacheConfigEnum;
import com.guruji.auth.enums.GeneralEnum;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
public class UniverseCacheConfig extends CachingConfigurerSupport {

  @Bean
  public net.sf.ehcache.CacheManager ehCacheManager() {

    CacheConfiguration tenSecondCache = new CacheConfiguration();
    tenSecondCache.setName(CacheConfigEnum.TEN_SECOND_TO_LIVE.name());
    tenSecondCache.setMemoryStoreEvictionPolicy(GeneralEnum.LRU.getValue());
    tenSecondCache.setMaxEntriesLocalHeap(CacheConfigEnum.MAX_ENTRIES_LOCAL_HEAP.getValue());
    tenSecondCache.setTimeToLiveSeconds(CacheConfigEnum.TEN_SECOND_TO_LIVE.getValue());

    CacheConfiguration tenMinuteCache = new CacheConfiguration();
    tenMinuteCache.setName(CacheConfigEnum.TEN_MINUTES_TO_LIVE.getName());
    tenMinuteCache.setMemoryStoreEvictionPolicy(GeneralEnum.LRU.getValue());
    tenMinuteCache.setMaxEntriesLocalHeap(CacheConfigEnum.MAX_ENTRIES_LOCAL_HEAP.getValue());
    tenMinuteCache.setTimeToLiveSeconds(CacheConfigEnum.TEN_MINUTES_TO_LIVE.getValue());

    CacheConfiguration twentyMinuteCache = new CacheConfiguration();
    twentyMinuteCache.setName(CacheConfigEnum.TWENTY_MINUTES_TO_LIVE.getName());
    twentyMinuteCache.setMemoryStoreEvictionPolicy(GeneralEnum.LRU.getValue());
    twentyMinuteCache.setMaxEntriesLocalHeap(CacheConfigEnum.MAX_ENTRIES_LOCAL_HEAP.getValue());
    twentyMinuteCache.setTimeToLiveSeconds(CacheConfigEnum.TWENTY_MINUTES_TO_LIVE.getValue());

    net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
    config.addCache(tenSecondCache);
    config.addCache(tenMinuteCache);
    config.addCache(twentyMinuteCache);
    return net.sf.ehcache.CacheManager.newInstance(config);
  }

  @Bean
  @Override
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager());
  }

}
