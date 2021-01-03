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
@Data
@Entity(name = "Author")
@Table(name ="yazar")
public class AuthorEntity extends BaseEntity {

    @Column(name = "ad_soyad", nullable = false)
    private String name;


    @ManyToMany(mappedBy = "authorEntities")
    private Set<BookEntity> bookEntities = new HashSet<>();


    public AuthorEntity() {
        super();
        this.name = name;
    }

}
