package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.dao.PersonMongoDAO;
import com.example.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{
    private final PersonDAO personDAO;
    private final PersonMongoDAO personMongoDAO;
    @Autowired
    public PersonServiceImpl(@Qualifier("nonMongo") PersonDAO personDAO,
                             @Qualifier("Mongo")PersonMongoDAO personMongoDAO) {
        this.personDAO = personDAO;
        this.personMongoDAO = personMongoDAO;
    }

    @Override
    public Person addPerson(String name) {
        log.info("Calling DAO from service");
        return personDAO.insertPerson(name);
    }

    @Override
    public List<Person> showPersonList() {
        return personDAO.showAllPerson();
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personDAO.selectPersonById(id);
    }

    @Override
    public String deleteById(UUID id) {
        return personDAO.deleteById(id);
    }

    @Override
    public String updateById(UUID id, Person person) {
        return personDAO.updateById(id,person);
    }

    @Override
    public Person savePersonUsingMongo(String name) {
        Person p1 = new Person();
        p1.setId(UUID.randomUUID());
        p1.setName(name);
        personMongoDAO.save(p1);
        log.info("Person added to the list are {}", p1);
        return p1;
    }
    @Override
    public List<Person> getAllPersonUsingMongo() {
        List<Person> allPerson = personMongoDAO.findAll();
        log.info("All person list from mongo are {}", allPerson);
        return allPerson;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        Optional<Person> optionalPerson = personMongoDAO.findById(id);
        return optionalPerson;
    }

    @Override
    public String deletePersonByIdUsingMongo(UUID id) {
        if(personMongoDAO.existsById(id)){
            personMongoDAO.deleteById(id);
            return "given id got deleted";
        }
        return "given id not found";
    }

    @Override
    public String updatingByIdUsingMongo(Person updatePerson) {
            personMongoDAO.save(updatePerson);
            return "given person got updated";
    }
}