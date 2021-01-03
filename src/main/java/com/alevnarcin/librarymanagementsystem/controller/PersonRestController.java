package com.alevnarcin.librarymanagementsystem.controller;


import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.service.PersonService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/persons", produces = {"application/json"})
public class PersonRestController {

    private  PersonService personService;


    public PersonRestController(PersonService service) {

        this.personService = Objects.requireNonNull(service);
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

    @ResponseStatus(HttpStatus.CREATED)
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

    /*@GetMapping("/get-book/{personId}/{bookId}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("personId") Integer personId, @PathVariable("bookId") Integer bookId) {
        return  ResponseEntity.ok(personService.getBook(personId,bookId));
    }*/


    @GetMapping("/get-borrowed/{personId}/{borrowedId}")
    public ResponseEntity<BorrowedEntity> getBorrow(@PathVariable("personId") Integer personId, @PathVariable("borrowedId") Integer borrowedId) {
        return ResponseEntity.ok(personService.getBorrow(personId,borrowedId));
    }


}
