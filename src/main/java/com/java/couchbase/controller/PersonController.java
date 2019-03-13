package com.java.couchbase.controller;

import com.java.couchbase.model.Person;
import com.java.couchbase.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFavorite(@RequestBody Person person) {
        personService.save(person);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person saveFavorite(@PathVariable Integer id) {
        return personService.getPerson(id);
    }
}
