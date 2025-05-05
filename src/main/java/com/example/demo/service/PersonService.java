package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    Person addPerson(String name);
    List<Person> showPersonList();
    // select by id
    Optional<Person> selectPersonById(UUID id);
    // delete by id
    String deleteById(UUID id);
    // update by id
    String updateById(UUID id, Person person);

    Person savePersonUsingMongo(String name);
    List<Person> getAllPersonUsingMongo();
    Optional<Person> getPersonById(UUID id);
    String deletePersonByIdUsingMongo (UUID id);
    String updatingByIdUsingMongo (Person updatePerson);
}
