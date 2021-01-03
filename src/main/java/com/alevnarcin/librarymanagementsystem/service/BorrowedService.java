package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.BorrowedConverter;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.BorrowedRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BorrowedService {

    private final BorrowedRepository borrowedRepository;
    private final BorrowedConverter borrowedConverter;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private BookService bookService;
    private final PersonRepository personRepository;

    public BorrowedService(PersonRepository personRepository,BorrowedRepository borrowedRepository, BorrowedConverter borrowedConverter, BookRepository bookRepository, BookConverter bookConverter) {
        this.borrowedRepository = borrowedRepository;
        this.borrowedConverter = borrowedConverter;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.personRepository = personRepository;
    }

    public BorrowedDto find(Integer id){
        BorrowedEntity borrowedEntity = borrowedRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedEntity);
    }

    public BorrowedDto create(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = borrowedConverter.borrowedDtoToBorrowedEntity(borrowedDto);
        BorrowedEntity savedBorrowedEntity = borrowedRepository.save(borrowedEntity);
        return borrowedConverter.borrowedEntityToBorrowedDto(savedBorrowedEntity);
    }

    public BorrowedDto update(BorrowedDto borrowedDto, Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElseThrow(NoSuchElementException::new);

        borrowedEntity.setBorrowanceDate(borrowedDto.getBorrowanceDate());
        borrowedEntity.setReturnDate(borrowedDto.getReturnDate());
        borrowedEntity.setId(borrowedDto.getId());

        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedRepository.save(borrowedEntity));
    }

    public void delete(Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElseThrow(NoSuchElementException::new);
        borrowedRepository.delete(borrowedEntity);
    }

    public BookEntity getBook(Integer borrowedId, Integer bookId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        bookEntity.setBorrowedEntity(borrowedEntity);

        return bookRepository.save(bookEntity);
    }

    public PersonEntity getPerson(Integer borrowedId, Integer personId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        personEntity.setBorrowedEntity(borrowedEntity);

        return personRepository.save(personEntity);

    }

}