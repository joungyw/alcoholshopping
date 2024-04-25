package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.market.MarketRepository;
import com.three.alcoholshoppingmall.project.purchase.Delivery;
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
    private final MarketRepository marketRepository;

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
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByEmailAndNameAndMarketname(shoppingbasketDTO.getEmail(),shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());

        if (basket.isEmpty()) {
            int alcoholprice = stockRepository.checkprice(shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
            int totalprice = alcoholprice * shoppingbasketDTO.getAmount();
           Delivery type = marketRepository.deliverytype(shoppingbasketDTO.getMarketname());
            shoppingbasket = Shoppingbasket.builder()
                    .email(shoppingbasketDTO.getEmail())
                    .name(shoppingbasketDTO.getName())
                    .marketname(shoppingbasketDTO.getMarketname())
                    .amount(shoppingbasketDTO.getAmount())
                    .price(totalprice)
                    .delivery(type)
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
            int alcoholprice = stockRepository.checkprice(shoppingbasketDTO.getName(), shoppingbasketDTO.getMarketname());
            int totalprice = alcoholprice * shoppingbasketDTO.getAmount();
            Shoppingbasket exbasket = basket.get();
            exbasket.setAmount(shoppingbasketDTO.getAmount());
            exbasket.setPrice(totalprice);
            shoppingbasketRepository.save(exbasket);
        }
        else {
            throw new IllegalStateException("해당 내용은 장바구니에 없습니다.");
        }
        List<Shoppingbasket> list = shoppingbasketRepository.findByEmail(shoppingbasketDTO.getEmail());
        return list;

    }
}
