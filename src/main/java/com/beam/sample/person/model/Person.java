package com.beam.sample.person.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Person")
@TypeAlias("Person")
@Data
@Accessors(chain = true)
public class Person {

    @Id
    private String id;
    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

}
