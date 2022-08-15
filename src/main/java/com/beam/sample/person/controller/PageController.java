package com.beam.sample.person.controller;

import com.beam.sample.person.model.Account;
import com.beam.sample.person.model.Person;
import com.beam.sample.person.service.DiskService;
import com.beam.sample.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.beam.sample.person.service.AccountService.SESSION_ACCOUNT;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PageController {

    private final PersonService personService;
    private final DiskService diskService;

    @GetMapping(value = {"/", "/home", "/person", "/person/{id}", "/personb64/{id}", "/person-multi/{id}"}, produces = {MediaType.TEXT_HTML_VALUE})
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

        personService.save(person, file);

        log.info(person);

        return "redirect:/new-person";
    }

    @GetMapping("/img/{filename}")
    public ResponseEntity<byte[]> image(@PathVariable String filename) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity(diskService.read(filename), headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.of(null);
        }
    }
}
