package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PublisherConverter;
import com.alevnarcin.librarymanagementsystem.dto.PublisherDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PublisherEntity;
import com.alevnarcin.librarymanagementsystem.exception.response.ExceptionResponse;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherConverter publisherConverter;
    private final BookRepository bookRepository;


    @Transactional
    public PublisherDto find(Integer id) {
        PublisherEntity entity = publisherRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new ExceptionResponse("Oopps cannot find publisher... ");
        }
        return publisherConverter.publisherEntityToPublisherDto(entity);
    }

    @Transactional
    public PublisherDto create(PublisherDto publisherDto) {
        PublisherEntity publisherEntity = publisherConverter.publisherDtoToPublisherEntity(publisherDto);
        PublisherEntity savedPublisherEntity = publisherRepository.save(publisherEntity);

        return publisherConverter.publisherEntityToPublisherDto(savedPublisherEntity);
    }

    @Transactional
    public PublisherDto update(PublisherDto publisherDto, Integer publisherId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElse(null);
        if (publisherEntity == null) {
            throw new ExceptionResponse("Oopps cannot find publisher... ");
        }
        publisherEntity.setName(publisherDto.getName());

        return publisherConverter.publisherEntityToPublisherDto(publisherRepository.save(publisherEntity));
    }

    @Transactional
    public void delete(Integer publisherId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElse(null);
        if (publisherEntity == null) {
            throw new ExceptionResponse("Oopps cannot find publisher... ");
        }
        publisherRepository.delete(publisherEntity);
    }

    @Transactional
    public BookEntity getBook(Integer publisherId, Integer bookId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElse(null);
        if (publisherEntity == null) {
            throw new ExceptionResponse("Oopps cannot find publisher... ");
        }
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if (bookEntity == null) {
            throw new ExceptionResponse("Oopps cannot find book... ");
        }
        publisherEntity.getBookEntities().add(bookEntity);
        bookEntity.getPublisherEntities().add(publisherEntity);

        return bookRepository.save(bookEntity);
    }
}
