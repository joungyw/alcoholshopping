package com.three.alcoholshoppingmall.project.alcohol;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {

    //가장 많이 팔린 술 8개
    @Query(value = "SELECT a.* FROM alcohol a LEFT JOIN (\n" +
            "    SELECT s.code, COUNT(p.ordernumber) AS total_orders\n" +
            "    FROM purchase p\n" +
            "    JOIN shoppingbasket sb ON p.shoppingnumber = sb.shoppingnumber\n" +
            "    JOIN stock s ON sb.stocknumber = s.stocknumber\n" +
            "    WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "    GROUP BY s.code ) AS p ON a.code = p.code\n" +
            "ORDER BY COALESCE(total_orders, 0) DESC, a.code ASC\n" +
            "LIMIT 8", nativeQuery = true)
    List<Alcohol> mostsold();

    @Query(value = "SELECT a.*, COALESCE(p.total_orders, 0) AS total_orders\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN (\n" +
            "    SELECT s.code, COUNT(p.ordernumber) AS total_orders\n" +
            "    FROM purchase p\n" +
            "    JOIN shoppingbasket sb ON p.shoppingnumber = sb.shoppingnumber\n" +
            "    JOIN stock s ON sb.stocknumber = s.stocknumber\n" +
            "    JOIN alcohol a ON s.code = a.code\n" +
            "    GROUP BY s.code \n" +
            ") AS p ON a.code = p.code\n" +
            "WHERE a.maincategory = :maincategory\n" +
            "ORDER BY COALESCE(p.total_orders, 0) DESC, a.code ASC \n" +
            "LIMIT 8", nativeQuery = true)
    List<Alcohol> most(String maincategory);

    @Query(value = "SELECT * FROM alcohol ORDER BY code DESC LIMIT 8", nativeQuery = true)
    List<Alcohol> newproduct(); //신제품

    List<Alcohol> findByMaincategory(String maincategory); // 대분류로 주류 검색하기

    List<Alcohol> findBySubcategory(String subcategory); // 소분류로 주류 검색하기

    List<Alcohol> findByNameContaining(String name);// 이름으로 주류 검색하기


    //술 평점
    @Query(value = "SELECT ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade \n" +
            "FROM alcohol a LEFT JOIN review b ON a.code = b.code \n" +
            "GROUP BY a.code", nativeQuery = true)
    List<Double> Ratingaverage();

    //술 재고
    @Query(value = "SELECT COALESCE(SUM(b.amount), 0) AS total_amount \n" +
            "FROM alcohol a \n" +
            "LEFT JOIN stock b ON a.code = b.code \n" +
            "GROUP BY a.name", nativeQuery = true)
    List<Integer> Allamount();

    List<Alcohol> findByName(String name);

    //특정 술의 평점
    @Query(value = "SELECT ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade \n" +
            "FROM alcohol a LEFT JOIN review b ON a.code = b.code \n" +
            "WHERE a.name = :name GROUP BY a.name\n", nativeQuery = true)
    Double Rating(String name);

    //특정 술의 가격
    @Query(value = "SELECT price FROM alcohol WHERE code= :code", nativeQuery = true)
    int Price(Long code);

    @Query(value = "SELECT price FROM alcohol WHERE name = :name", nativeQuery = true)
    int Price(String name);



    // 인기순
    @Query(value = "SELECT a.* FROM alcohol a LEFT JOIN (\n" +
            "    SELECT s.code, COUNT(p.ordernumber) AS total_orders\n" +
            "    FROM purchase p\n" +
            "    JOIN shoppingbasket sb ON p.shoppingnumber = sb.shoppingnumber\n" +
            "    JOIN stock s ON sb.stocknumber = s.stocknumber\n" +
            "    WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "    GROUP BY s.code ) AS p ON a.code = p.code\n" +
            "ORDER BY COALESCE(total_orders, 0) DESC, a.code ASC", nativeQuery = true)
    List<Alcohol> pop();

    // 인기순 평점
    @Query(value = "SELECT COALESCE(AVG(r.grade), 0) AS avg_rating FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "LEFT JOIN (\n" +
            "    SELECT s.code, SUM(sb.amount) AS purchase_amount \n" +
            "    FROM purchase p \n" +
            "    JOIN shoppingbasket sb ON p.shoppingnumber = sb.shoppingnumber \n" +
            "    JOIN stock s ON sb.stocknumber = s.stocknumber \n" +
            "    GROUP BY s.code\n" +
            ") b ON a.code = b.code \n" +
            "GROUP BY a.code \n" +
            "ORDER BY COALESCE(MAX(b.purchase_amount), 0) DESC, a.code ASC", nativeQuery = true)
    List<Double> popratings();

    // 인기순 가격

    // 인기순 리뷰 갯수
    @Query(value = "SELECT COALESCE(COUNT(r.writing), 0) AS review_count \n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "LEFT JOIN (\n" +
            "    SELECT s.code, SUM(sb.amount) AS purchase_amount \n" +
            "    FROM purchase p \n" +
            "    JOIN shoppingbasket sb ON p.shoppingnumber = sb.shoppingnumber \n" +
            "    JOIN stock s ON sb.stocknumber = s.stocknumber \n" +
            "    GROUP BY s.code\n" +
            ") b ON a.code = b.code \n" +
            "GROUP BY a.code \n" +
            "ORDER BY COALESCE(MAX(b.purchase_amount), 0) DESC, a.code ASC", nativeQuery = true)
    List<Integer> popreviewCount();

    // 최대 가격 순 정렬
    @Query(value = "SELECT * FROM alcohol ORDER BY price DESC, code ASC", nativeQuery = true)
    List<Alcohol> max();

    // 최대 가격 순 정렬시 평점 정렬
    @Query(value = "SELECT COALESCE(AVG(r.grade), 0) AS ratingaverage\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "GROUP BY a.code, a.name\n" +
            "ORDER BY COALESCE(MAX(a.price), 0) DESC, a.code ASC", nativeQuery = true)
    List<Double> maxratings();


    // 최대 가격 순 정렬시 리뷰 갯수
    @Query(value = "SELECT COALESCE(COUNT(r.writing), 0) AS review_count\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "GROUP BY a.code \n" +
            "ORDER BY COALESCE(MAX(a.price), 0) DESC, a.code ASC", nativeQuery = true)
    List<Integer> maxreviewCount();

    // 최소 가격 순 정렬
    @Query(value = "SELECT * FROM alcohol ORDER BY price ASC, code ASC", nativeQuery = true)
    List<Alcohol> min();

    // 최소 가격 순 정렬시 평점 정렬
    @Query(value = "SELECT COALESCE(AVG(r.grade), 0) AS ratingaverage\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "GROUP BY a.code, a.name\n" +
            "ORDER BY COALESCE(MIN(a.price), 0) ASC, a.code ASC", nativeQuery = true)
    List<Double> minratings();


    // 최소 가격 순 정렬시 리뷰 갯수
    @Query(value = "SELECT COALESCE(AVG(r.grade), 0) AS ratingaverage\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review r ON a.code = r.code \n" +
            "GROUP BY a.code, a.name\n" +
            "ORDER BY COALESCE(MIN(a.price), 0) ASC, a.code ASC", nativeQuery = true)
    List<Integer> minreviewCount();

    Alcohol findByCode(Long code);
}
