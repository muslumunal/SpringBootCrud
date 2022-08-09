package com.beam.sample.person.repository;

import com.beam.sample.person.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void addAccount(){

        Account account = new Account()
                .setUsername("alie")
                .setPassword("veli");
        account.setId(UUID.randomUUID().toString());
        accountRepository.save(account);
    }


}
