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

    //해당 매장에서 해당 술을 장바구니에 넣었는지
    Optional<Shoppingbasket> findByUser_EmailAndShoppingnumber(String email, Long shoppingnumber);

    void deleteByUser_EmailAndShoppingnumber(String email, Long shoppingnumber);


    //장바구니의 물품 번호 추출
    @Query(value = "SELECT stocknumber FROM shoppingbasket WHERE shoppingnumber = :shoppingnumber",nativeQuery = true)
    Long numbercheck(Long shoppingnumber);


}
