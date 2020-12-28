package com.alevnarcin.librarymanagementsystem;

import com.alevnarcin.librarymanagementsystem.repository.BookRepository;
import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import com.alevnarcin.librarymanagementsystem.service.BookService;
import com.alevnarcin.librarymanagementsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    // method adı ile bir tane CommandLineRunner bean'i yarat.
    // Autowired olarak CommandLineRunner istenen yerlere bu yaratılan Bean'ı kullanabiliyor.
//    @Bean
//    CommandLineRunner Saver(@Autowired BookRepository bookRepo, @Autowired PersonRepository personRepository) {
//
//        return new MyCommandlineRunner(bookRepo, personRepository);
//    }
}
