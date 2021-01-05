package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.*;
import com.alevnarcin.librarymanagementsystem.repository.*;
import com.alevnarcin.librarymanagementsystem.validation.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private PersonService personService;
    private final AuthorRepository authorRepository;
    private final BorrowedRepository borrowedRepository;
    private final PublisherRepository publisherRepository;


    @Autowired
    public BookService setPersonService(PersonService personService) {
        this.personService = personService;
        return this;
    }

    public BookDto find(int id) {
        BookEntity entity = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return bookConverter.bookEntityToBookDto(entity);
    }

    public BookDto create(BookDto bookDto) {
        BookValidation.validateBook(bookDto);
        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookDto);  // 1. convert dto to entity
        BookEntity savedBookEntity = bookRepository.save(bookEntity);       // 2. save entity to database
        return bookConverter.bookEntityToBookDto(savedBookEntity);       // 3. convert entity to dto and return bookDto
    }

    public BookDto update(BookDto bookDto, Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);

        bookEntity.setName(bookDto.getName());
        bookEntity.setSupplyType(bookDto.getSupplyType());
        bookEntity.setStock(bookDto.getStock());

        return bookConverter.bookEntityToBookDto(bookRepository.save(bookEntity));
    }

    public void delete(Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        bookRepository.delete(bookEntity);
    }


    //RELATİON -> bookEntity&authorEntity
    public AuthorEntity getAuthor(Integer authorId, Integer bookId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        authorEntity.getBookEntities().add(bookEntity);
        bookEntity.getAuthorEntities().add(authorEntity);

        return authorRepository.save(authorEntity);
    }

    //RELATİON -> bookEntity&publisherEntity
    public PublisherEntity getPublisher(Integer publisherId, Integer bookId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        publisherEntity.getBookEntities().add(bookEntity);
        bookEntity.getPublisherEntities().add(publisherEntity);

        return publisherRepository.save(publisherEntity);
    }


    //RELATION -> bookEntity&borrowedEntity
    public BorrowedEntity getBorrow(Integer borrowedId, Integer bookId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        borrowedEntity.setBookEntity(bookEntity);

        return borrowedRepository.save(borrowedEntity);
    }


}
