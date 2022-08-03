package com.beam.sample.person.repository;
import com.beam.sample.person.model.Address;
import com.beam.sample.person.model.Gender;
import com.beam.sample.person.model.Person;
import com.mongodb.client.result.DeleteResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Person getMePerson() {
        return new Person()
                .setAge(20)
                .setFirstName("MÜslüm")
                .setLastName("Ünal")
                .setGender(Gender.MALE);
    }


    @Test
    public void insertPerson() {
        insertPersonWithId(UUID.randomUUID().toString());
    }

    public void insertPersonWithId(String id) {

        Person person = getMePerson();

        person.setId(id);

        personRepository.save(person);

    }

    @Test
    public void findAllPerson() {
        List<Person> p = personRepository.findAll();
        for (Person v : p) {
            System.out.println(v.getFirstName());
        }
    }

    @Test
    public void findByIdPerson() {
        Optional<Person> p = personRepository.findById("bde3d181-7fe6-439e-8e2d-ae2a32f522ea");

        if (p.isPresent()) {
            System.out.println(p.get().getAge());
        }
    }

    @Test
    public void deletePersonById() {
        insertPersonWithId("bde3d181-7fe6-439e-8e2d-ae2a32f522ea");
        DeleteResult deleteResult = mongoTemplate.remove(Query.query(Criteria.where("_id").is("bde3d181-7fe6-439e-8e2d-ae2a32f522ea")), "Person");
        Assertions.assertEquals(1, deleteResult.getDeletedCount());
        System.out.println(deleteResult);
    }

    @Test
    public void updatePerson() {
        Person p = personRepository.findById("66dd0942-a34d-4041-b4df-99ef234268d1").orElse(null);
        p.setFirstName("Kerem");
        personRepository.save(p);
    }

    @Test
    public void findAllWithSort() {
        List<Person> people = personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        for (Person person : people) {
            System.out.println(person.getFirstName());
        }
    }

    @Test
    public void randomCall() {
        Person example = new Person()
                .setGender(Gender.MALE)
                .setLastName("Ünal");

        List<Person> people = personRepository.findAll(Example.of(example));

        people = personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));

        Page<Person> page = personRepository.findAll(PageRequest.of(1, 2));

        people = personRepository.findByAgeAndLastName(20, "veli");
        people = personRepository.sameAsAbove(20, "veli");

        personRepository.empty();

        //personRepository.complexQuery();

    }

    @Test
    public void nestRelation() {
        Person p = getMePerson();
        p.setId(UUID.randomUUID().toString());
        p.setFirstName("Adddr");

        p.setAddress(new Address()
                .setCity("Ankara"));



        p.setAddressList(Arrays.asList(
                new Address()
                        .setCity("Ankara"),
                new Address()
                        .setCity("Istanbul")));

        personRepository.save(p);
    }

    @Test
    public void referRelation(){

        Person p = getMePerson();
        p.setId(UUID.randomUUID().toString());
        p.setFirstName("Refer");

        Address a1 = new Address()
                .setCity("Ankara");
        a1.setId(UUID.randomUUID().toString());

        Address a2 = new Address()
                .setCity("Istanbul");
        a2.setId(UUID.randomUUID().toString());

        addressRepository.saveAll(Arrays.asList(a1, a2));

        p.setAddressE(a1);
        p.setAddressListE(Arrays.asList(a1, a2));

        personRepository.save(p);

    }

}
