package com.beam.sample.person.repository;
import com.beam.sample.person.model.Gender;
import com.beam.sample.person.model.Person;
import com.mongodb.client.result.DeleteResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void insertPerson(){
        insertPersonWithId(UUID.randomUUID().toString());
    }
    public void insertPersonWithId(String id){

        Person person = new Person()
                .setAge(20)
                .setFirstName("MÜslüm")
                .setLastName("Ünal")
                .setGender(Gender.MALE);

        person.setId(id);

        personRepository.save(person);

    }

    @Test
    public void findAllPerson(){
       List<Person> p = personRepository.findAll();
       for(Person v : p){
           System.out.println(v.getFirstName());
       }
    }

    @Test
    public void findByIdPerson(){
        Person p = personRepository.findById("bde3d181-7fe6-439e-8e2d-ae2a32f522ea").orElse(null);
        System.out.println(p.getAge());
    }

    @Test
    public void deletePersonById(){
        insertPersonWithId("bde3d181-7fe6-439e-8e2d-ae2a32f522ea");
        DeleteResult deleteResult = mongoTemplate.remove(Query.query(Criteria.where("_id").is("bde3d181-7fe6-439e-8e2d-ae2a32f522ea")),"Person");
        assertEquals(1,deleteResult.getDeletedCount());
        System.out.println(deleteResult);
    }

    @Test
    public void updatePerson(){
        Person p = personRepository.findById("66dd0942-a34d-4041-b4df-99ef234268d1").orElse(null);
        p.setFirstName("Kerem");
        personRepository.save(p);
    }

    @Test
    public void findAllWithSort()
    {
        List<Person> people = personRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        for(Person person: people){
            System.out.println(person.getFirstName());
        }
    }

}
