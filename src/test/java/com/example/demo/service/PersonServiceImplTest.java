package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
@InjectMocks
private PersonServiceImpl personServiceimpl;
@Mock
private PersonDAO personDAO;
    @Test
    void addPerson() {
        String name = "Surya";
        Person p = new Person("Surya", UUID.fromString("a0ecfcd9-b149-4a2a-b3a4-22a8a717a1ef"));
        when(personDAO.insertPerson(name)).thenReturn(p);
        Person q = personServiceimpl.addPerson(name);
        assertEquals("Surya",q.getName());
        verify(personDAO,times(1)).insertPerson(name);
    }

    @Test
    void showPersonList() {
        Person p = new Person("Surya", UUID.fromString("a0ecfcd9-b149-4a2a-b3a4-22a8a717a1ef"));
        Person q = new Person("Surya", UUID.fromString("a0ecfcd9-b149-4a2a-b3a4-22a8a717a1ef"));
        List<Person> personList = new ArrayList<>();
        personList.add(p);personList.add(q);
        when(personDAO.showAllPerson()).thenReturn(personList);
        List<Person> personList2 = personServiceimpl.showPersonList();
        assertEquals(personList.size(), personList2.size());
    }

    @Test
    void selectPersonById() {


    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void savePersonUsingMongo() {
    }

    @Test
    void getAllPersonUsingMongo() {
    }

    @Test
    void getPersonById() {
    }

    @Test
    void deletePersonByIdUsingMongo() {
    }

    @Test
    void updatingByIdUsingMongo() {
    }
}