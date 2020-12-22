package com.alevnarcin.librarymanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Long k_Id;
    private String k_Name;
    private String k_AuthorName;
    private String k_Publisher;
    private String k_Type;
    private Long k_Stock;
    private String k_SupplyType;
    private String k_SupplyDate;

    public BookEntity(Long k_BookId, String k_BookName, String k_AuthorName, String k_PublisherName, String k_BookType, Long k_Stock, String k_SupplyType, String k_SupplyDate) {
        this.k_Id = k_BookId;
        this.k_Name = k_BookName;
        this.k_AuthorName = k_AuthorName;
        this.k_Publisher = k_PublisherName;
        this.k_Type = k_BookType;
        this.k_Stock = k_Stock;
        this.k_SupplyType = k_SupplyType;
        this.k_SupplyDate = k_SupplyDate;
    }

    public BookEntity() {

        super();
    }

    public Long getK_BookId() {

        return k_Id;
    }

    public void setK_BookId(Long k_BookId) {

        this.k_Id = k_BookId;
    }

    public String getK_BookName() {

        return k_Name;
    }

    public void setK_BookName(String k_BookName) {

        this.k_Name = k_BookName;
    }

    public String getK_AuthorName() {

        return k_AuthorName;
    }

    public void setK_AuthorName(String k_AuthorName) {

        this.k_AuthorName = k_AuthorName;
    }

    public String getK_PublisherName() {

        return k_Publisher;
    }

    public void setK_PublisherName(String k_PublisherName) {

        this.k_Publisher = k_PublisherName;
    }

    public String getK_BookType() {

        return k_Type;
    }

    public void setK_BookType(String k_BookType) {

        this.k_Type = k_BookType;
    }

    public Long getK_Stock() {

        return k_Stock;
    }

    public void setK_Stock(Long k_Stock) {

        this.k_Stock = k_Stock;
    }

    public String getK_SupplyType() {

        return k_SupplyType;
    }

    public void setK_SupplyType(String k_SupplyType) {

        this.k_SupplyType = k_SupplyType;
    }

    public String getK_SupplyDate() {

        return k_SupplyDate;
    }

    public void setK_SupplyDate(String k_SupplyDate) {

        this.k_SupplyDate = k_SupplyDate;
    }

    @Override
    public String toString() {
        return String.format("Book [Id=%s, Author Name=%s, Publisher Name=%s]", k_Id,k_AuthorName,k_Publisher);
    }

}
