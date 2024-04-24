package com.three.alcoholshoppingmall.project.search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {


    @Query(value = "SELECT *  FROM search where email = :email ORDER BY Id DESC LIMIT 5", nativeQuery = true)
    List<Search> recentSearch(String email);


}