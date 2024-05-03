package com.three.alcoholshoppingmall.project.shoppingbasket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingbasketRepository extends JpaRepository<Shoppingbasket, Long> {

    //장바구니 조회
    List<Shoppingbasket> findByUser_Email(String email);

    @Query(value = "SELECT a.name \n" +
            "FROM alcohol a \n" +
            "JOIN stock b ON a.code = b.code \n" +
            "JOIN shoppingbasket c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email", nativeQuery = true)
    List<String> alcohol(String email);

    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN shoppingbasket c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email", nativeQuery = true)
    List<String> market(String email);


    @Query(value = "SELECT NAME from alcohol WHERE CODE = :code", nativeQuery = true)
    String alcoholname(Long code);

    @Query(value = "SELECT marketname from market WHERE marketcode= :marketcode", nativeQuery = true)
    String marketname(Long marketcode);


    //해당 매장에서 해당 술을 장바구니에 넣었는지
    Optional<Shoppingbasket> findByUser_EmailAndStock_Stocknumber(String email, Long stock);

    void deleteByUser_EmailAndStock_Stocknumber(String email, Long stock);


    //장바구니의 물품 번호 추출
    @Query(value = "SELECT stocknumber FROM shoppingbasket WHERE stocknumber = :stocknumber", nativeQuery = true)
    Long numbercheck(Long stocknumber);


    @Query(value = "SELECT amount FROM shoppingbasket WHERE email = :email AND stocknumber = :stocknumber", nativeQuery = true)
    int amount(String email, Long stocknumber);


}
