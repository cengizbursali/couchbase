package com.java.couchbase.repository;

import com.java.couchbase.model.Person;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface PersonRepository extends CouchbaseRepository<Person, Integer> {
}