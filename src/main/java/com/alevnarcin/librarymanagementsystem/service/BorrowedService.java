package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BorrowedConverter;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.BorrowedRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BorrowedService {

    private final BorrowedRepository borrowedRepository;
    private final BorrowedConverter borrowedConverter;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BorrowedDto find(Integer id) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(id).orElseThrow(() -> new RuntimeException("cannot find borrow id"));
        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedEntity);
    }

    public BorrowedDto create(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = borrowedConverter.borrowedDtoToBorrowedEntity(borrowedDto);
        BorrowedEntity savedBorrowedEntity = borrowedRepository.save(borrowedEntity);
        return borrowedConverter.borrowedEntityToBorrowedDto(savedBorrowedEntity);
    }

    public BorrowedDto update(BorrowedDto borrowedDto, Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElseThrow(NoSuchElementException::new);

        borrowedEntity.setId(borrowedDto.getId());
        borrowedEntity.setBorrowStartDate(borrowedDto.getBorrowStartDate());
        //borrowedEntity.setBorrowEndDate(borrowedDto.getBorrowEndDate());
        //borrowedEntity.setBorrowReturnDate(borrowedDto.getBorrowReturnDate());

        return borrowedConverter.borrowedEntityToBorrowedDto(borrowedRepository.save(borrowedEntity));
    }

    public void delete(Integer borrowedId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(borrowedId).orElseThrow(NoSuchElementException::new);
        borrowedRepository.delete(borrowedEntity);
    }

    public BookEntity getBook(Integer borrowedId, Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        BorrowedEntity borrowedEntity = new BorrowedEntity();
        borrowedEntity.setBookEntity(bookEntity);
        borrowedEntity.setBorrowStartDate(LocalDate.now());
        borrowedEntity.setBorrowEndDate(LocalDate.now().plusDays(30));

        return bookRepository.save(bookEntity);
    }

    public PersonEntity getBorrow(Integer bookId, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        BorrowedEntity borrowedEntity = new BorrowedEntity();

        borrowedEntity.setPersonEntity(personEntity);
        borrowedEntity.setBookEntity(bookEntity);
        borrowedEntity.setBorrowStartDate(LocalDate.now());
        borrowedEntity.setBorrowEndDate(LocalDate.now().plusDays(30));

        personEntity.getBorrowedEntities().add(borrowedEntity);

        return personRepository.save(personEntity);
    }

}