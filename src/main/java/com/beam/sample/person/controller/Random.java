package com.beam.sample.person.controller;

import com.beam.sample.person.model.Person;
import com.beam.sample.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Random {

    private final PersonRepository personRepository;

    @GetMapping(value = {"person/list"}, params={"new", "person"})
    public Person person(){
        return personRepository.empty();
    }
}
