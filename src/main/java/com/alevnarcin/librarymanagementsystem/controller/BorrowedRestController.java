package com.alevnarcin.librarymanagementsystem.controller;


import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.service.BorrowedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/borrowed", produces = {"application/json"})
public class BorrowedRestController {

    private BorrowedService borrowedService;

    public BorrowedRestController(BorrowedService service) {

        this.borrowedService = Objects.requireNonNull(service);
    }

    @GetMapping("/{borrowedId}")
    public ResponseEntity<BorrowedDto> get(@PathVariable("borrowedId") Integer id) {
        try {
            BorrowedDto borrowedDto = borrowedService.find(id);
            return new ResponseEntity<>(borrowedDto, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", consumes={"application/json"})
    public ResponseEntity<BorrowedDto>create(@RequestBody BorrowedDto borrowedDto) {

        return new ResponseEntity<>(borrowedService.create(borrowedDto), HttpStatus.CREATED);
    }

    @PutMapping("/{borrowedId}")
    public ResponseEntity<BorrowedDto> update(@RequestBody BorrowedDto borrowedDto, @PathVariable("borrowedId") Integer borrowedId) {
        return new ResponseEntity<>(borrowedService.update(borrowedDto, borrowedId), HttpStatus.OK);
    }

    @DeleteMapping("/{borrowedId}")
    public ResponseEntity<Void> delete(@PathVariable("borrowedId") Integer borrowedId) {
        borrowedService.delete(borrowedId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //bookEntity&borrowedEntity
    @GetMapping("/get-book/{borrowedId}/{bookId}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("borrowedId") Integer borrowedId, @PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(borrowedService.getBook(borrowedId,bookId));
    }

    //personEntity&borrowedEntity
    @GetMapping("/get-person/{borrowedId}/{personId}")
    public ResponseEntity<PersonEntity> getPerson(@PathVariable("borrowedId") Integer borrowedId, @PathVariable("personId") Integer personId) {
        return ResponseEntity.ok(borrowedService.getPerson(borrowedId, personId));
    }

}
