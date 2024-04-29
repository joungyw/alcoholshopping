package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.market.MarketRepository;
import com.three.alcoholshoppingmall.project.purchase.Delivery;
import com.three.alcoholshoppingmall.project.stock.Stock;
import com.three.alcoholshoppingmall.project.stock.StockRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
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
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final AlcoholRepository alcoholRepository;

    public List<Shoppingbasket> Shoppinglist(String email) {

        List<Shoppingbasket> list = shoppingbasketRepository.findByUser_Email(email);

        if (list == null) {
            return null;
        } else {
            return list;
        }
    }

    public List<Shoppingbasket> Shopping(ShoppingbasketDTO shoppingbasketDTO) {
        Shoppingbasket shoppingbasket;
        List<Shoppingbasket> list = new ArrayList<>();
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndShoppingnumber(shoppingbasketDTO.getUser().getEmail(),shoppingbasketDTO.getStock());

        if (basket.isEmpty()) {
            User usercheck = userRepository.findByEmail(shoppingbasketDTO.getUser().getEmail());
            Stock stockcheck = stockRepository.findByStocknumber(shoppingbasketDTO.getStock());
            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int totalprice = alcoholprice * shoppingbasketDTO.getAmount();

            shoppingbasket = Shoppingbasket.builder()
                    .user(usercheck)
                    .stock(stockcheck)
                    .amount(shoppingbasketDTO.getAmount())
                    .price(totalprice)
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
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndShoppingnumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getShoppingnumber());
        if(basket.isPresent()){
             shoppingbasketRepository.deleteByUser_EmailAndShoppingnumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getShoppingnumber());
        }else {
            throw new IllegalStateException("해당 내용은 장바구니에 없습니다.");
        }
        List<Shoppingbasket> list = shoppingbasketRepository.findByUser_Email(shoppingbasketDTO.getUser().getEmail());
        return list;
    }

    public List<Shoppingbasket> Put(ShoppingbasketDTO shoppingbasketDTO) {
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndShoppingnumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getShoppingnumber());
        if(basket.isPresent()){

            Long number = shoppingbasketRepository.numbercheck(shoppingbasketDTO.getShoppingnumber());
            Stock stockcheck = stockRepository.findByStocknumber(number);
            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int totalprice = alcoholprice * shoppingbasketDTO.getAmount();

            Shoppingbasket exbasket = basket.get();
            exbasket.setAmount(shoppingbasketDTO.getAmount());
            exbasket.setPrice(totalprice);
            shoppingbasketRepository.save(exbasket);

        }
        else {
            throw new IllegalStateException("해당 내용은 장바구니에 없습니다.");
        }
        List<Shoppingbasket> list = shoppingbasketRepository.findByUser_Email(shoppingbasketDTO.getUser().getEmail());
        return list;

    }
}
