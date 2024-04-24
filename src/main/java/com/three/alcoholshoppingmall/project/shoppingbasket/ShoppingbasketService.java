package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.stock.Stock;
import com.three.alcoholshoppingmall.project.stock.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingbasketService {

    private final ShoppingbasketRepository shoppingbasketRepository;
    private final StockRepository stockRepository;

    public List<Shoppingbasket> Shoppinglist(String email) {

        List<Shoppingbasket> list = shoppingbasketRepository.findByEmail(email);

        if (list == null) {
            return null;
        } else {
            return list;
        }
    }

    public List<Shoppingbasket> Shopping(ShoppingbasketDTO shoppingbasketDTO) {
        Shoppingbasket shoppingbasket;
        List<Shoppingbasket> list = new ArrayList<>();
        // 해당 매장의 해당 술의 재고량
        //int limt = stockRepository.limt(shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
        //장바구니에 해당 매장의 해당 술을 넣어 놨는지
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByEmailAndNameAndMarketname(shoppingbasketDTO.getEmail(),shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
        if (basket.isEmpty()) {
            shoppingbasket = Shoppingbasket.builder()
                    .email(shoppingbasketDTO.getEmail())
                    .name(shoppingbasketDTO.getName())
                    .marketname(shoppingbasketDTO.getMarketname())
                    .amount(shoppingbasketDTO.getAmount())
                    .build();
            Shoppingbasket basketsave = shoppingbasketRepository.save(shoppingbasket);
            list.add(basketsave);
        } else {
            throw new IllegalStateException("이미 장바구니에 있습니다.");
        }
        return list;
    }

    @Transactional
    public List<Shoppingbasket> Delete(ShoppingbasketDTO shoppingbasketDTO) {
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByEmailAndNameAndMarketname(shoppingbasketDTO.getEmail(),shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
        if(basket.isPresent()){
             shoppingbasketRepository.deleteByEmailAndNameAndMarketname(shoppingbasketDTO.getEmail(),shoppingbasketDTO.getName(),shoppingbasketDTO.getMarketname());
        }else {
            throw new IllegalStateException("해당 내용은 장바구니에 없습니다.");
        }
        List<Shoppingbasket> list = shoppingbasketRepository.findByEmail(shoppingbasketDTO.getEmail());
        return list;
    }

    public List<Shoppingbasket> Put(ShoppingbasketDTO shoppingbasketDTO) {
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByEmailAndNameAndMarketname(shoppingbasketDTO.getEmail(),shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
        if(basket.isPresent()){
            Shoppingbasket exbasket = basket.get();
            exbasket.setAmount(shoppingbasketDTO.getAmount());
            shoppingbasketRepository.save(exbasket);
        }
        else {
            throw new IllegalStateException("해당 내용은 장바구니에 없습니다.");
        }
        List<Shoppingbasket> list = shoppingbasketRepository.findByEmail(shoppingbasketDTO.getEmail());
        return list;

    }
}
