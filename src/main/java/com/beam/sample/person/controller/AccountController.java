package com.beam.sample.person.controller;

import com.beam.sample.person.dto.AuthenticationResponse;
import com.beam.sample.person.dto.AuthenticatonRequest;
import com.beam.sample.person.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static com.beam.sample.person.service.AccountService.SESSION_ACCOUNT;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    @PostMapping("login")
    @ResponseBody
    public AuthenticationResponse login(@RequestBody AuthenticatonRequest request, HttpSession session){
        AuthenticationResponse response = accountService.login(request.getUsername(), request.getPassword());

        if(response.getCode() == 0){
            session.setAttribute(SESSION_ACCOUNT, response.getAccount());
        }

        return response;
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute(SESSION_ACCOUNT);
        return "redirect:/login";
    }
}
