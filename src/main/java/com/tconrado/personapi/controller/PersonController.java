package com.tconrado.personapi.controller;

import com.tconrado.personapi.dto.request.PersonDTO;
import com.tconrado.personapi.dto.response.MessageResponseDTO;
import com.tconrado.personapi.exception.PersonNotFoundException;
import com.tconrado.personapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "People", description = "Operation with people")
@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService service;

    @Operation(summary = "Create people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created people",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO person) {
        return service.createPerson(person);
    }

    @Operation(summary = "Get all people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the people",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) })
    })
    @GetMapping
    public List<PersonDTO> listAll() {
        return service.listAll();
    }

    @Operation(summary = "Get a people by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the people",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "People not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return service.findById(id);
    }

    @Operation(summary = "Update a people by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated people",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "People not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") Long id, @RequestBody PersonDTO dto)
            throws PersonNotFoundException {
        return service.update(id, dto);
    }

    @Operation(summary = "Delete a people by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted people",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "People not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws PersonNotFoundException {
        service.delete(id);
    }
}
