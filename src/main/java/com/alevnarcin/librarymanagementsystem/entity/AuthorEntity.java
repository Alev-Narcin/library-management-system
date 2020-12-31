package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Author")
@Table(name = )
public class AuthorEntity extends BaseEntity {

    @Column(name = "ad_soyad", nullable = false)
    private String name;


    @ManyToMany
    Set<BookEntity> bookEntities = new HashSet<>();



}
