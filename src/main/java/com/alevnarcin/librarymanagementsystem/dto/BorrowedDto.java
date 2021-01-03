package com.alevnarcin.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedDto {

    private Integer id;
    private LocalDate borrowanceDate;
    private LocalDate returnDate;

}
