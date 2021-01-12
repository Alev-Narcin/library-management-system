package com.alevnarcin.librarymanagementsystem.repository;

import com.alevnarcin.librarymanagementsystem.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    /*String existByPasswordAndName(String password, String name);
*/
}
