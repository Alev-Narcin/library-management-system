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
    @OneToOne(fetch = FetchType.LAZY)
    private PersonEntity personEntity;

    //@Relation
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;


    @Column(name = "borrowance_date", nullable = false)
    private LocalDate borrowanceDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;


}

