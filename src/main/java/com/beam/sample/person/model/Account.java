package com.beam.sample.person.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
@TypeAlias("Address")
@Data
@Accessors(chain = true)
public class Account extends Base {

    private String username;
    private String password;

}
