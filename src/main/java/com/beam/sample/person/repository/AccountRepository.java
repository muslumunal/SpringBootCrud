package com.beam.sample.person.repository;

import com.beam.sample.person.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    boolean existsByUsernameAndPassword(String username, String password);

    Optional<Account> findByUsername(String username);
}
