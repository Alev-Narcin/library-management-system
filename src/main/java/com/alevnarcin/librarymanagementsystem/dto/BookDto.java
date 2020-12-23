package com.alevnarcin.librarymanagementsystem.dto;

import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDto {

    private Integer id;
    private String name;
    private String authorName;
    private String publisher;
    private BookType type;
    private Long stock;
    private String supplyType;
    private LocalDateTime supplyDate;
}
