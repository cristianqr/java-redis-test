package com.nadall.redistest.repositories;

import com.nadall.redistest.dtos.Person;
import java.util.List;
import java.util.Map;

public interface PersonRepository {
  void save(Person person);
  Person find(Long personId);
  List<Person> findAll();
  Map<Long, Person> findAllMap();
  void update(Person person);
  void delete(Long personId);
}
