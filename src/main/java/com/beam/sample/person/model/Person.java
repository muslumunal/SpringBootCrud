package com.beam.sample.person.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Person")
@TypeAlias("Person")
@Data
@Accessors(chain = true)
public class Person extends Base {

    public static final String IDENTIFIER = "E";

    private String firstName;

    private String lastName;

    private Integer age;

    private Gender gender;

    private Address address;

    private List<Address> addressList;

    @DBRef
    private Address addressE;

    @DBRef
    private List<Address> addressListE;

}
