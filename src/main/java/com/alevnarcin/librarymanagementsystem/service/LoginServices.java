package com.alevnarcin.librarymanagementsystem.service;

import com.alevnarcin.librarymanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginServices {

    private final PersonRepository personRepository;

    public String Login(String password, String name) {

       // String isUser= personRepository.existByPasswordAndName(password,name);
       return null;
    }

}
