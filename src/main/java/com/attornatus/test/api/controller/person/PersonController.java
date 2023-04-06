package com.attornatus.test.api.controller.person;

import com.attornatus.test.api.request.person.PersonRequest;
import com.attornatus.test.api.request.person.PersonWithAddressRequest;
import com.attornatus.test.api.response.person.PersonResponse;
import com.attornatus.test.domain.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("rest/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    public List<PersonResponse> getPersons(){
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public PersonResponse getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @PostMapping("/create")
        public PersonResponse createPerson(@RequestBody PersonRequest person){
            return personService.createPerson(person);
        }

    @PostMapping("/create/address")
    public ResponseEntity<PersonResponse> createPersonWithAddress(@RequestBody PersonWithAddressRequest person){
        return ResponseEntity.ok().body(personService.createPersonWithAddress(person));
        }

    @PutMapping("/edit/{id}")
    public PersonResponse editPerson(@RequestBody PersonRequest personRequest, @PathVariable Long id){
        return personService.editPerson(personRequest, id);
    }
}
