package com.three.alcoholshoppingmall.project.shoppingbasket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingbasketRepository extends JpaRepository<Shoppingbasket, Long> {

    @Query(value = "SELECT NAME from alcohol WHERE CODE = :code", nativeQuery = true)
    String alcoholname(Long code);

    @Query(value = "SELECT marketname from market WHERE marketcode= :marketcode", nativeQuery = true)
    String marketname(Long marketcode);

    @Query(value = "SELECT picture FROM alcohol\n" +
            "WHERE CODE = :code",nativeQuery = true)
    String pictur(Long code);

    Shoppingbasket findByUser_Email(String email);
}
