package com.beam.sample.person.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
@TypeAlias("Address")
@Data
@Accessors(chain = true)
public class Address extends Base {

    private String city;
}
