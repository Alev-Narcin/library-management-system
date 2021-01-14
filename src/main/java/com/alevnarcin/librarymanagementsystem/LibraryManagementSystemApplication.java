package com.alevnarcin.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    // method adı ile bir tane CommandLineRunner bean'i yarat.
    // Autowired olarak CommandLineRunner istenen yerlere bu yaratılan Bean'ı kullanabiliyor.
    //Manuel olarak giriş yapılacağı zaman Bean'ı kullan.

    /*@Bean
    CommandLineRunner Saver(@Autowired BookRepository bookRepo, @Autowired PersonRepository personRepository) {

        return new MyCommandlineRunner(bookRepo, personRepository);
    }*/
}
