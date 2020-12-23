package com.alevnarcin.librarymanagementsystem.service;

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

   public BookEntity update(BookEntity bookEntity) {
      return bookRepository.save(bookEntity);
   }


   public Book find(int id) {
      Optional<BookEntity> entity = bookRepository.findById(id);
      if (entity.isPresent()) {
         return convert(entity.get());
      }
      throw new NoSuchElementException();
   }

   private BookEntity convert(Book book) {
      throw new UnsupportedOperationException("implement");
   }

   private Book convert(BookEntity entity) {
      // Book'a dönüştür.
      throw new UnsupportedOperationException("implement");
   }

   public void create(Book book) {
      bookRepository.save(convert(book));
   }
}
