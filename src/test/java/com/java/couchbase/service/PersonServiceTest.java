package com.java.couchbase.service;

import com.java.couchbase.model.Person;
import com.java.couchbase.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void it_should_save_person_to_couchbase() {
        // Given
        final Person person = new Person();

        // When
        personService.save(person);

        // Then
        verify(personRepository).save(person);
    }

    @Test
    public void it_should_get_person_from_couchbase() {
        // Given
        final Integer id = 3;
        final Person person = new Person();
        Optional<Person> optionalPerson = Optional.of(person);
        when(personRepository.findById(id)).thenReturn(optionalPerson);

        // When
        final Person result = personService.getPerson(id);

        // Then
        verify(personRepository).findById(id);
        assertThat(result).isEqualTo(person);
    }
}