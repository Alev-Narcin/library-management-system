package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BorrowedRepository extends JpaRepository<BorrowedEntity, Integer> {
}
