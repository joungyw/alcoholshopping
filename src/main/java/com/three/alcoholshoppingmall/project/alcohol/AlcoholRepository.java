package com.three.alcoholshoppingmall.project.alcohol;



import com.three.alcoholshoppingmall.project.favorites.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {

    //가장 많이 팔린 술 8개
    @Query(value = "SELECT a.* FROM alcohol a LEFT JOIN (\n" +
            "SELECT s.code, COUNT(p.ordernumber) AS total_orders\n" +
            "FROM purchase p JOIN stock s ON p.stocknumber = s.stocknumber\n" +
            "WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "GROUP BY s.code) AS p ON a.code = p.code\n" +
            "ORDER BY COALESCE(total_orders, 0) DESC, a.code ASC\n" +
            "LIMIT 3", nativeQuery = true)
    List<Alcohol> mostsold();


    //가장 많이 팔린술의 평점
    @Query(value = "SELECT  ROUND(COALESCE(AVG(r.grade), 0), 1) AS average_grade\n" +
            "FROM alcohol a LEFT JOIN review r ON a.code = r.code\n" +
            "LEFT JOIN (SELECT s.code, COUNT(p.ordernumber) AS total_orders\n" +
            "FROM purchase p JOIN stock s ON p.stocknumber = s.stocknumber\n" +
            "WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW()) GROUP BY s.code\n" +
            ") AS p ON a.code = p.code GROUP BY a.code\n" +
            "ORDER BY COALESCE(MAX(p.total_orders), 0) DESC, a.code ASC\n" +
            "LIMIT 3",nativeQuery = true)
    List<Double> mostsoldgrade();


    //랜덤
    @Query(value = "SELECT * FROM alcohol ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Alcohol> RAND();

    @Query(value = "SELECT ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade\n" +
            "FROM alcohol a\n" +
            "LEFT JOIN review b ON a.code = b.code\n" +
            "WHERE a.code = :code\n" +
            "GROUP BY a.code\n" +
            "LIMIT 3 ", nativeQuery = true)
    List<Double> Randgrade(Long code);



    //신제품
    @Query(value = "SELECT * FROM alcohol ORDER BY code DESC LIMIT 3", nativeQuery = true)
    List<Alcohol> newproduct();

    @Query(value = "SELECT ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade\n" +
            "FROM alcohol a LEFT JOIN review b ON a.code = b.code\n" +
            "GROUP BY a.code ORDER BY a.code DESC\n" +
            "LIMIT 3", nativeQuery = true)
    List<Double> newgrade();






    List<Alcohol> findByMaincategory(String maincategory); // 대분류로 주류 검색하기

    List<Alcohol> findBySubcategory(String subcategory); // 소분류로 주류 검색하기

    List<Alcohol> findByNameContaining(String name);// 이름으로 주류 검색하기


    //술 평점
    @Query(value = "SELECT " +
            "ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade \n" +
            "FROM alcohol a LEFT JOIN review b ON a.code = b.code \n" +
            "GROUP BY a.code", nativeQuery = true)
    List<Double> Ratingaverage();

    //술 재고
    @Query(value = "SELECT COALESCE(SUM(b.amount), 0) AS total_amount \n" +
            "FROM alcohol a \n" +
            "LEFT JOIN stock b ON a.code = b.code \n" +
            "GROUP BY a.name", nativeQuery = true)
    List<Integer> Allamount();

    Alcohol findByName(String name);


    //특정 술의 평점
    @Query(value = "SELECT ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade \n" +
            "FROM alcohol a LEFT JOIN review b ON a.code = b.code \n" +
            "WHERE a.name = :name GROUP BY a.name\n", nativeQuery = true)
    Double Rating(String name);

    @Query(value = "SELECT\n" +
            "        ROUND(COALESCE(AVG(b.grade), 0), 1) AS average_grade \n" +
            "\t\t  ,a.name,a.price,a.code\n" +
            "    FROM\n" +
            "        alcohol a \n" +
            "    LEFT OUTER JOIN\n" +
            "        review b \n" +
            "            ON a.code = b.code  \n" +
            "    GROUP BY\n" +
            "        a.name,a.price,a.code\n" +
            "\tHAVING a.name LIKE :name", nativeQuery = true)
    List<Double> RatingList(String name);

    //특정 술의 가격
    @Query(value = "SELECT price FROM alcohol WHERE code= :code", nativeQuery = true)
    int Price(Long code);



    // 인기순
    @Query(value = "SELECT a.* FROM alcohol a LEFT JOIN (\n" +
            "               SELECT s.code, COUNT(p.ordernumber) AS total_amount\n" +
            "                FROM purchase p\n" +
            "                JOIN stock s ON s.stocknumber = p.stocknumber\n" +
            "                WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "                GROUP BY s.code ) AS p ON a.code = p.code\n" +
            "            ORDER BY COALESCE(total_amount, 0) DESC, a.code ASC", nativeQuery = true)
    List<Alcohol> pop();

    // 인기순 평점
    @Query(value = "SELECT COALESCE(AVG(r.grade), 0) AS avg_rating FROM alcohol a\n" +
            "LEFT JOIN review r ON a.code = r.code\n" +
            "LEFT JOIN (\n" +
            "    SELECT s.code, SUM(p.amount) AS total_amount\n" +
            "    FROM purchase p\n" +
            "    JOIN stock s ON s.stocknumber = p.stocknumber\n" +
            "    WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "    GROUP BY s.code\n" +
            ") AS p ON a.code = p.code GROUP BY a.code\n" +
            "ORDER BY COALESCE(SUM(p.total_amount), 0) DESC, a.code ASC\n", nativeQuery = true)
    List<Double> popratings();

    // 인기순 리뷰 갯수
    @Query(value = "SELECT COALESCE(COUNT(r.writing), 0) AS review_count\n" +
            "FROM alcohol a\n" +
            "LEFT JOIN review r ON a.code = r.code\n" +
            "LEFT JOIN (\n" +
            "    SELECT s.code, SUM(p.amount) AS total_amount\n" +
            "    FROM purchase p\n" +
            "    JOIN stock s ON s.stocknumber = p.stocknumber\n" +
            "    WHERE YEARWEEK(p.purchaseday) = YEARWEEK(NOW())\n" +
            "    GROUP BY s.code\n" +
            ") AS p ON a.code = p.code\n" +
            "GROUP BY a.code\n" +
            "ORDER BY COALESCE(SUM(p.total_amount), 0) DESC, a.code ASC", nativeQuery = true)
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

    @Query(value = "SELECT name from alcohol where code = :code" , nativeQuery = true)
    String name(Long code);

    @Query(value = "SELECT a.name FROM alcohol a JOIN favorites b ON a.code = b.code WHERE b.email = :email", nativeQuery = true)
    List<String> MyFavoritesname(String email);
    @Query(value = "SELECT a.code FROM alcohol a JOIN favorites b ON a.code = b.code WHERE b.email = :email", nativeQuery = true)
    List<Long> MyFavoritescode(String email);

    @Query(value = "SELECT a.picture FROM alcohol a JOIN favorites b ON a.code = b.code WHERE b.email = :email", nativeQuery = true)
    List<String> MyFavoritespicture(String email);


    @Query(value = "SELECT a.*\n" +
            "FROM alcohol a\n" +
            "LEFT JOIN stock b ON a.code = b.code\n" +
            "LEFT JOIN market c ON c.marketcode = b.marketcode\n" +
            "WHERE c.delivery = :type\n" +
            "GROUP BY a.code ORDER BY a.code",nativeQuery = true)
    List<Alcohol> Markettype(String type);



    @Query(value = "SELECT COALESCE(AVG(b.grade), 0) AS ratingaverage\n" +
            "FROM alcohol a \n" +
            "LEFT JOIN review b ON a.code = b.code \n" +
            "LEFT JOIN stock c ON a.code = c.code \n" +
            "LEFT JOIN market d ON c.marketcode = d.marketcode\n" +
            "WHERE d.delivery = :type\n" +
            "GROUP BY a.code ORDER BY a.code",nativeQuery = true)
    List<Double> Markeratingaverage(String type);
}
