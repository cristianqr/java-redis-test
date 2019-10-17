package com.nadall.redistest.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Value("${spring.redis.host}")
  private String redisHost;
  @Value("${spring.redis.port}")
  private int redisPort;
  @Value("${spring.redis.password}")
  private String redisPassword;
  @Value("${spring.redis.ssl}")
  private Boolean redisSsl;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(this.redisHost);
    redisStandaloneConfiguration.setPort(this.redisPort);
    redisStandaloneConfiguration.setPassword(this.redisPassword);

    JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
    jedisClientConfiguration.connectTimeout(Duration.ofSeconds(120));
    jedisClientConfiguration.readTimeout(Duration.ofSeconds(120));

    if(this.redisSsl.equals(Boolean.TRUE)) {
      jedisClientConfiguration.useSsl();
    }

    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
        jedisClientConfiguration.build());

    return jedisConFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    redisTemplate.afterPropertiesSet();

    return  redisTemplate;
  }
}