package com.alevnarcin.librarymanagementsystem;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import com.alevnarcin.librarymanagementsystem.enumeration.PersonType;
import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;


import java.time.LocalDateTime;

// Spring başladıktan sonra istediğin kodu çalıştırmak için CommandLineRunner interface'ini implement ediyorsun.
// run methodu normaldeki main'in oluyor.

@RequiredArgsConstructor
public class MyCommandlineRunner implements CommandLineRunner {

    private final BookRepository bookRepo;
    private final PersonRepository personRepo;


    @Override
    public void run(String... args) throws Exception {

        saveBook();
        savePerson();

    }

    private void savePerson() {
        PersonEntity entity = new PersonEntity();
        entity.setTC("12345678912");
        entity.setMemberShipDate(LocalDateTime.now());
        entity.setAddress("kosova mah. veysel karani cad.ışıklar inşaaat no :23");
        entity.setName("Rüzgar narçin");
        entity.setPhoneNumber("05542546635");
        entity.setType(PersonType.ERKEK);
        entity.setEmail("alevnarcin@mavinci.com");
        saveEntity(entity);
    }

    private void saveBook() {
        BookEntity entity = new BookEntity();
        entity.setAuthorName("Alev Narçin");
        entity.setName("JPA Essentials");
        entity.setPublisher("Altinören");
        entity.setSupplyDate(LocalDateTime.now());
        entity.setStock(20l);
        entity.setType(BookType.BILGISAYAR_INTERNET);
        entity.setSupplyType("bağış");
        saveEntity(entity);
    }

    private void saveEntity(BookEntity entity) {

        bookRepo.save(entity);
    }

    private void saveEntity(PersonEntity entity) {

        personRepo.save(entity);
    }

}
