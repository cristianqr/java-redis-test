package com.nadall.redistest.repositories;

public interface BaseCacheManagerRepository {
  void save(String nameSpace, Object key, Object value);
  void save(String nameSpace, Object key, Object value, int expirationTime);
  Object find(String nameSpace, Object key);
  Object find(String nameSpace, Object key, int expirationTime);
  void delete(String nameSpace, Object key);
}
