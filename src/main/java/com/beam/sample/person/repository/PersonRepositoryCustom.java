package com.beam.sample.person.repository;

import com.beam.sample.person.model.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    Person empty();

    List<Person> complexQuery();
}
