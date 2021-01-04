package com.alevnarcin.librarymanagementsystem.dto;

import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {

    private Integer id;
    private String name;
    private Set<AuthorEntity> authorNames = new HashSet<>();
    private String publisher;
    private BookType type;
    private Long stock;
    private String supplyType;
    private LocalDateTime supplyDate;
    private String serial_number;
}
