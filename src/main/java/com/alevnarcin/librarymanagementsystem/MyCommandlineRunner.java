package com.alevnarcin.librarymanagementsystem;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;

// Spring başladıktan sonra istediğin kodu çalıştırmak için CommandLineRunner interface'ini implement ediyorsun.
// run methodu normaldeki main'in oluyor.
public class MyCommandlineRunner implements CommandLineRunner {

    private final BookRepository bookRepo;

    public MyCommandlineRunner(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        BookEntity entity = new BookEntity();
        entity.setAuthorName("Alev Narçin");
        entity.setName("JPA Essentials");
        entity.setPublisher("Altinören");
        entity.setSupplyDate(LocalDateTime.now());
        //saveEntity(entity);
        deleteEntity(entity);
        //updateEntity(entity);
    }

    private void saveEntity(BookEntity entity) {
        bookRepo.save(entity);
    }

    private void deleteEntity(BookEntity entity) {
        bookRepo.delete(entity);

    }

    private void updateEntity(BookEntity entity ) {
        BookEntity Stockk = bookRepo.findById(entity.getId(), entity.getStock());
        entity.getStock() = Stockk.getStock();
        bookRepo.save(entity);
    }
}
