package com.three.alcoholshoppingmall.project.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByStocknumber(Long stock);


    @Query(value = "SELECT stocknumber FROM stock a\n" +
            "JOIN alcohol b ON a.code = b.code\n" +
            "WHERE b.code = :code", nativeQuery = true)
    List<Integer> code(Long code);


}
