package com.tconrado.personapi.service;

import com.tconrado.personapi.dto.mapper.PersonMapper;
import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.entity.Person;
import com.tconrado.personapi.repository.PersonRepository;
import com.tconrado.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonService service;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        PersonDTO dto = PersonUtils.createFakeDTO();
        Person savedPerson = PersonUtils.createFakeEntity();

        //Mockito.when(mapper.toModel(dto)).thenReturn((savedPerson));
        Mockito.when(repository.save(Mockito.any(Person.class))).thenReturn(savedPerson);

        MessageResponseDTO expectedMessage = messageResponse(savedPerson.getId());
        MessageResponseDTO successMessage = service.createPerson(dto);

        Assertions.assertEquals(expectedMessage, successMessage);

    }

    private MessageResponseDTO messageResponse(Long id) {
        return MessageResponseDTO.builder()
                .message("Created Person with ID " + id)
                .build();
    }
}