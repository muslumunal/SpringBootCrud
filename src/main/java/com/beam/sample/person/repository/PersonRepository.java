package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
