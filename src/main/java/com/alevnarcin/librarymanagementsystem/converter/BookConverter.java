package com.alevnarcin.librarymanagementsystem.converter;

import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Component
public class BookConverter {

    public BookDto bookEntityToBookDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookEntity.getId());
        bookDto.setName(bookEntity.getName());
        bookDto.setAuthorNames(bookEntity.getAuthorEntities());
        bookDto.setType(bookEntity.getType());
        bookDto.setSupplyType(bookEntity.getSupplyType());
        bookDto.setSupplyDate(bookEntity.getSupplyDate());
        bookDto.setSerial_number((bookEntity.getSerial_number()));
        bookDto.setIsAvailable(bookEntity.getIsAvailable());

        return bookDto;
    }

    public BookEntity bookDtoToBookEntity(BookDto bookDto) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDto.getId());
        bookEntity.setName(bookDto.getName());
        bookEntity.setAuthorEntities(bookDto.getAuthorNames());
        bookEntity.setType(bookDto.getType());
        bookEntity.setSupplyType(bookDto.getSupplyType());
        bookEntity.setSupplyDate(bookDto.getSupplyDate());
        bookEntity.setSerial_number(bookDto.getSerial_number());
        bookEntity.setIsAvailable(bookDto.getIsAvailable());

        return bookEntity;
    }
}
