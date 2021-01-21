package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByName(String name);
}
