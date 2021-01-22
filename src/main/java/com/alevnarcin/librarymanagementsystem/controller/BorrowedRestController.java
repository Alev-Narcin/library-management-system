package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.service.BorrowedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping(value = "/borrowed", produces = {"application/json"})
@RequiredArgsConstructor
public class BorrowedRestController {

    private final BorrowedService borrowedService;

    @GetMapping("/{borrowedId}")
    public ResponseEntity<BorrowedDto> get(@PathVariable("borrowedId") Integer id) {
        try {
            BorrowedDto borrowedDto = borrowedService.find(id);
            return new ResponseEntity<>(borrowedDto, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
    @GetMapping("/get-book/{bookId}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(borrowedService.getBook(bookId));
    }
    @GetMapping("/get-borrows/{personId}")
    public ResponseEntity<List<BorrowedDto>> getBorrows(@PathVariable("personId") Integer personId) {
        return ResponseEntity.ok(borrowedService.list(personId));
    }

    //personEntity&bookEntity
    @GetMapping("/get-borrow/{bookId}/{name}")
    public ResponseEntity<PersonDto> getBorrow(@PathVariable("bookId") Integer bookId , @PathVariable("name") String name) {
        return ResponseEntity.ok(borrowedService.saveBorrow(bookId,name));
    }

}
