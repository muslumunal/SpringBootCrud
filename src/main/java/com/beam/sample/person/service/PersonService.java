package com.beam.sample.person.service;

import com.beam.sample.person.model.Person;
import com.beam.sample.person.repository.AddressRepository;
import com.beam.sample.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final DiskService diskService;

    public List<Person> list() {
        return personRepository.findAll();
    }

    public Page<Person> page(int page, int size) {
        return personRepository.findAll(PageRequest.of(page, size));
    }

    public Person findById(String id) {
        return personRepository.findById(id)
                .orElse(personRepository.empty());
    }

    public Person save(Person person) {

        if (person.getAvatar() != null && person.getAvatar().startsWith("data:image")) {
            String ext = person.getAvatar().substring(person.getAvatar().indexOf("/") + 1, person.getAvatar().indexOf(";"));
            byte[] data = Base64.getDecoder().decode(person.getAvatar().substring(person.getAvatar().indexOf(",") + 1) );
            try {
                person.setAvatar(diskService.write(data, ext));
            } catch (IOException e) {
                person.setAvatar(null);
                e.printStackTrace();
            }
        }

        if (person.getId() == null) {
            log.info("Person inserted");
            person.setId(UUID.randomUUID().toString());
            return personRepository.insert(person);
        } else {
            log.info("Person updated");
            return personRepository.save(person);
        }
    }

    public void remove(String id) {
        log.info("Person removed. [Id: " + id + "]");
        personRepository.deleteById(id);
    }

    public void save(Person person, MultipartFile file) {

        String filename = null;

        try {
            filename = diskService.write(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        person.setAvatar(filename);

        personRepository.save(person);


    }
}
