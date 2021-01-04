package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "t_borrowed")
@Entity(name = "BORROWED")
public class BorrowedEntity extends BaseEntity {


    // @Relation
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity personEntity;

    //@Relation
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;

    @Column(name = "borrow_start_date", nullable = false)
    private LocalDate borrowStartDate;

    @Column(name = "return_end_date", nullable = false)
    private LocalDate borrowEndDate;

    @Column(name = "borrow_return_date", nullable = false)
    private LocalDate borrowReturnDate;
}

