package com.beam.sample.person.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
@TypeAlias("Account")
@Data
@Accessors(chain = true)
public class Account extends Base {

    private String username;
    private String password;

}
