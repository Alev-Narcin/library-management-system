package com.alevnarcin.librarymanagementsystem.controller;


import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.service.LoginServices;
import com.alevnarcin.librarymanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/persons", produces = {"application/json"})
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;
    private final LoginServices loginServices;

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

    @GetMapping("/login/{password}/{name}")
    public ResponseEntity<String> login(@PathVariable("password") String password, @PathVariable("name") String name){
        String login = loginServices.Login(password,name);
        return new ResponseEntity<>(login,HttpStatus.OK);
    }


}
