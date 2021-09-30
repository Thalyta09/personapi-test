package com.tconrado.personapi.service;

import com.tconrado.personapi.dto.mapper.PersonMapper;
import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.entity.Person;
import com.tconrado.personapi.exception.PersonNotFoundException;
import com.tconrado.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository repository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO dto) {
        Person personToSave = personMapper.toModel(dto);

        Person newPerson = repository.save(personToSave);
        return messageResponse(newPerson.getId(), "Created Person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPerson = repository.findAll();
        return allPerson.stream().map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO update(Long id, PersonDTO dto) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(dto);

        Person updatedPerson = repository.save(personToUpdate);
        return messageResponse(updatedPerson.getId(), "Updated Person with ID ");
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO messageResponse(Long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}
