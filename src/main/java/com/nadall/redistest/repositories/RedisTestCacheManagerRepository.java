package com.nadall.redistest.repositories;

import com.nadall.redistest.dtos.Person;
import java.util.List;

public interface RedisTestCacheManagerRepository {
  void save(Person person);
  void saveMultiple(List<Person> person);
  Person find(Long personId);
  List<Person> findAll();
  void update(Person person);
  void delete (Long personId);
}
