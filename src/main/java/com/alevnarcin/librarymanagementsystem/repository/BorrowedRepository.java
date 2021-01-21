package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BorrowedRepository extends JpaRepository<BorrowedEntity, Integer> {

}
