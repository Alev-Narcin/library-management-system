package com.alevnarcin.librarymanagementsystem.service;


import com.alevnarcin.librarymanagementsystem.converter.AuthorConverter;
import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.repository.AuthorRepository;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;
    private final BookRepository bookRepository;

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
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        authorEntity.getBookEntities().add(bookEntity);
        bookEntity.getAuthorEntities().add(authorEntity);

        return bookRepository.save(bookEntity);
    }

}
