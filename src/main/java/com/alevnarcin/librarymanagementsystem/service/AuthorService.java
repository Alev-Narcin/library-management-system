package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.AuthorConverter;
import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.AuthorRepository;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;
    private final BookRepository bookRepository;

    public AuthorDto find(Integer id) {
        AuthorEntity entity = authorRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find author... ");
        }
        return authorConverter.authorEntityToAuthorDto(entity);
    }

    public AuthorDto create(AuthorDto authorDto) {
        AuthorEntity authorEntity = authorConverter.authorDtoToAuthorEntity(authorDto);
        AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);

        return authorConverter.authorEntityToAuthorDto(savedAuthorEntity);
    }

    public AuthorDto update(AuthorDto authorDto, Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElse(null);
        if (authorEntity == null) {
            throw new ExceptionResponse("Oopps cannot find author... ");
        }
        authorEntity.setName(authorDto.getName());

        return authorConverter.authorEntityToAuthorDto(authorRepository.save(authorEntity));
    }

    public  void delete(Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElse(null);
        if (authorEntity == null) {
            throw new ExceptionResponse("Oopps cannot find author... ");
        }
        authorRepository.delete(authorEntity);
    }

    public BookEntity getBook(Integer authorId, Integer bookId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElse(null);
        if (authorEntity == null) {
            throw new ExceptionResponse("Oopps cannot find author... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        authorEntity.getBookEntities().add(bookEntity);
        bookEntity.getAuthorEntities().add(authorEntity);

        return bookRepository.save(bookEntity);
    }

}
