package com.alevnarcin.librarymanagementsystem.controller;

import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.dto.PublisherDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/publishers", produces = {"application/json"})
@RequiredArgsConstructor
public class PublisherRestController {

    private final PublisherService publisherService;

    @GetMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> get(@PathVariable("publisherId") int id) {

        try {
            PublisherDto publisherDto =publisherService.find(id);
            return new ResponseEntity<>(publisherDto, HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<PublisherDto> create(@RequestBody PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.create(publisherDto), HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> update(@RequestBody PublisherDto publisherDto, @PathVariable("publisherId") Integer publisherId) {
        return new ResponseEntity<>(publisherService.update(publisherDto, publisherId), HttpStatus.OK);
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<Void> delete(@PathVariable("publisherId") Integer publisherId) {
        publisherService.delete(publisherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-book/{publisherId}/{bookId}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("publisherId") Integer publisherId, @PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(publisherService.getBook(publisherId, bookId));
    }


}
