package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.BookType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Kitap")     // JPQL querylerinde kullanacağın isimi tanımla  //Dışta db ile bağlantıyı sağlar. Row isimlerine karşılık gelir.
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

    @Column(name="yayimci", nullable = false, updatable = false, length = 64)
    private String publisher;

    //ENUM'lar 'type' görevi görür. String,Long gibi.
    @Enumerated(EnumType.ORDINAL)
    @Column(name="tur", nullable = false, updatable = false)
    private BookType type;

    @Column(name="stok_adedi", nullable = false)
    private Long   stock;

    @Column(name="temin_turu", length = 64)
    private String supplyType;

    @Column(name="temin_gunu", nullable = false, updatable = false)
    private LocalDateTime supplyDate;

//    @OneToOne
//    @JoinColumn(name="person_id", foreignKey = @ForeignKey(name = "person_fk"))
//    private PersonEntity person;

    public BookEntity() {

        super();
    }


}
