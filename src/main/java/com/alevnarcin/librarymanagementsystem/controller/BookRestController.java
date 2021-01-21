package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.entity.*;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin(origins = "http://localhost:3000")                                             // bu class'ta tanımlı olan methodların return değerlerini Response'un body'sine yaz.
@RestController                                                                            // Response, browser'a dönülen HTTP mesajı oluyor.
@RequestMapping(value = "/books", produces = {"application/json"})                      // class seviyesindeki mapping bütün methodların adreslerinin önünü tanımlıyor.
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;                                               // parantez { içerisinde tanımlı olan değişkenler
                                                                                        // @PathVariable'ında annotate edilen parametre ile eşleştirilir.
    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> getAllBook(){

        if(bookService.findAll()==null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

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

    //bookEntity&authorEntity
    @GetMapping("/get-author/{bookId}/{authorId}")
    public ResponseEntity<AuthorEntity> getAuthor(@PathVariable("bookId") Integer bookId, @PathVariable("authorId") Integer authorId) {
        return ResponseEntity.ok(bookService.getAuthor(bookId, authorId));
    }

    //bookEntity&publisherEntity
    @GetMapping("/get-publisher/{bookId}/{publisherId}")
    public ResponseEntity<PublisherEntity> getPublisher(@PathVariable("bookId") Integer bookId, @PathVariable("publisherId") Integer publisherId) {
        return ResponseEntity.ok(bookService.getPublisher(bookId, publisherId));
    }

    @GetMapping("/get-borrowed/{bookId}/{borrowedId}")
    public ResponseEntity<BorrowedEntity> getBorrow(@PathVariable("bookId") Integer bookId, @PathVariable("borrowedId") Integer borrowedId) {
        return ResponseEntity.ok(bookService.getBorrow(bookId,borrowedId));
    }

}