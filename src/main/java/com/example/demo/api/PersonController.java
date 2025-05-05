package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequestMapping("api")
@RestController
public class PersonController {
    private  final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/adding")
    public void PersonAdditionEndpoint(@RequestBody Person person){
        log.info("Calling service from controller");
        personService.addPerson(person.getName());
    }
    @GetMapping("/showing")
    public List<Person> PersonShowingEndpoint(){
        log.info("showPersonList function returns {}",personService.showPersonList());
        return personService.showPersonList();
    }
    @GetMapping("selectingById/{id}")
    public Optional<Person> showPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id);
    }
    @DeleteMapping("deletingById/{id}")
    public String deletePersonById(@PathVariable("id") UUID id){
        return personService.deleteById(id);
    }
    @PutMapping("updatingById/{id}")
    public String UpdatePersonById(@PathVariable("id") UUID id, @RequestBody Person updatePerson){
        return personService.updateById(id,updatePerson);
    }

//    ------------------------------------------------------------------------------------------------------

    @PostMapping("saveUsingMongo")
    public Person savePerson(@RequestBody Person person){
        log.info("Calling savePerson using mongo from controller");
        return personService.savePersonUsingMongo(person.getName());
    }
    @GetMapping("getAllUsingMongo")
    public List<Person> getAllUsingMongo() {
        log.info("Calling getAll Person using mongo from controller");
        List<Person> personList = personService.getAllPersonUsingMongo();
        log.warn("List of Person saved in Mongo {}",personList);
        return personList;
    }

    @GetMapping("getPersonByIdUsingMongo/{id}")
    public Optional<Person> getPersonByIdUsingMongo(@PathVariable("id") UUID id){
        return personService.getPersonById(id);
    }

    @DeleteMapping("deletingByIdUsingMongo/{id}")
    public String deletePersonByIdUsingMongo(@PathVariable("id") UUID id){
        return personService.deletePersonByIdUsingMongo(id);
    }

    @PostMapping("updatingByIdUsingMongo")
    public String updatingByIdUsingMongo(@RequestBody Person updatePerson){
        return personService.updatingByIdUsingMongo(updatePerson);
    }

//    ------------------------------------------------------------------------------------------------------

    @GetMapping("/testing")
    public String testing() {
        return "Controller is working";
    }

}

