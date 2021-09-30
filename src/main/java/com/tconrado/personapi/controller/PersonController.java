package com.tconrado.personapi.controller;

import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO person) {
        return service.createPerson(person);
    }
}
