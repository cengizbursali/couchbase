package com.java.couchbase.service;

import com.java.couchbase.model.Person;
import com.java.couchbase.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person getPerson(Integer id) {
        final Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }
}
