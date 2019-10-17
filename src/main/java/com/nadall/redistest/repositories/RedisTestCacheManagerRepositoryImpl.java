package com.nadall.redistest.repositories;

import com.nadall.redistest.dtos.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RedisTestCacheManagerRepositoryImpl implements RedisTestCacheManagerRepository{
  @Autowired
  private BaseCacheManagerRepository baseCacheManagerRepository;
  private String nameSpace = "Person";

  @Override
  public void save(Person person) {
    this.baseCacheManagerRepository.save(this.nameSpace, person.getPersonId(), person, 1);
  }

  @Override
  public void saveMultiple(List<Person> person) {
    this.baseCacheManagerRepository.save(this.nameSpace, "people", person, 1);
  }

  @Override
  public Person find(Long personId) {
    return (Person) this.baseCacheManagerRepository.find(this.nameSpace, personId);
  }

  @Override
  public List<Person> findAll() {
    return (List<Person>) this.baseCacheManagerRepository.find(this.nameSpace, "people");
  }

  @Override
  public void update(Person person) {
    this.baseCacheManagerRepository.save(this.nameSpace, person.getPersonId(), person, 1);
  }

  @Override
  public void delete(Long personId) {
    this.baseCacheManagerRepository.delete(this.nameSpace, personId);
  }
}
