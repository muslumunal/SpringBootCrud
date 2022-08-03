package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String>, PersonRepositoryCustom {

    List<Person> findByAgeAndLastName(int age, String lastName);

    @Query(value = "{lastName: ?1, age: ?0}", fields = "{firstName: 1}")
    List<Person> sameAsAbove(int age, String lastName);

    default Person empty() {
        return new Person()
                .setAge(20);
    }
}
