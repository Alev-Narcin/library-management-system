package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.entity.base.BaseEntity;
import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "BOOK")                                               // JPQL querylerinde kullanacağın isimi tanımla  //Dışta db ile bağlantıyı sağlar. Row isimlerine karşılık gelir.
@Table(name = "t_book")
@Getter
@Setter                                                             //@EqualsAndHashCode(exclude = "authorEntities")
public class BookEntity extends BaseEntity {                        // Lombok annotation'ı getter ve setterları yaratıyor, required args constructor, equals and hashCode methodlarını otomatik yaratıyor.

    @Column(name = "ISBN", nullable = false, unique = true)
    private String serial_number;

    @Column(name = "name", nullable = false, length = 64)       // kolon tanımları, columnDefinition'ı kullanma
    private String name;

    @Enumerated(EnumType.STRING)                   //ENUM'lar 'type' görevi görür. String yada ORDINAL olabilir.
    @Column(name = "type", updatable = false)     //Aşk,macera,roman,hikaye,polisiye vb.
    private BookType type;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "supply_type", length = 64)          //bağış,satın alma gibi
    private String supplyType;

    @Column(name = "supply_date", nullable = false, updatable = false)
    private LocalDateTime supplyDate;

    //Relation authorEntity&bookEntity
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "t_book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorEntity> authorEntities = new HashSet<>();


    //Relation borrowedEntity&bookEntity
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bookEntity")
    @JsonIgnoreProperties(value = "bookEntity")
    private Set<BorrowedEntity> borrowedEntities = new HashSet<>();

    //Relation publisherEntity&bookEntity
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "t_book_publisher",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<PublisherEntity> publisherEntities = new HashSet<>();
}
