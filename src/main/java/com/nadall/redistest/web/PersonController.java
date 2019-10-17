package com.nadall.redistest.web;

import com.nadall.redistest.dtos.Person;
import com.nadall.redistest.repositories.PersonRepository;
import com.nadall.redistest.repositories.RedisTestCacheManagerRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {
  @Autowired
  private RedisTestCacheManagerRepository personRepository;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;


  /*@GetMapping("findAll")
  public List<Person> findAll() {
    return this.personRepository.findAll();
  }

  @GetMapping("findAllMap")
  public Map<Long, Person> findAllMap() {
    return this.personRepository.findAllMap();
  }*/

  @PostMapping()
  public Person save(@RequestBody Person person) {
    this.personRepository.save(person);
    return person;
  }

  @PostMapping("saveMultiple")
  public List<Person> saveMultiple(@RequestBody List<Person> person) {
    this.personRepository.saveMultiple(person);
    return this.personRepository.findAll();
  }

  @GetMapping("{personId}")
  public Person find(@PathVariable("personId") Long personId) {
    return this.personRepository.find(personId);
  }

  @PutMapping("{personId}")
  public Person update(@PathVariable("personId") Long personId, @RequestBody Person person) {
    this.personRepository.update(person);
    return this.personRepository.find(personId);
  }

  @DeleteMapping("{personId}")
  public void update(@PathVariable("personId") Long personId) {
    this.personRepository.delete(personId);
  }

  @GetMapping("test")
  public String test() {
    ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();

    String key = "name";

    if(!this.stringRedisTemplate.hasKey(key)) {
      ops.set("name", "Cristian");
    }

    return  ops.get("name");
  }
}
