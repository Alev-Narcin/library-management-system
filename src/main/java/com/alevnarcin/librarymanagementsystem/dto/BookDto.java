package com.alevnarcin.librarymanagementsystem.dto;

import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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
    private String serial_number;
}
