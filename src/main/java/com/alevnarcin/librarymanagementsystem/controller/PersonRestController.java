package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/persons", produces = {"application/json"})
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<PersonDto>> getAllPerson(){

        if(personService.findAllPerson()==null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(personService.findAllPerson(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> get(@PathVariable("personId") int id) {
        PersonDto personDto = personService.find(id);
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.create(personDto), HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto personDto, @PathVariable("personId") Integer personId) {
        return new ResponseEntity<>(personService.update(personDto, personId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable("personId") Integer personId) {
        personService.delete(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-borrowed/{personId}/{bookId}")
    public ResponseEntity<BorrowedEntity> getBorrow(@PathVariable("personId") Integer personId, @PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(personService.getBorrow(personId, bookId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> personDto = personService.findAll();
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

}
