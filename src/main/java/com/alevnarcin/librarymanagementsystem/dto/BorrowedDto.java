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
    private LocalDate borrowStartDate;;
    private LocalDate borrowEndDate;
    private LocalDate borrowReturnDate;
    private Integer book_entity_id;
    private Integer person_entity_id;

}
