package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.BorrowedConverter;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.BorrowedRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BorrowedService {

    private final BorrowedRepository borrowedRepository;
    private final BorrowedConverter borrowedConverter;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Transactional
    public BorrowedDto find(Integer id) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(id).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedEntity);
    }

    @Transactional
    public BorrowedDto create(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = borrowedConverter.borrowedDtoToBorrowedEntity(borrowedDto);
        BorrowedEntity savedBorrowedEntity = borrowedRepository.save(borrowedEntity);

        return borrowedConverter.borrowedEntityToBorrowedDto(savedBorrowedEntity);
    }

    @Transactional
    public BorrowedDto update(BorrowedDto borrowedDto, Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }

        borrowedEntity.setBorrowReturnDate(borrowedDto.getBorrowReturnDate());

        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedRepository.save(borrowedEntity));
    }

    @Transactional
    public void delete(Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        borrowedRepository.delete(borrowedEntity);
    }

    @Transactional
    public BookEntity getBook(Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        BorrowedEntity borrowedEntity = new BorrowedEntity();
        borrowedEntity.setBookEntity(bookEntity);
        borrowedEntity.setBorrowStartDate(LocalDate.now());
        borrowedEntity.setBorrowEndDate(LocalDate.now().plusDays(30));

        return bookRepository.save(bookEntity);
    }

    @Transactional
    public PersonEntity getBorrow(Integer bookId, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        BorrowedEntity borrowedEntity = new BorrowedEntity();
        borrowedEntity.setPersonEntity(personEntity);
        borrowedEntity.setBookEntity(bookEntity);
        borrowedEntity.setBorrowStartDate(LocalDate.now());
        borrowedEntity.setBorrowEndDate(LocalDate.now().plusDays(30));

        personEntity.getBorrowedEntities().add(borrowedEntity);

        return personRepository.save(personEntity);
    }

}