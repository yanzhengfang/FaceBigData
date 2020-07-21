package com.koocloud.facerecognition.showdata.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

@Configuration
public class RedisTemplateConfig extends CachingConfigurerSupport {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置lettuce连接池
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig<LettucePoolingClientConfiguration> redisPool() {
        return new GenericObjectPoolConfig<>();
    }
    /**
     * 本地redis配置信息
     */
    @Configuration
    public static class LocalRedisConfig {
        @org.springframework.beans.factory.annotation.Value("${spring.redis.local.host:127.0.0.1}")
        private String host;
        @org.springframework.beans.factory.annotation.Value("${spring.redis.local.port:6379}")
        private Integer port;
        @org.springframework.beans.factory.annotation.Value("${spring.redis.local.password:}")
        private String password;
        @org.springframework.beans.factory.annotation.Value("${spring.redis.local.database:0}")
        private Integer database;

        @Bean
        public RedisStandaloneConfiguration redisLocalConfig() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(host);
            config.setPassword(RedisPassword.of(password));
            config.setPort(port);
            config.setDatabase(database);
            return config;
        }
    }


    /**
     * 远程数据配置
     */
    @Configuration
    @ConditionalOnProperty(name = "host", prefix = "spring.remote-redis")
    public static class rempoteRedisConfig {
        @Value("${spring.remote-redis.host:127.0.0.1}")
        private String host;
        @Value("${spring.remote-redis.port:6379}")
        private Integer port;
        @Value("${spring.remote-redis.password:}")
        private String password;
        @Value("${spring.remote-redis.database:0}")
        private Integer database;

        @Bean
        public RedisStandaloneConfiguration redisRemoteConfig() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(host);
            config.setPassword(RedisPassword.of(password));
            config.setPort(port);
            config.setDatabase(database);
            return config;
        }
    }

    /**
     * 配置本地数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     */
    @Bean("localFactory")
    @Primary
    public LettuceConnectionFactory factory(GenericObjectPoolConfig<LettucePoolingClientConfiguration> config,
                                            RedisStandaloneConfiguration redisLocalConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisLocalConfig, clientConfiguration);
    }

    /**
     * 配置远程的redis链接工厂
     * @param config
     * @param redisRemoteConfig
     * @return
     */
    @Bean("remoteFactory")
    public LettuceConnectionFactory dbFactory(GenericObjectPoolConfig<LettucePoolingClientConfiguration> config,
                                              RedisStandaloneConfiguration redisRemoteConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisRemoteConfig, clientConfiguration);
    }

    /**
     * 配置本地数据源的RedisTemplate
     * 注意：这里指定使用名称=factory 的 RedisConnectionFactory
     * 并且标识第一个数据源是默认数据源 @Primary
     */
    @Bean("localStrRedisTemplate")
    @Primary
    public RedisTemplate<String, String> redisTemplate(@Qualifier("localFactory") RedisConnectionFactory factory) {

        return buildRedisTemplate(factory);
    }

    /**
     * 配置远程数据源的RedisTemplate
     * 注意：这里指定使用名称=factory2 的 RedisConnectionFactory
     */
    @Bean("remoteStrRedisTemplate")
    public RedisTemplate<String, String> redisTemplate2(@Qualifier("remoteFactory") RedisConnectionFactory dbFactory) {
        return buildRedisTemplate(dbFactory);
    }

    public RedisTemplate<String, String> buildRedisTemplate(RedisConnectionFactory factory) {

        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = jackson2JsonRedisSerializer();
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }



}
