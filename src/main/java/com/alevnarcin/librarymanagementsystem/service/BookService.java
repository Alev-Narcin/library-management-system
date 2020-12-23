package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.model.Book;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;


    public BookDto find(int id) {
        BookEntity entity = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return bookConverter.bookEntityToBookDto(entity);
    }

    public BookDto update(BookDto bookDto) {
        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookDto);
        BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookConverter.bookEntityToBookDto(savedBookEntity);
    }

    public BookDto create(BookDto bookDto) {
        // 1. convert dto to entity
        BookEntity bookEntity = bookConverter.bookDtoToBookEntity(bookDto);
        // 2. save entity to database
        BookEntity savedBookEntity = bookRepository.save(bookEntity);
        // 3. convert entity to dto
        bookDto = bookConverter.bookEntityToBookDto(savedBookEntity);
        // 4. return dto
        return bookDto;
    }


}
