package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.AuthorConverter;
import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.repository.AuthorRepository;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;
    private BookService bookService;
    private final BookConverter bookConverter;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, AuthorConverter authorConverter, BookConverter bookConverter, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
        this.bookConverter = bookConverter;
        this.bookRepository = bookRepository;
    }

    public AuthorDto find(Integer id) {
        AuthorEntity entity = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return authorConverter.authorEntityToAuthorDto(entity);
    }

    public AuthorDto create(AuthorDto authorDto) {
        AuthorEntity authorEntity = authorConverter.authorDtoToAuthorEntity(authorDto);
        AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);
        return authorConverter.authorEntityToAuthorDto(savedAuthorEntity);
    }

    public AuthorDto update(AuthorDto authorDto, Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);

        authorEntity.setName(authorDto.getName());
        authorEntity.setId(authorDto.getId());

        return authorConverter.authorEntityToAuthorDto(authorRepository.save(authorEntity));

    }

    public  void delete(Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        authorRepository.delete(authorEntity);
    }


    public BookEntity getBook(Integer authorId, Integer bookId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElse(null);
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        bookEntity.setAuthorEntities((Set<AuthorEntity>) authorEntity);

        return bookRepository.save(bookEntity);

    }

}
