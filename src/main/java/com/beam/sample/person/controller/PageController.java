package com.beam.sample.person.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.beam.sample.person.service.AccountService.SESSION_ACCOUNT;

@Controller
public class PageController {

    @GetMapping(value = {"/", "/home", "/person", "/person/{id}"}, produces = {MediaType.TEXT_HTML_VALUE})
    public String home(HttpSession session) {
        if (session.getAttribute(SESSION_ACCOUNT) == null) {
            return "redirect:/login";
        } else {
            return "index";
        }
    }

    @GetMapping(value = {"/login"}, produces = {MediaType.TEXT_HTML_VALUE})

    public String login() {
        return "index";
    }
}
