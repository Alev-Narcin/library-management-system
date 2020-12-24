package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController // bu class'ta tanımlı olan methodların return değerlerini Response'un body'sine yaz.
// Response, browser'a dönülen HTTP mesajı oluyor.
@RequestMapping(value = "/books", produces = {"application/json"})
// class seviyesindeki mapping bütün methodların adreslerinin önünü tanımlıyor.
public class BookRestController {

    private final BookService bookService;
    private int id;

    public BookRestController(BookService service) {

        this.bookService = Objects.requireNonNull(service);
    }

    // parantez { içerisinde tanımlı olan değişkenler
    // @PathVariable'ında annotate edilen parametre ile eşleştirilir.
    // yani method çağrılırken parametrenin değeri adres satırında karşılık gelen yerdeki değerdir.
    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("book_id") int id) {
        try {
            BookDto bookDto = bookService.find(id);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", consumes = {"application/json"})
    public  ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.create(bookDto), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{book_id}")
    public  ResponseEntity<BookDto> update(BookDto bookDto){
        return new ResponseEntity<>(bookService.update(bookDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<BookDto> delete(@PathVariable("book_id") int id){
        BookDto bookDto = bookService.find(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }

}