package com.tconrado.personapi.service;

import com.tconrado.personapi.dto.MessageResponseDTO;
import com.tconrado.personapi.entity.Person;
import com.tconrado.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person newPerson = repository.save(person);
        return MessageResponseDTO.builder()
                .message("Created person with ID " + newPerson.getId())
                .build();
    }
}
