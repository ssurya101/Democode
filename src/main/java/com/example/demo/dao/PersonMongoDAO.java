package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("Mongo")
public interface PersonMongoDAO extends MongoRepository<Person, UUID> {
}
