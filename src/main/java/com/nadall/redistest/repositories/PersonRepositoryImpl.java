package com.nadall.redistest.repositories;

import com.nadall.redistest.dtos.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
  private static final String KEY = "Person";
  private RedisTemplate<String, Object> redisTemplate;
  private HashOperations<String, Long, Person> hashOperations;

  public PersonRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = this.redisTemplate.opsForHash();
  }

  @Override
  public void save(Person person) {
    // redisTemplate.opsForValue().set(KEY);
    this.hashOperations.put(KEY, person.getPersonId(), person);
  }

  @Override
  public Person find(Long personId) {
    return this.hashOperations.get(KEY, personId);
  }

  @Override
  public List<Person> findAll() {
    Map<Long, Person> personsMap = this.hashOperations.entries(KEY);
    List<Person> persons = new ArrayList<>();
    for(Map.Entry<Long, Person> entry: personsMap.entrySet()) {
      persons.add(entry.getValue());
    }
    return persons;
  }

  @Override
  public Map<Long, Person> findAllMap() {
    return this.hashOperations.entries(KEY);
  }

  @Override
  public void update(Person person) {
    this.hashOperations.put(KEY, person.getPersonId(), person);
  }

  @Override
  public void delete(Long personId) {
    this.hashOperations.delete(KEY, personId);
  }


}
