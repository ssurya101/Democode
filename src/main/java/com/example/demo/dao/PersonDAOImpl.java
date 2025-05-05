package com.example.demo.dao;

import com.example.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository("nonMongo")
public class PersonDAOImpl implements PersonDAO{
    List<Person> personList = new ArrayList<>();

    @Override
    public Person insertPerson(String name) {
        Person p1 = new Person();
        p1.setId(UUID.randomUUID());
        p1.setName(name);
        personList.add(p1);
        log.info("Person added to the list are {}", personList);
        return p1;
    }

    @Override
    public List<Person> showAllPerson() {
        return personList;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personList.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public String deleteById(UUID id) {
        Optional<Person> optionalPerson = selectPersonById(id);
        if(optionalPerson.isEmpty())
            return "ID Required for deletion is not present";
        personList.remove(optionalPerson.get());
        return "ID Required for deletion got deleted";
    }

    @Override
    public String updateById(UUID id, Person updatePerson) {
//        Optional<Person> optionalPerson = selectPersonById(id);
        int f = 0;
        for(Person p : personList){
            if(p.getId().equals(id)){
                p.setName(updatePerson.getName());
                f++;
                break;
            }
        }
        if(f==0)
            return "ID Required for Updation is not present";
        return "ID Required for Updation got updated";
    }
}
