package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.PersonConverter;
import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.BorrowedRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final BorrowedRepository borrowedRepository;

    public List<PersonDto> findAllPerson(){
        List<PersonEntity> allPersons = personRepository.findAll();
        List<PersonDto> allDto = new ArrayList<>();
        if(allPersons == null) {
            return null;
        }
        for(int i=0; i<allPersons.size(); i++) {
            allDto.add(personConverter.personEntityToPersonDto(allPersons.get(i)));
        }
        return allDto;
    }

    public List<PersonDto> findAll() {

        List<PersonEntity> entity = personRepository.findAll();

        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find person...");
        }

        List<PersonDto> entityDtos = new ArrayList<>();
        PersonDto entityDto ;

        for(int i=0; i<entity.size(); i++) {

            if(!entity.get(i).getName().equals("admin")){

                entityDto = personConverter.personEntityToPersonDto(entity.get(i)) ;
                entityDtos.add(entityDto);
            }
        }
        return entityDtos;
    }


    public PersonDto find(int id) {
        PersonEntity entity = personRepository.findById(id).orElse(null);

        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find person...");
        }
        return personConverter.personEntityToPersonDto(entity);
    }

    public PersonDto create(PersonDto personDto) {
        PersonEntity personEntity = personConverter.personDtoToPersonEntity(personDto);
        PersonEntity savedPersonEntitiy = personRepository.save(personEntity);

        return personConverter.personEntityToPersonDto(savedPersonEntitiy);
    }

    public PersonDto update(PersonDto personDto, Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person...");
        }
        personEntity.setAddress(personDto.getAddress());
        personEntity.setName(personDto.getName());
        personEntity.setEmail(personDto.getEmail());
        personEntity.setPhoneNumber(personDto.getPhoneNumber());
        personEntity.setPassword((personDto.getPassword()));

        return personConverter.personEntityToPersonDto(personRepository.save(personEntity));
    }

    public void delete(Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person...");
        }
        personRepository.delete(personEntity);
    }

    public BorrowedEntity getBorrow(Integer bookId, Integer personId) {
        BorrowedEntity borrowedEntity = borrowedRepository.findById(bookId).orElse(null);
        if (borrowedEntity == null) {
            throw new ExceptionResponse("Oopps cannot find borrowed...");
        }
        PersonEntity personEntity = personRepository.findById(personId).orElse(null);
        if (personEntity == null) {
            throw new ExceptionResponse("Oopps cannot find person...");
        }
        borrowedEntity.setPersonEntity(personEntity);

        return borrowedRepository.save(borrowedEntity);
    }

}
