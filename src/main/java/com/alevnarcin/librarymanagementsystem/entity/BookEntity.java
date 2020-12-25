package com.alevnarcin.librarymanagementsystem.entity;

import com.alevnarcin.librarymanagementsystem.enumeration.BookType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "Kitap")         // JPQL querylerinde kullanacağın isimi tanımla  //Dışta db ile bağlantıyı sağlar. Row isimlerine karşılık gelir.
@Table(name = "kitap")          // tablo adı
@Data                           // Lombok annotation'ı getter ve setterları yaratıyor, required args constructor, equals and hashCode methodlarını otomatik yaratıyor.
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // kolon tanımları, columnDefinition'ı kullanma
    @Column(name = "isim", nullable = false, length = 64)
    private String name;

    @Column(name = "yazar", nullable = false, length = 64)
    private String authorName;

    @Column(name = "yayimci", nullable = false, length = 64)
    private String publisher;

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


}
