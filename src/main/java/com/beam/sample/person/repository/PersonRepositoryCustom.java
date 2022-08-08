package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface PersonRepositoryCustom {

    Person empty();

    List<Person> complexQuery();

    UpdateResult updatePeople(int current, int n);
}
