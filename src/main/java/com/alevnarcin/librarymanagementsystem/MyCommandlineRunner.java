package com.alevnarcin.librarymanagementsystem;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;

// Spring başladıktan sonra istediğin kodu çalıştırmak için CommandLineRunner interface'ini implement ediyorsun.
// run methodu normaldeki main'in oluyor.
@RequiredArgsConstructor
public class MyCommandlineRunner implements CommandLineRunner {

    private final BookRepository bookRepo;
    private final BookService bookService;

//    public MyCommandlineRunner(BookRepository bookRepo, BookService bookService) {
//        this.bookService = bookService;
//        this.bookRepo = bookRepo;
//    }


    @Override
    public void run(String... args) throws Exception {
        saveBook();
        //deleteEntity(entity);
        //updateEntity(entity);
    }

    private void saveBook() {
        BookEntity entity = new BookEntity();
        entity.setAuthorName("Alev Narçin");
        entity.setName("JPA Essentials");
        entity.setPublisher("Altinören");
        entity.setSupplyDate(LocalDateTime.now());
        saveEntity(entity);
    }

    private void saveEntity(BookEntity entity) {

        bookRepo.save(entity);
    }


//    private void deleteEntity(BookEntity entity) {
//        bookRepo.delete(entity);
//
//    }

//    private void updateEntity(BookEntity entity) {
//        Optional<BookEntity> book = bookRepo
//                .findById(entity.getId());
//        if (book.isPresent()) {
//        }

       //   bookService.update(entity);

//}
}
