package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.converter.BookConverter;
import com.alevnarcin.librarymanagementsystem.converter.PublisherConverter;
import com.alevnarcin.librarymanagementsystem.dto.PublisherDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PublisherEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherConverter publisherConverter;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public PublisherService(PublisherRepository publisherRepository, PublisherConverter publisherConverter, BookRepository bookRepository, BookConverter bookConverter) {
        this.publisherRepository = publisherRepository;
        this.publisherConverter = publisherConverter;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public PublisherDto find(Integer id) {
        PublisherEntity entity = publisherRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return publisherConverter.publisherEntityToPublisherDto(entity);
    }

    public PublisherDto create(PublisherDto publisherDto) {
        PublisherEntity publisherEntity = publisherConverter.publisherDtoToPublisherEntity(publisherDto);
        PublisherEntity savedPublisherEntity = publisherRepository.save(publisherEntity);
        return publisherConverter.publisherEntityToPublisherDto(savedPublisherEntity);
    }

    public PublisherDto update(PublisherDto publisherDto, Integer publisherId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElseThrow(NoSuchElementException::new);

        publisherEntity.setName(publisherDto.getName());
        publisherEntity.setId(publisherDto.getId());

        return publisherConverter.publisherEntityToPublisherDto(publisherRepository.save(publisherEntity));
    }

    public void delete(Integer publisherId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElse(null);
        publisherRepository.delete(publisherEntity);
    }

    public BookEntity getBook(Integer publisherId, Integer bookId) {
        PublisherEntity publisherEntity = publisherRepository.findById(publisherId).orElseThrow(NoSuchElementException::new);
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        publisherEntity.getBookEntities().add(bookEntity);
        bookEntity.getPublisherEntities().add(publisherEntity);

        return bookRepository.save(bookEntity);

    }

}
