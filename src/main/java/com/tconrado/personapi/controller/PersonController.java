package com.tconrado.personapi.controller;

import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.exception.PersonNotFoundException;
import com.tconrado.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO person) {
        return service.createPerson(person);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") Long id, @RequestBody PersonDTO dto)
            throws PersonNotFoundException {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws PersonNotFoundException {
        service.delete(id);
    }
}
