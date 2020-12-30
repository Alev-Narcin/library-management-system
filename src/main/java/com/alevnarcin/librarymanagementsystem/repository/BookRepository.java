package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA repository'i __extend__ edersen repository bean'ini spring boot otomatik yaratıyor.
// JpaRepository<EntityType, EntityIdType>
// EntityType := BookEntity gibi
// EntityIdType := Tanımladığın entity'nin idsini type.
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {


}
