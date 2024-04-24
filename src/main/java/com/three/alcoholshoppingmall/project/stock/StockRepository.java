package com.three.alcoholshoppingmall.project.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Stack;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByNameAndMarketname(String name, String marketname);


    //해당 매장의 해당술의 재고
    @Query(value = "SELECT amount FROM stock WHERE NAME = :name AND marketname = :marketname",nativeQuery = true)
    int limt(String name, String marketname);
}
