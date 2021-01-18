package com.alevnarcin.librarymanagementsystem.service;

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
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class BorrowedService {

    private final BorrowedRepository borrowedRepository;
    private final BorrowedConverter borrowedConverter;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BorrowedDto find(Integer id) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(id).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedEntity);
    }

    public BorrowedDto create(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = borrowedConverter.borrowedDtoToBorrowedEntity(borrowedDto);
        BorrowedEntity savedBorrowedEntity = borrowedRepository.save(borrowedEntity);
        return borrowedConverter.borrowedEntityToBorrowedDto(savedBorrowedEntity);
    }

    public BorrowedDto update(BorrowedDto borrowedDto, Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }

        borrowedEntity.setBorrowStartDate(borrowedDto.getBorrowStartDate());
        borrowedEntity.setBorrowReturnDate(borrowedDto.getBorrowReturnDate());

        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedRepository.save(borrowedEntity));
    }

    public void delete(Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        borrowedRepository.delete(borrowedEntity);
    }

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

    public PersonEntity saveBorrow(Integer bookId, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);

        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }

        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);

        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }

        if (!bookEntity.getIsAvailable()) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        } else {
            BorrowedEntity borrowedEntity = new BorrowedEntity();
            borrowedEntity.setPersonEntity(personEntity);
            borrowedEntity.setBookEntity(bookEntity);
            borrowedEntity.setBorrowStartDate(LocalDate.now());
            borrowedEntity.setBorrowEndDate(LocalDate.now().plusDays(30));
            personEntity.getBorrowedEntities().add(borrowedEntity);
            bookEntity.setIsAvailable(false);

            bookRepository.save(bookEntity);

            return personRepository.save(personEntity);
        }
    }
}