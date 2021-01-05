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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
//@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final BorrowedRepository borrowedRepository;

    @Autowired
    public PersonService(BorrowedRepository borrowedRepository, PersonRepository personRepository, PersonConverter personConverter, BookRepository bookRepository, BookConverter bookConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.borrowedRepository = borrowedRepository;
    }

    public PersonDto find(int id) {
        PersonEntity entity = personRepository.findById(id).orElse(null);

        if (entity == null) {
            throw new ExceptionResponse("Hata var. Düzgün istek at kardeşim");
        }

        return personConverter.personEntityToPersonDto(entity);
    }


    public PersonDto create(PersonDto personDto) {

        PersonEntity personEntity = personConverter.personDtoToPersonEntity(personDto);
        PersonEntity savedPersonEntitiy = personRepository.save(personEntity);

        return personConverter.personEntityToPersonDto(savedPersonEntitiy);

    }

    public PersonDto update(PersonDto personDto, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(NoSuchElementException::new);

        personEntity.setAddress(personDto.getAddress());
        personEntity.setName(personDto.getName());
        personEntity.setEmail(personDto.getEmail());
        personEntity.setPhoneNumber(personDto.getPhoneNumber());

        return personConverter.personEntityToPersonDto(personRepository.save(personEntity));
    }

    public void delete(Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(NoSuchElementException::new);
        personRepository.delete(personEntity);
    }


    public BorrowedEntity getBorrow(Integer bookId, Integer personId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(NoSuchElementException::new);
        borrowedEntity.setPersonEntity(personEntity);

        return borrowedRepository.save(borrowedEntity);
    }

}
