package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.purchase.PurchaseRepository;
import com.three.alcoholshoppingmall.project.stock.Stock;
import com.three.alcoholshoppingmall.project.stock.StockDTO;
import com.three.alcoholshoppingmall.project.stock.StockNumber;
import com.three.alcoholshoppingmall.project.stock.StockRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingbasketService {

    private final ShoppingbasketRepository shoppingbasketRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final AlcoholRepository alcoholRepository;

    public List<Shopping> Shoppinglist(String email) {
        List<Shopping> list = new ArrayList<>();

        List<Shoppingbasket> shoppingbaskets = shoppingbasketRepository.findByUser_Email(email);
        List<String> alcoholname = shoppingbasketRepository.alcohol(email);
        List<String> marketname = shoppingbasketRepository.market(email);
        List<String> Picture = purchaseRepository.Picture(email);

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Shoppingbasket check = shoppingbaskets.get(i);
            String alcoholName = alcoholname.isEmpty() ? null : alcoholname.get(i);
            String marketName = marketname.isEmpty() ? null : marketname.get(i);
            String picturecode = Picture.isEmpty() ? null : Picture.get(i);

            if (alcoholName != null && marketName != null) {
                Shopping shopping = Shopping.builder()
                        .stock(check.getStock().getStocknumber())
                        .name(alcoholName)
                        .marketname(marketName)
                        .amount(check.getAmount())
                        .price(check.getPrice())
                        .picture(picturecode)
                        .build();

                list.add(shopping);
            }
        }
        return list;
    }


    public List<Shopping> Shopping(ShoppingbasketDTO shoppingbasketDTO) {
        Shoppingbasket shoppingbasket;
        List<Shopping> list = new ArrayList<>();
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndStock_Stocknumber(shoppingbasketDTO.getUser().getEmail(),shoppingbasketDTO.getStock());

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
          shoppingbasketRepository.save(shoppingbasket);

            String alcohol = shoppingbasketRepository.alcoholname(shoppingbasket.getStock().getAlcohol().getCode());
            String market = shoppingbasketRepository.marketname(shoppingbasket.getStock().getMarket().getMarketcode());
            String Picture = shoppingbasketRepository.pictur(shoppingbasket.getStock().getAlcohol().getCode());

          Shopping shopping = Shopping
                  .builder()
                  .stock(shoppingbasket.getStock().getStocknumber())
                  .name(alcohol)
                  .marketname(market)
                  .amount(shoppingbasket.getAmount())
                  .price(shoppingbasket.getPrice())
                  .picture(Picture)
                  .build();

            list.add(shopping);
        } else {

            Stock stockcheck = stockRepository.findByStocknumber(shoppingbasketDTO.getStock());

            String alcohols = shoppingbasketRepository.alcoholname(stockcheck.getAlcohol().getCode());
            String market = shoppingbasketRepository.marketname(stockcheck.getMarket().getMarketcode());
            String Picture = shoppingbasketRepository.pictur(stockcheck.getAlcohol().getCode());

            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int Beforeamount = shoppingbasketRepository.amount(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getStock());
            int plusamount = shoppingbasketDTO.getAmount() +Beforeamount ;
            int plusPrice = plusamount * alcoholprice;

            Shoppingbasket shoppingbasketcheck = basket.get();
            shoppingbasketcheck.setPrice(plusPrice);
            shoppingbasketcheck.setAmount(plusamount);
            shoppingbasketRepository.save(shoppingbasketcheck);

            Shopping shopping = Shopping
                    .builder()
                    .stock(stockcheck.getStocknumber())
                    .name(alcohols)
                    .marketname(market)
                    .amount(plusamount)
                    .price(plusPrice)
                    .picture(Picture)
                    .build();

            list.add(shopping);



        }
        return list;
    }

    @Transactional
    public List<Shopping> Delete(ShoppingbasketDTO shoppingbasketDTO) {
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndStock_Stocknumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getStock());
        if(basket.isPresent()){
             shoppingbasketRepository.deleteByUser_EmailAndStock_Stocknumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getStock());
        }else {
            throw new BizException(ErrorCode.NOTFOUNDSHPPING);
        }
        List<Shopping> list = new ArrayList<>();
        List<Shoppingbasket> shoppingbaskets = shoppingbasketRepository.findByUser_Email(shoppingbasketDTO.getUser().getEmail());
        List<String> alcoholname = shoppingbasketRepository.alcohol(shoppingbasketDTO.getUser().getEmail());
        List<String> marketname = shoppingbasketRepository.market(shoppingbasketDTO.getUser().getEmail());
        List<String> Picture = purchaseRepository.Picture(shoppingbasketDTO.getUser().getEmail());


        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Shoppingbasket check = shoppingbaskets.get(i);
            String alcoholName = alcoholname.isEmpty() ? null : alcoholname.get(i);
            String marketName = marketname.isEmpty() ? null : marketname.get(i);
            String picturecode = Picture.isEmpty() ? null : Picture.get(i);

            if (alcoholName != null && marketName != null) {
                Shopping shopping = Shopping.builder()
                        .stock(check.getStock().getStocknumber())
                        .name(alcoholName)
                        .marketname(marketName)
                        .amount(check.getAmount())
                        .price(check.getPrice())
                        .picture(picturecode)
                        .build();

                list.add(shopping);
            }
        }
        return list;
    }

    public List<Shopping> Put(ShoppingbasketDTO shoppingbasketDTO) {
        Optional<Shoppingbasket> basket = shoppingbasketRepository.findByUser_EmailAndStock_Stocknumber(shoppingbasketDTO.getUser().getEmail(), shoppingbasketDTO.getStock());
        if(basket.isPresent()){
            Long number = shoppingbasketRepository.numbercheck(shoppingbasketDTO.getStock());
            Stock stockcheck = stockRepository.findByStocknumber(number);
            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int totalprice = alcoholprice * shoppingbasketDTO.getAmount();

            Shoppingbasket exbasket = basket.get();
            exbasket.setAmount(shoppingbasketDTO.getAmount());
            exbasket.setPrice(totalprice);
            shoppingbasketRepository.save(exbasket);

        }
        else {
            throw new BizException(ErrorCode.NOTFOUNDSHPPING);
        }
        List<Shopping> list = new ArrayList<>();
        List<Shoppingbasket> shoppingbaskets = shoppingbasketRepository.findByUser_Email(shoppingbasketDTO.getUser().getEmail());
        List<String> alcoholname = shoppingbasketRepository.alcohol(shoppingbasketDTO.getUser().getEmail());
        List<String> marketname = shoppingbasketRepository.market(shoppingbasketDTO.getUser().getEmail());
        List<String> Picture = purchaseRepository.Picture(shoppingbasketDTO.getUser().getEmail());

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Shoppingbasket check = shoppingbaskets.get(i);
            String alcoholName = alcoholname.isEmpty() ? null : alcoholname.get(i);
            String marketName = marketname.isEmpty() ? null : marketname.get(i);
            String picturecode = Picture.isEmpty() ? null : Picture.get(i);

            if (alcoholName != null && marketName != null) {
                Shopping shopping = Shopping.builder()
                        .stock(check.getStock().getStocknumber())
                        .name(alcoholName)
                        .marketname(marketName)
                        .amount(check.getAmount())
                        .price(check.getPrice())
                        .picture(picturecode)
                        .build();

                list.add(shopping);
            }
        }
        return list;

    }

    public List<StockNumber> Stock(StockDTO stockDTO) {

        Optional<Stock> number = stockRepository.findByAlcohol_CodeAndMarket_Marketcode(
                stockDTO.getAlcohol(),stockDTO.getMarket());

        if (number.isEmpty()) {
            throw new BizException(ErrorCode.NOTFOUNDSTOCK);
        }

        List<StockNumber> list = new ArrayList<>();
        StockNumber stockNumber = StockNumber.builder()
                .stock(number.get().getStocknumber())
                .build();

        list.add(stockNumber);

        return list;
    }
}

