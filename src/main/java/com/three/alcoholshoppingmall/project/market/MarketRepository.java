package com.three.alcoholshoppingmall.project.market;

import com.three.alcoholshoppingmall.project.purchase.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long> {

    @Query(value = "SELECT delivery FROM market WHERE marketname = :marketname",nativeQuery = true)
    Delivery deliverytype(String marketname);

}
