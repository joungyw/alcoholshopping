package com.three.alcoholshoppingmall.project.market;

import com.three.alcoholshoppingmall.project.purchase.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long> {


    @Query(value = "SELECT a.* FROM market a \n" +
            "LEFT JOIN stock b ON a.marketcode = b.marketcode \n" +
            "LEFT JOIN alcohol c ON b.code = c.code \n" +
            "WHERE c.code = :code", nativeQuery = true)
    List<Market> marketlist(Long code);


    @Query(value = "SELECT e.* FROM purchase a " +
            "LEFT JOIN stock b ON a.stocknumber = b.stocknumber " +
            "LEFT JOIN alcohol c ON b.code = c.code " +
            "LEFT JOIN review d ON c.code = d.code AND d.email = :email " +
            "LEFT JOIN market e ON b.marketcode = e.marketcode " +
            "WHERE a.email = :email and d.code IS NULL", nativeQuery = true)
    List<Market> marketreview(String email);
}
