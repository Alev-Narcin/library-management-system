package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity(name = "Author")
@Table(name ="yazar")
public class AuthorEntity extends BaseEntity {

    @Column(name = "ad_soyad", nullable = false)
    private String name;


    @ManyToMany(mappedBy = "authorEntity")
    private Set<BookEntity> bookEntities = new HashSet<>();


    public AuthorEntity(String name) {
        this.name = name;
    }

    public AuthorEntity() {
        super();
    }
}
