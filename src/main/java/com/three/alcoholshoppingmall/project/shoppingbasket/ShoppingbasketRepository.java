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
            "WHERE c.email = :email",nativeQuery = true)
    List<String> alcohol(String email);

    @Query(value = "SELECT a.marketname\n" +
            "FROM market a \n" +
            "JOIN stock b ON a.marketcode = b.marketcode\n" +
            "JOIN shoppingbasket c ON b.stocknumber = c.stocknumber \n" +
            "WHERE c.email = :email",nativeQuery = true)
    List<String> market(String email);


@Query(value = "SELECT a.name \n" +
        "        FROM alcohol a \n" +
        "        JOIN stock b ON a.code = b.code\n" +
        "        JOIN shoppingbasket c ON b.stocknumber = c.stocknumber\n" +
        "        WHERE c.shoppingnumber = :shoppingnumber",nativeQuery = true)
    String alcoholname(Long shoppingnumber);

    @Query(value = "SELECT a.marketname \n" +
            "        FROM market a \n" +
            "        JOIN stock b ON a.marketcode = b.marketcode\n" +
            "        JOIN shoppingbasket c ON b.stocknumber = c.stocknumber\n" +
            "        WHERE c.shoppingnumber = :shoppingnumber",nativeQuery = true)
    String marketname(Long shoppingnumber);




    //해당 매장에서 해당 술을 장바구니에 넣었는지
    Optional<Shoppingbasket> findByUser_EmailAndShoppingnumber(String email, Long shoppingnumber);

    void deleteByUser_EmailAndShoppingnumber(String email, Long shoppingnumber);


    //장바구니의 물품 번호 추출
    @Query(value = "SELECT stocknumber FROM shoppingbasket WHERE shoppingnumber = :shoppingnumber",nativeQuery = true)
    Long numbercheck(Long shoppingnumber);


    @Query(value = "SELECT a.amount\n" +
            "FROM shoppingbasket a \n" +
            "JOIN purchase b ON a.shoppingnumber = b.shoppingnumber\n" +
            "WHERE a.shoppingnumber = :shoppingnumber",nativeQuery = true)
    List<Integer> amount(Long shoppingnumber);

    @Query(value = "SELECT a.price\n" +
            "FROM shoppingbasket a \n" +
            "JOIN purchase b ON a.shoppingnumber = b.shoppingnumber\n" +
            "WHERE a.shoppingnumber = :shoppingnumber",nativeQuery = true)
    List<Integer> price(Long shoppingnumber);

}
