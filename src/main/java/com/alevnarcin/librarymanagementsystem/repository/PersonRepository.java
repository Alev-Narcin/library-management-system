package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {


}
