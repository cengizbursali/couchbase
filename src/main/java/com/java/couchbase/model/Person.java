package com.java.couchbase.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Getter
@Setter
@Document
public class Person {

    @Id
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String job;
}
