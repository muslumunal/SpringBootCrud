package com.beam.sample.person.controller;

import com.beam.sample.person.model.Account;
import com.beam.sample.person.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import static com.beam.sample.person.service.AccountService.SESSION_ACCOUNT;

@Log4j2
@Controller
public class PageController {

    @GetMapping(value = {"/", "/home", "/person", "/person/{id}"}, produces = {MediaType.TEXT_HTML_VALUE})
    public String home(HttpSession session, Model model) {
        if (session.getAttribute(SESSION_ACCOUNT) == null) {
            return "redirect:/login";
        } else {
            Account account = (Account) session.getAttribute(SESSION_ACCOUNT);
            model.addAttribute("current_user", account);
            return "index";
        }
    }

    @GetMapping(value = {"/login"}, produces = {MediaType.TEXT_HTML_VALUE})

    public String login() {
        return "index";
    }

    @GetMapping("new-person")
    public String newPerson() {
        return "new-person";
    }

    @PostMapping("new-person")
    public String newPerson(Person person, @RequestPart MultipartFile file) {

        //log.info("Person added", firstName, lastName);

        log.info(person);

        return "redirect:/new-person";
    }
}
