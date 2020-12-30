package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonConverter personConverter, BookRepository bookRepository, BookConverter bookConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public PersonDto find(int id) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return personConverter.personEntityToPersonDto(entity);
    }


    @Transactional
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


    public PersonEntity getBook(Integer personId, Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        personEntity.getBookEntities().add(bookEntity);

        return  personRepository.save(personEntity);
    }

}
