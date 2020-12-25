package com.alevnarcin.librarymanagementsystem.entity;


import com.alevnarcin.librarymanagementsystem.enumeration.PersonType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "Kisi")
@Table(name = "kisi")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "TC", length = 11, nullable = false, updatable = false)
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

}
