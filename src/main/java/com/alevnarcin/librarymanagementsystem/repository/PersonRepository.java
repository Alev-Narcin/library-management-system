package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;

import java.lang.annotation.Native;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("from com.alevnarcin.librarymanagementsystem.entity.PersonEntity p where p.name is not null")
    String existByPasswordAndName(String password, String name);

    PersonEntity findByNameNotNull();
}
