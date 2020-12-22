package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.BookType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Kitap")     // JPQL querylerinde kullanacağın isimi tanımla
@Table(name="kitap")          // tablo adı
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    // kolon tanımları, columnDefinition'ı kullanma
    @Column(name="isim", nullable = false, updatable = false, length = 64)
    private String name;

    @Column(name="yazar", nullable = false, updatable = false, length = 64)
    private String authorName;

    @Column(name="yayımcı", nullable = false, updatable = false, length = 64)
    private String publisher;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="tür", nullable = false, updatable = false)
    private BookType type;

    @Column(name="stok_adeti", nullable = false)
    private Long   stock;

    @Column(name="temin_türü", length = 64)
    private String supplyType;

    @Column(name="temin_günü", nullable = false, updatable = false)
    private LocalDateTime supplyDate;

    @OneToOne
    @JoinColumn(name="person_id", foreignKey = @ForeignKey(name = "person_fk"))
    private PersonEntity person;

    public BookEntity() {
        super();
    }


}
