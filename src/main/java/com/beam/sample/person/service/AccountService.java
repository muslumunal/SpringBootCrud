package com.beam.sample.person.service;

import com.beam.sample.person.dto.AuthenticationResponse;
import com.beam.sample.person.model.Account;
import com.beam.sample.person.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    public static final String SESSION_ACCOUNT = "session.account";

    private final AccountRepository accountRepository;


    public AuthenticationResponse login(String username, String password){

        Optional<Account> optionalAccount = accountRepository.findByUsername(username);

        if(optionalAccount.isEmpty()){
            return new AuthenticationResponse()
                    .setCode(11);
        }else{
            Account account = optionalAccount.get();
            if(account.getPassword().equals(password)){
                return new AuthenticationResponse()
                        .setAccount(account)
                        .setCode(0);
            }else{
                return new AuthenticationResponse()
                        .setCode(10);
            }
        }

    }
}
