package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BorrowedConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
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
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BorrowedService {

    private final BorrowedRepository borrowedRepository;
    private final BorrowedConverter borrowedConverter;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

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

    public List<BorrowedDto> list(Integer personId) {
        List<BorrowedEntity> borrowedEntity = borrowedRepository.findAll();
        List<BorrowedDto> borrowedDto = new ArrayList<>();
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        for (int i = 0; i < borrowedEntity.size(); i++) {
            borrowedDto.add(borrowedConverter.borrowedEntityToBorrowedDto(borrowedEntity.get(i)));
        }

        // get book by id set dto book name


        return borrowedDto;
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

    public PersonDto saveBorrow(Integer bookId, String name) {
        PersonEntity personEntity = personRepository.findByName(name).orElse(null);

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

            return personConverter.personEntityToPersonDto(personRepository.save(personEntity));
        }
    }
}