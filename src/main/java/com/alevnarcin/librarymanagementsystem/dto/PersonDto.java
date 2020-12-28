package com.alevnarcin.librarymanagementsystem.dto;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Integer id;
    private String TC;
    private String name;
    private String phoneNumber;
    private String email;
    private PersonType type;
    private LocalDateTime memberShipDate;
    private String address;
    private Set<BookEntity> bookEntities;
}
