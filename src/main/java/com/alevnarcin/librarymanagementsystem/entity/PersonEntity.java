package com.alevnarcin.librarymanagementsystem.entity;


import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import sun.security.util.Password;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "PERSON")
@Table(name = "t_person")
public class PersonEntity extends BaseEntity {

    @Column(name = "TC", length = 11, nullable = false)
    private String TC;

    @Column(name = "name_surname", length = 25, nullable = false)
    private String name;

    @Column(name = "phone_number", length =14, nullable = false)
    private String phoneNumber;

    @Column(name = "Email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private PersonType type;

    @Column(name = "member_ship_date", nullable = false, updatable = false )
    private LocalDateTime memberShipDate;

    @Column(name = "address", nullable = false)
    private String address;

//    @Column(name = "password", nullable = false)
//    private String password;

    public PersonEntity(){
        super();
    }

    // RELATIONS
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "personEntity")
    @JsonIgnoreProperties(value = "personEntity")
    private Set<BorrowedEntity> borrowedEntities = new HashSet<>();


}
