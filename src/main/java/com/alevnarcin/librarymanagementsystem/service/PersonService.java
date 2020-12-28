package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BookService bookService;
    private final BookConverter bookConverter;

    public PersonDto find(int id) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(NoSuchElementException::new);
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

    public PersonDto getBook(Integer personId, Integer bookId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookService.find(bookId));
        personEntity.getBookEntities().add(bookEntity);
        return personConverter.personEntityToPersonDto(personRepository.save(personEntity));
    }
}
