package com.gt.hotel.config.redis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Component;

/**
 * RedisSession配置Config 默认session超时时间 1小时 3600秒
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/07/16
 */
@Slf4j
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisSessionConfig {

    @Autowired
    private RedisSessionProperties redisSessionProperties;

    /**
     * 设置Cookie作用于
     *
     * @return DefaultCookieSerializer
     */
    @Bean(name = "defaultCookieSerializer")
    public DefaultCookieSerializer defaultCookieSerializer() {
        log.info(" domainName : {} , cookieName : {} , cookiePath : {} ", redisSessionProperties.getDomainName(), redisSessionProperties.getCookieName(), redisSessionProperties.getCookiePath());
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setDomainName(redisSessionProperties.getDomainName());
        cookieSerializer.setCookieName(redisSessionProperties.getCookieName());
        cookieSerializer.setCookiePath(redisSessionProperties.getCookiePath());
        return cookieSerializer;
    }

    /**
     * 内部类注入
     */
    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix = "redisSession")
    static class RedisSessionProperties {
        private String cookieName;
        private String cookiePath;
        private String domainName;
    }
}


