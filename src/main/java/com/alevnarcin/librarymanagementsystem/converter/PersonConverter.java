package com.alevnarcin.librarymanagementsystem.converter;

import com.alevnarcin.librarymanagementsystem.dto.PersonDto;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public PersonDto personEntityToPersonDto(PersonEntity personEntity) {

        PersonDto personDto = new PersonDto();

        personDto.setEmail(personEntity.getEmail());
        personDto.setAddress(personEntity.getAddress());
        personDto.setId(personEntity.getId());
        personDto.setName(personEntity.getName());
        personDto.setTC(personEntity.getTC());
        personDto.setType(personEntity.getType());
        personDto.setPhoneNumber(personEntity.getPhoneNumber());
        personDto.setMemberShipDate(personEntity.getMemberShipDate());

        return personDto;

    }

    public PersonEntity personDtoToPersonEntity(PersonDto personDto) {

        PersonEntity personEntity = new PersonEntity();

        personEntity.setEmail(personDto.getEmail());
        personEntity.setName(personDto.getName());
        personEntity.setType(personDto.getType());
        personEntity.setAddress(personDto.getAddress());
        personEntity.setPhoneNumber(personDto.getPhoneNumber());
        personEntity.setTC(personDto.getTC());
        personEntity.setId(personDto.getId());
        personEntity.setMemberShipDate(personDto.getMemberShipDate());

        return personEntity;

    }
}
