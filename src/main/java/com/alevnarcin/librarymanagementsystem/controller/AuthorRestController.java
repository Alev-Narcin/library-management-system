package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/authors", produces = {"application/json"})
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> get(@PathVariable("authorId") int id) {
        try {
            AuthorDto authorDto =authorService.find(id);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {

        return new ResponseEntity<>(authorService.create(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto, @PathVariable("authorId") Integer authorId) {
        return new ResponseEntity<>(authorService.update(authorDto, authorId), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> delete(@PathVariable("authorId") Integer authorId) {
        authorService.delete(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-book/{authorId}/{bookId}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("authorId") Integer authorId, @PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(authorService.getBook(authorId, bookId));
    }
}
