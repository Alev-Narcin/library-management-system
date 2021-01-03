package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "emanet")
@Entity(name = "Borrowed")
public class BorrowedEntity extends BaseEntity {


    // @Relation
    @OneToOne(fetch = FetchType.LAZY)
    private PersonEntity personEntity;

    //@Relation
    @OneToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;


    @Column(name = "alis_tarihi", nullable = false)
    private LocalDate borrowanceDate;

    @Column(name = "teslim_tarihi", nullable = false)
    private LocalDate returnDate;


}

