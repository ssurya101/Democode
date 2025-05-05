package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO  {
    // insert person by name
    Person insertPerson(String name);
    // show all person
    List<Person> showAllPerson();
    // select by id
    Optional<Person> selectPersonById(UUID id);
    // delete by id
    String deleteById(UUID id);
    // update by id
    String updateById(UUID id, Person person);
}
