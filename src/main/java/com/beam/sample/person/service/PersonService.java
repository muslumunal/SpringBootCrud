package com.beam.sample.person.service;

import com.beam.sample.person.model.Person;
import com.beam.sample.person.repository.AddressRepository;
import com.beam.sample.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public List<Person> list() {
        return personRepository.findAll();
    }

    public Page<Person> page(int page, int size) {
        return personRepository.findAll(PageRequest.of(page, size));
    }

    public Person findById(String id) {
        return personRepository.findById(id)
                .orElse(personRepository.empty());
    }

    public Person save(Person person) {
        if (person.getId() == null) {
            log.info("Person inserted");
            person.setId(UUID.randomUUID().toString());
            return personRepository.insert(person);
        } else {
            log.info("Person updated");
            return personRepository.save(person);
        }
    }

    public void remove(String id) {
        log.info("Person removed. [Id: " + id + "]");
       personRepository.deleteById(id);
    }
}
