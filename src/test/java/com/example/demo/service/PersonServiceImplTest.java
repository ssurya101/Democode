package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.dao.PersonMongoDAO;
import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
@InjectMocks
private PersonServiceImpl personServiceimpl;
@Mock
private PersonDAO personDAO;
@Mock
private PersonMongoDAO personMongoDAO;
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
        Person p = new Person("Surya", UUID.fromString("a0ecfcd9-b149-4a2a-b3a4-22a8a717a1ef"));
        UUID id = UUID.fromString("a0ecfcd9-b149-4a2a-b3a4-22a8a717a1ef");
        when(personDAO.selectPersonById(id)).thenReturn(Optional.of(p));
        Optional<Person> optionalPerson = personServiceimpl.selectPersonById(id);
        assertEquals(p.getId(),id);
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        when(personDAO.deleteById(id)).thenReturn("Deleted");

        String result = personServiceimpl.deleteById(id);

        assertEquals("Deleted", result);
        verify(personDAO, times(1)).deleteById(id);
    }

    @Test
    void updateById() {
        UUID id = UUID.randomUUID();
        Person p = new Person("Updated Name", id);
        when(personDAO.updateById(id, p)).thenReturn("Updated");

        String result = personServiceimpl.updateById(id, p);

        assertEquals("Updated", result);
        verify(personDAO, times(1)).updateById(id, p);
    }

    @Test
    void savePersonUsingMongo() {
        String name = "MongoPerson";
        Person savedPerson = personServiceimpl.savePersonUsingMongo(name);

        assertNotNull(savedPerson.getId());
        assertEquals(name, savedPerson.getName());
        verify(personMongoDAO, times(1)).save(any(Person.class));
    }

    @Test
    void getAllPersonUsingMongo() {
        List<Person> mockList = Arrays.asList(
                new Person("Mongo1", UUID.randomUUID()),
                new Person("Mongo2", UUID.randomUUID())
        );
        when(personMongoDAO.findAll()).thenReturn(mockList);

        List<Person> result = personServiceimpl.getAllPersonUsingMongo();

        assertEquals(2, result.size());
        verify(personMongoDAO, times(1)).findAll();
    }

    @Test
    void getPersonById() {
        UUID id = UUID.randomUUID();
        Person p = new Person("MongoUser", id);
        when(personMongoDAO.findById(id)).thenReturn(Optional.of(p));

        Optional<Person> result = personServiceimpl.getPersonById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(personMongoDAO, times(1)).findById(id);
    }

    @Test
    void deletePersonByIdUsingMongo_WhenExists() {
        UUID id = UUID.randomUUID();
        when(personMongoDAO.existsById(id)).thenReturn(true);

        String result = personServiceimpl.deletePersonByIdUsingMongo(id);

        assertEquals("given id got deleted", result);
        verify(personMongoDAO, times(1)).deleteById(id);
    }

    @Test
    void deletePersonByIdUsingMongo_WhenNotExists() {
        UUID id = UUID.randomUUID();
        when(personMongoDAO.existsById(id)).thenReturn(false);

        String result = personServiceimpl.deletePersonByIdUsingMongo(id);

        assertEquals("given id not found", result);
        verify(personMongoDAO, never()).deleteById(id);
    }

    @Test
    void updatingByIdUsingMongo() {
        Person updatePerson = new Person("Updated Mongo", UUID.randomUUID());

        String result = personServiceimpl.updatingByIdUsingMongo(updatePerson);

        assertEquals("given person got updated", result);
        verify(personMongoDAO, times(1)).save(updatePerson);
    }
}
