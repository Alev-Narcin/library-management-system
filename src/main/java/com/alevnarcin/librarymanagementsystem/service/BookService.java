package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.*;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.*;
import com.alevnarcin.librarymanagementsystem.validation.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final AuthorRepository authorRepository;
    private final BorrowedRepository borrowedRepository;
    private final PublisherRepository publisherRepository;


    @Autowired
    public BookService setPersonService(PersonService personService) {
        return this;
    }

    public BookDto findAll(){
        BookEntity bookEntity = (BookEntity) bookRepository.findAll();
        return bookConverter.bookEntityToBookDto(bookEntity);
    }

    public BookDto find(int id) {
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        return bookConverter.bookEntityToBookDto(entity);
    }

    public BookDto create(BookDto bookDto) {
        BookValidation.validateBook(bookDto);
        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookDto);  // 1. convert dto to entity
        BookEntity savedBookEntity = bookRepository.save(bookEntity);       // 2. save entity to database

        return bookConverter.bookEntityToBookDto(savedBookEntity);          // 3. convert entity to dto and return bookDto
    }

    public BookDto update(BookDto bookDto, Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        bookEntity.setName(bookDto.getName());
        bookEntity.setSupplyType(bookDto.getSupplyType());
        bookEntity.setType(bookDto.getType());
        bookEntity.setIsAvailable(bookDto.getIsAvailable());

        return bookConverter.bookEntityToBookDto(bookRepository.save(bookEntity));
    }

    public void delete(Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        bookRepository.delete(bookEntity);
    }


    //RELATİON -> bookEntity&authorEntity
    public AuthorEntity getAuthor(Integer authorId, Integer bookId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElse(null);
        if (authorEntity == null) {
            throw new ExceptionResponse("Oopps cannot find author... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        authorEntity.getBookEntities().add(bookEntity);
        bookEntity.getAuthorEntities().add(authorEntity);

        return authorRepository.save(authorEntity);
    }

    //RELATİON -> bookEntity&publisherEntity
    public PublisherEntity getPublisher(Integer publisherId, Integer bookId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElse(null);
        if (publisherEntity == null) {
            throw new ExceptionResponse("Oopps cannot find publisher... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        publisherEntity.getBookEntities().add(bookEntity);
        bookEntity.getPublisherEntities().add(publisherEntity);

        return publisherRepository.save(publisherEntity);
    }

    //RELATION -> bookEntity&borrowedEntity
    public BorrowedEntity getBorrow(Integer borrowedId, Integer bookId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        borrowedEntity.setBookEntity(bookEntity);

        return borrowedRepository.save(borrowedEntity);
    }


}
