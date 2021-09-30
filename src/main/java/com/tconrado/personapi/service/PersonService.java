package com.tconrado.personapi.service;

import com.tconrado.personapi.dto.mapper.PersonMapper;
import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.entity.Person;
import com.tconrado.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO createPerson(PersonDTO dto) {
        Person personToSave = personMapper.toModel(dto);

        Person newPerson = repository.save(personToSave);
        return MessageResponseDTO.builder()
                .message("Created person with ID " + newPerson.getId())
                .build();
    }
}
