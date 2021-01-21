package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "t_borrowed")
@Entity(name = "BORROWED")
public class BorrowedEntity extends BaseEntity {

    @Column(name = "borrow_start_date", nullable = false)
    private LocalDate borrowStartDate;

    @Column(name = "return_end_date", nullable = false)
    private LocalDate borrowEndDate;

    @Column(name = "borrow_return_date")
    private LocalDate borrowReturnDate;

    // @Relation
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;

    // @Relation
    @ManyToOne(fetch = FetchType.LAZY)
     private PersonEntity personEntity;
}

