package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/persons", produces = {"application/json"})
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {

        this.personService = Objects.requireNonNull(personService);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> get(@PathVariable("personId") int id) {
        try {
            PersonDto personDto = personService.find(id);
            return new ResponseEntity<>(personDto, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.create(personDto), HttpStatus.CREATED);
    }


}
