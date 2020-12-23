package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.model.Book;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController // bu class'ta tanımlı olan methodların return değerlerini Response'un body'sine yaz.
// Response browser'a dönülen HTTP mesajı oluyor.
@RequestMapping(value = "/books", produces = {"application/json"})
// class seviyesindeki mapping bütün methodların adreslerinin önünü tanımlıyor.
public class BookRestController {

    private final BookService service;

    public BookRestController(BookService service) {
        this.service = Objects.requireNonNull(service);
    }


    @GetMapping("/{book_id}")
    // parantez { içerisinde tanımlı olan değişkenler
    // @PathVariable'ında annotate edilen parametre ile eşleştirilir.
    // yani method çağrılırken parametrenin değeri adres satırında karşılık gelen yerdeki değerdir.
    public ResponseEntity<Book> getBook(@PathVariable("book_id") int id) {
        try {
            Book book = service.find(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", consumes = {"application/json"})
    public void create(@RequestBody Book book) {
        service.create(book);
    }

}
