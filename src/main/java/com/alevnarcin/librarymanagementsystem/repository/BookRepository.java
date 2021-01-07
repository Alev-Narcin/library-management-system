package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

// JPA repository'i __extend__ edersen repository bean'ini spring boot otomatik yaratıyor.
// JpaRepository<EntityType, EntityIdType>
// EntityType := BookEntity gibi
// EntityIdType := Tanımladığın entity'nin idsini type.
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Set<BookEntity> findAllByIsAvailable(Boolean isAvailable);

//    @Query(value = "select book from BOOK book order by ?1", nativeQuery = true)
//    Set<BookEntity> findAllOrderByName(String name);
}
