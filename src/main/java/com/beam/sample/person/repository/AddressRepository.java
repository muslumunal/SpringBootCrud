package com.beam.sample.person.repository;

import com.beam.sample.person.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
