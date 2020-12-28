package com.alevnarcin.librarymanagementsystem.entity;


import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity(name = "Kisi")
@Table(name = "kisi")
@EqualsAndHashCode(of = "id")
public class PersonEntity extends BaseEntity {

    @Column(name = "TC", length = 11, nullable = false)
    private String TC;

    @Column(name = "ad_Soyad", length = 25, nullable = false)
    private String name;

    @Column(name = "Telefon_No", length =14, nullable = false)
    private String phoneNumber;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "cinsiyet", nullable = false, updatable = false)
    private PersonType type;

    @Column(name = "uyelik_tarihi", nullable = false, updatable = false )
    private LocalDateTime memberShipDate;

    @Column(name = "adres", nullable = false)
    private String address;

    public PersonEntity(){
        super();
    }

    // RELATIONS
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    @JoinTable(name= "kisi_kitap",
            joinColumns = @JoinColumn(name = "personId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<BookEntity> bookEntities = new HashSet<>();
}
