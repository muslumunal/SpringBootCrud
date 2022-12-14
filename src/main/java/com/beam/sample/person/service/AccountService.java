package com.beam.sample.person.service;

import com.beam.sample.person.dto.AuthenticationResponse;
import com.beam.sample.person.model.Account;
import com.beam.sample.person.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    public static final String SESSION_ACCOUNT = "account";

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse login(String username, String password){

        Optional<Account> optionalAccount = accountRepository.findByUsername(username);

        if(optionalAccount.isEmpty()){
            return new AuthenticationResponse()
                    .setCode(11);
        }else{
            Account account = optionalAccount.get();
            if(passwordEncoder.matches(password, account.getPassword())){
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
