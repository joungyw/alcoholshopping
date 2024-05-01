package com.three.alcoholshoppingmall.project.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


    //해당 이메일을 사용하는 사람이 물건을 구매한적 있는지 확인
    @Query(value = "SELECT * FROM purchase  WHERE email = :email LIMIT 1 ", nativeQuery = true)
    Optional<Purchase> findByEmail(@Param("email") String email);

    //구매 내역중 픽업으로 수령한거
    @Query(value = "SELECT * FROM purchase WHERE email = :email AND delivery = 'PICKUP' ORDER BY purchaseday DESC, ordernumber DESC", nativeQuery = true)
    List<Purchase> Pickuplist(@Param("email") String email);

    //구매 내역중 배달로 수령한거
    @Query(value = "SELECT * FROM purchase WHERE email = :email AND delivery = 'DELIVERY' ORDER BY purchaseday DESC, ordernumber DESC", nativeQuery = true)
    List<Purchase> Deliverylist(@Param("email") String email);

    //구매 내역중 픽업으로 수령한거 최근 5개
    @Query(value = "SELECT * FROM purchase WHERE email = :email AND delivery = 'PICKUP' ORDER BY purchaseday DESC, ordernumber DESC LIMIT 5", nativeQuery = true)
    List<Purchase> Pickuplimt(@Param("email") String email);

    //구매 내역중 배달로 수령한거 최근 5개
    @Query(value = "SELECT * FROM purchase WHERE email = :email AND delivery = 'DELIVERY' ORDER BY purchaseday DESC, ordernumber DESC LIMIT 5", nativeQuery = true)
    List<Purchase> Deliverylimt(@Param("email") String email);

    //구매내역 전체
    @Query(value = "SELECT * FROM purchase WHERE email = :email ORDER BY purchaseday DESC, ordernumber DESC", nativeQuery = true)
    List<Purchase> Purchase(String email);

    @Query(value = "SELECT a.name \n" +
            "FROM alcohol a \n" +
            "JOIN stock b ON a.code = b.code \n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email\n" +
            "AND c.delivery = 'PICKUP' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC", nativeQuery = true)
    List<String> alcoholspick(String email);

    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email\n" +
            "AND c.delivery = 'PICKUP' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC", nativeQuery = true)
    List<String> marketspick(String email);


    @Query(value = "SELECT a.name \n" +
            "FROM alcohol a \n" +
            "JOIN stock b ON a.code = b.code \n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email\n" +
            "AND c.delivery = 'DELIVERY' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC", nativeQuery = true)
    List<String> alcoholsdelivery(String email);


    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email \n" +
            "AND c.delivery = 'DELIVERY' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC", nativeQuery = true)
    List<String> marketsdelivery(String email);




    @Query(value = "SELECT a.name \n" +
            "FROM alcohol a \n" +
            "JOIN stock b ON a.code = b.code \n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email\n" +
            "AND c.delivery = 'PICKUP' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC LIMIT 5", nativeQuery = true)
    List<String> alcoholspicklimt(String email);

    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email \n" +
            "AND c.delivery = 'PICKUP' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC LIMIT 5", nativeQuery = true)
    List<String> marketspicklimt(String email);


    @Query(value = "SELECT a.name \n" +
            "FROM alcohol a \n" +
            "JOIN stock b ON a.code = b.code \n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email\n" +
            "AND c.delivery = 'DELIVERY' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC LIMIT 5", nativeQuery = true)
    List<String> alcoholsdeliverylimt(String email);


    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN purchase c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email \n" +
            "AND c.delivery = 'DELIVERY' \n" +
            "ORDER BY c.purchaseday DESC, c.ordernumber DESC LIMIT 5", nativeQuery = true)
    List<String> marketsdeliverylimt(String email);

}
