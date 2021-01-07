package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.BorrowedRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BorrowedRepository borrowedRepository;


    @Transactional
    public PersonDto find(int id) {
        PersonEntity entity = personRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }
        return personConverter.personEntityToPersonDto(entity);
    }

    @Transactional
    public PersonDto create(PersonDto personDto) {
        PersonEntity personEntity = personConverter.personDtoToPersonEntity(personDto);
        PersonEntity savedPersonEntitiy = personRepository.save(personEntity);

        return personConverter.personEntityToPersonDto(savedPersonEntitiy);
    }

    @Transactional
    public PersonDto update(PersonDto personDto, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }
        personEntity.setAddress(personDto.getAddress());
        personEntity.setName(personDto.getName());
        personEntity.setEmail(personDto.getEmail());
        personEntity.setPhoneNumber(personDto.getPhoneNumber());

        return personConverter.personEntityToPersonDto(personRepository.save(personEntity));
    }

    @Transactional
    public void delete(Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }
        personRepository.delete(personEntity);
    }

    @Transactional
    public BorrowedEntity getBorrow(Integer bookId, Integer personId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(bookId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed... ");
        }
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person... ");
        }
        borrowedEntity.setPersonEntity(personEntity);

        return borrowedRepository.save(borrowedEntity);
    }
}
