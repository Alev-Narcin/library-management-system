package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@Table(name = "yayinevi")
@Entity(name = "Publisher")
public class PublisherEntity extends BaseEntity {

    @Column(name = "isim")
    private String name;

    @ManyToMany(mappedBy = "publisherEntities")
    private Set<BookEntity> bookEntities = new HashSet<>();

    public PublisherEntity() {
        super();
        this.name = name;
    }
}
