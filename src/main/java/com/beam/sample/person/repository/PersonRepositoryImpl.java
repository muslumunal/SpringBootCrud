package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private final MongoOperations mongoOperations;

    @Override
    public Person empty() {
        return new Person()
                .setAge(25);
    }

    @Override
    public List<Person> complexQuery() {
        mongoOperations.createCollection("Random");
        return null;
    }


}
