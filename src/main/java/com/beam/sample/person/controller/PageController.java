package com.beam.sample.person.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = {"/", "/index", "/about", "/person", "/person/{id}"}, produces = {MediaType.TEXT_HTML_VALUE})
    public String index(){
        return "index";
    }
}
