package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController    // bu class'ta tanımlı olan methodların return değerlerini Response'un body'sine yaz.
                   // Response, browser'a dönülen HTTP mesajı oluyor.
@RequestMapping(value = "/books", produces = {"application/json"})     // class seviyesindeki mapping bütün methodların adreslerinin önünü tanımlıyor.
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService service) {

        this.bookService = Objects.requireNonNull(service);
    }

    // parantez { içerisinde tanımlı olan değişkenler
    // @PathVariable'ında annotate edilen parametre ile eşleştirilir.
    // yani method çağrılırken parametrenin değeri adres satırında karşılık gelen yerdeki değerdir.

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> get(@PathVariable("bookId") Integer id) {
        try {
            BookDto bookDto = bookService.find(id);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", consumes={"application/json"})
    public ResponseEntity<BookDto>create(@RequestBody BookDto bookDto) {

        return new ResponseEntity<>(bookService.create(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto, @PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(bookService.update(bookDto, bookId), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable("bookId") Integer bookId) {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /*//personEntity&bookEntity
    @GetMapping("/get-person/{bookId}/{personId}")
    public ResponseEntity<PersonEntity> getPerson(@PathVariable("bookId") Integer bookId, @PathVariable("personId") Integer personId) {
        return ResponseEntity.ok(bookService.getPerson(bookId,personId));
    }*/

    //bookEntity&authorEntity
    @GetMapping("/get-author/{bookId}/{authorId}")
    public ResponseEntity<AuthorEntity> getAuthor(@PathVariable("bookId") Integer bookId, @PathVariable("authorId") Integer authorId) {
        return ResponseEntity.ok(bookService.getAuthor(bookId, authorId));
    }

    @GetMapping("/get-borrowed/{bookId}/{borrowedId}")
    public ResponseEntity<BorrowedEntity> getBorrow(@PathVariable("bookId") Integer bookId, @PathVariable("borrowedId") Integer borrowedId) {
        return ResponseEntity.ok(bookService.getBorrow(bookId,borrowedId));
    }

}