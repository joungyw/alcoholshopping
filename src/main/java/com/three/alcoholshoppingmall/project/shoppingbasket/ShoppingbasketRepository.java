package com.three.alcoholshoppingmall.project.shoppingbasket;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingbasketRepository extends JpaRepository<Shoppingbasket, Long> {

    //장바구니 조회
    List<Shoppingbasket> findByEmail(String email);

    //해당 매장에서 해당 술을 장바구니에 넣었는지
    Optional<Shoppingbasket> findByEmailAndNameAndMarketname(String email, String name, String marketname);

    void deleteByEmailAndNameAndMarketname(String email, String name, String marketname);
}
