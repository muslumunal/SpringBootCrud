package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

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


    public UpdateResult updatePeople(int current, int n) {
        return mongoOperations.updateMulti(
                query(where("age").is(Integer.valueOf(current))),
                update("age", Integer.valueOf(n)),
                Person.class);
    }

}
