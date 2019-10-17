package com.nadall.redistest.repositories;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseCacheManagerReporitoryImpl implements BaseCacheManagerRepository{

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Override
  public void save(String nameSpace, Object key, Object value) {
    this.redisTemplate.opsForValue().set(this.makeKey(nameSpace, key), value);
  }

  @Override
  public void save(String nameSpace, Object key, Object value, int expirationTime) {
    this.redisTemplate.opsForValue().set(this.makeKey(nameSpace, key), value, expirationTime, TimeUnit.MINUTES);
  }

  @Override
  public Object find(String nameSpace, Object key) {
    return this.redisTemplate.opsForValue().get(this.makeKey(nameSpace, key));
  }

  @Override
  public Object find(String nameSpace, Object key, int expirationTime) {
    this.redisTemplate.expire(key.toString(), expirationTime, TimeUnit.MINUTES);
    return this.redisTemplate.opsForValue().get(this.makeKey(nameSpace, key));
  }

  @Override
  public void delete(String nameSpace, Object key) {
    this.redisTemplate.delete(this.makeKey(nameSpace, key));
  }

  private String makeKey(String nameSpace, Object key) {
    return  nameSpace.concat(":").concat(key.toString());
  }
}
