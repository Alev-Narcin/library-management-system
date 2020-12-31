package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity(name = "Book")         // JPQL querylerinde kullanacağın isimi tanımla  //Dışta db ile bağlantıyı sağlar. Row isimlerine karşılık gelir.
@Table(name = "kitap")          // tablo adı
@Getter
@Setter

@EqualsAndHashCode(exclude = "authorEntities")

// Lombok annotation'ı getter ve setterları yaratıyor, required args constructor, equals and hashCode methodlarını otomatik yaratıyor.
public class BookEntity extends BaseEntity {


    @Column(name = "ISBN", nullable = false, unique = true)
    @Id
    private String serial_number;

    // kolon tanımları, columnDefinition'ı kullanma
    @Column(name = "isim", nullable = false, length = 64)
    private String name;

    @Column(name = "yazar", nullable = false, length = 64)
    private String authorName;   //field ın tipini değiştir.

    @Column(name = "yayimci", nullable = false, length = 64)
    private String publisher; // fieldın tipini değiştir.

    //ENUM'lar 'type' görevi görür. String yada ORDINAL olabilir.
    @Enumerated(EnumType.STRING)
    //Aşk,macera,roman,hikaye,polisiye vb.
    @Column(name = "tur", updatable = false)
    private BookType type;

    @Column(name = "stok_adedi")
    private Long stock;
    //bağış,satın alma gibi
    @Column(name = "temin_turu", length = 64)
    private String supplyType;

    @Column(name = "temin_gunu", nullable = false, updatable = false)
    private LocalDateTime supplyDate;


    public BookEntity() {

        super();
    }

    //Relation personEntity&bookEntity
    @ManyToOne
    @JoinColumn(name ="kisi_id")
    @JsonIgnoreProperties(value = "bookEntities")
    private PersonEntity personEntity;

    public PersonEntity getPersonEntity() {

        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {

        this.personEntity = personEntity;
    }


    //Relation authorEntity&bookEntity
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<AuthorEntity> authorEntities = new HashSet<>();



    public BookEntity(String name, AuthorEntity... authorEntities) {
        this.name = name;
        this.authorEntities = Stream.of(authorEntities).collect(Collectors.toSet());
        this.authorEntities.forEach(x ->x.getBookEntities().add(this));

    }
}
