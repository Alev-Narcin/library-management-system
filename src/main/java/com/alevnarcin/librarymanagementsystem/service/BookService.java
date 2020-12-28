package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.validation.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;


    public BookDto find(int id) {
        BookEntity entity = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return bookConverter.bookEntityToBookDto(entity);
    }

    public BookDto create(BookDto bookDto) {
        BookValidation.validateBook(bookDto);


        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookDto);  // 1. convert dto to entity
        BookEntity savedBookEntity = bookRepository.save(bookEntity);       // 2. save entity to database
        return bookConverter.bookEntityToBookDto(savedBookEntity);       // 3. convert entity to dto
        // 4. return dto
    }

    public BookDto update(BookDto bookDto, Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);

        bookEntity.setName(bookDto.getName());
        bookEntity.setPublisher(bookDto.getPublisher());
        bookEntity.setAuthorName(bookDto.getAuthorName());
        bookEntity.setSupplyType(bookDto.getSupplyType());
        bookEntity.setStock(bookDto.getStock());

        return bookConverter.bookEntityToBookDto(bookRepository.save(bookEntity));
    }

    public void delete(Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        bookRepository.delete(bookEntity);
    }
}
