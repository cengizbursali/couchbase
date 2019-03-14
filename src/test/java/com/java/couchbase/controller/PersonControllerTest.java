package com.java.couchbase.controller;

import com.java.couchbase.model.Person;
import com.java.couchbase.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void it_should_save_person() throws Exception {

        // When
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 1234,\n" +
                        "    \"name\": \"cengiz\",\n" +
                        "    \"surname\": \"bursalioglu\",\n" +
                        "    \"age\": 26,\n" +
                        "    \"job\": \"software developer\"\n" +
                        "}"))
                .andExpect(status().isCreated());

        // Then
        ArgumentCaptor<Person> requestCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personService).save(requestCaptor.capture());
        Person person = requestCaptor.getValue();
        assertThat(person.getId()).isEqualTo(1234);
        assertThat(person.getName()).isEqualTo("cengiz");
        assertThat(person.getSurname()).isEqualTo("bursalioglu");
        assertThat(person.getAge()).isEqualTo(26);
        assertThat(person.getJob()).isEqualTo("software developer");

    }

    @Test
    public void it_should_get_person_by_person_id() throws Exception {
        // Given
        final Person person = new Person();
        person.setId(2);
        when(personService.getPerson(2)).thenReturn(person);

        // When
        ResultActions result = mockMvc.perform(get("/person/2").contentType(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)));
    }
}