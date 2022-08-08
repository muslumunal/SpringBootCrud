package com.beam.sample.person.controller;


import com.beam.sample.person.model.Person;
import com.beam.sample.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("list")
    public List<Person> list() {
        return personService.list();
    }

    @GetMapping(value = "list", params = {"s"})
    public Page<Person> page(@RequestParam(required = false, defaultValue = "0") int page,
                             @RequestParam(value = "s") int size) {
        return personService.page(page, size);
    }

    @GetMapping("{id}")
    public Person read(@PathVariable String id){
        return personService.findById(id);
    }

    @PostMapping("persist")
    public Person persist(@RequestBody Person person){
        return personService.save(person);
    }

    @DeleteMapping("remove")
    public void remove(@RequestParam String id){
        personService.remove(id);
    }

}
