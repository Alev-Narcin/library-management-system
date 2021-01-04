package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "AUTHOR")
@Table(name ="t_author")
public class AuthorEntity extends BaseEntity {

    @Column(name = "name_surname", nullable = false)
    private String name;


    @ManyToMany(mappedBy = "authorEntities")
    private Set<BookEntity> bookEntities = new HashSet<>();


    public AuthorEntity() {
        super();
        this.name = name;
    }

}
