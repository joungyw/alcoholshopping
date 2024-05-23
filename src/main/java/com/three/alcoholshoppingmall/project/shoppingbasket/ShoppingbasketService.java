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
    private final AlcoholRepository alcoholRepository;
    private final DetailbasketRepository detailbasketRepository;

    public List<Shopping> Shoppinglist(String email) {
        List<Shopping> list = new ArrayList<>();

        List<Detailbasket> shoppingbaskets = detailbasketRepository.Detailbasket(email);
        List<String> alcoholname = detailbasketRepository.alcohol(email);
        List<String> marketname = detailbasketRepository.market(email);
        List<String> Picture = detailbasketRepository.Picture(email);

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Detailbasket check = shoppingbaskets.get(i);
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
                        .delivery(detailbasketRepository.delivery(check.getStock().getStocknumber()))
                        .build();

                list.add(shopping);
            }
        }
        return list;
    }

    public List<Shopping> Shopping(User user, DetailbasketDTO detailbasketDTO) {

        Detailbasket detailbasket;
        List<Shopping> list = new ArrayList<>();
        Optional<Detailbasket> basket = detailbasketRepository.basket(user.getEmail(), detailbasketDTO.getStock());

        if (basket.isEmpty()) {
            Shoppingbasket shoppingbasket = shoppingbasketRepository.findByUser_Email(user.getEmail());
            Stock stockcheck = stockRepository.findByStocknumber(detailbasketDTO.getStock());
            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int totalprice = alcoholprice * detailbasketDTO.getAmount();

            detailbasket = Detailbasket.builder()
                    .shoppingbasket(shoppingbasket)
                    .stock(stockcheck)
                    .amount(detailbasketDTO.getAmount())
                    .price(totalprice)
                    .build();

            detailbasketRepository.save(detailbasket);

            String alcohol = shoppingbasketRepository.alcoholname(detailbasket.getStock().getAlcohol().getCode());
            String market = shoppingbasketRepository.marketname(detailbasket.getStock().getMarket().getMarketcode());
            String Picture = shoppingbasketRepository.pictur(detailbasket.getStock().getAlcohol().getCode());

            Shopping shopping = Shopping
                    .builder()
                    .stock(detailbasket.getStock().getStocknumber())
                    .name(alcohol)
                    .marketname(market)
                    .amount(detailbasket.getAmount())
                    .price(detailbasket.getPrice())
                    .picture(Picture)
                    .delivery(detailbasketRepository.delivery(detailbasket.getStock().getStocknumber()))
                    .build();

            list.add(shopping);
        } else {

            Stock stockcheck = stockRepository.findByStocknumber(detailbasketDTO.getStock());

            String alcohols = shoppingbasketRepository.alcoholname(stockcheck.getAlcohol().getCode());
            String market = shoppingbasketRepository.marketname(stockcheck.getMarket().getMarketcode());
            String Picture = shoppingbasketRepository.pictur(stockcheck.getAlcohol().getCode());

            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int Beforeamount = detailbasketRepository.amount(user.getEmail(), detailbasketDTO.getStock());
            int plusamount = detailbasketDTO.getAmount() + Beforeamount;
            int plusPrice = plusamount * alcoholprice;

            Detailbasket shoppingbasketcheck = basket.get();
            shoppingbasketcheck.setPrice(plusPrice);
            shoppingbasketcheck.setAmount(plusamount);
            detailbasketRepository.save(shoppingbasketcheck);

            Shopping shopping = Shopping
                    .builder()
                    .stock(stockcheck.getStocknumber())
                    .name(alcohols)
                    .marketname(market)
                    .amount(plusamount)
                    .price(plusPrice)
                    .picture(Picture)
                    .delivery(detailbasketRepository.delivery(shoppingbasketcheck.getStock().getStocknumber()))
                    .build();

            list.add(shopping);
        }
        return list;
    }

    @Transactional
    public List<Shopping> Delete(User user, DetailbasketDTO detailbasketDTO) {
        Optional<Detailbasket> basket = detailbasketRepository.basket(user.getEmail(), detailbasketDTO.getStock());
        if (basket.isPresent()) {
            detailbasketRepository.deldete(user.getEmail(), detailbasketDTO.getStock());
        } else {
            throw new BizException(ErrorCode.NOTFOUNDSHPPING);
        }
        List<Shopping> list = new ArrayList<>();
        List<Detailbasket> shoppingbaskets = detailbasketRepository.baskets(user.getEmail());
        List<String> alcoholname = detailbasketRepository.alcohol(user.getEmail());
        List<String> marketname = detailbasketRepository.market(user.getEmail());
        List<String> Picture = detailbasketRepository.Picture(user.getEmail());

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Detailbasket check = shoppingbaskets.get(i);
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
                        .delivery(detailbasketRepository.delivery(check.getStock().getStocknumber()))
                        .build();

                list.add(shopping);
            }
        }
        return list;
    }

    public List<Shopping> Put(User user, DetailbasketDTO detailbasketDTO) {
        Optional<Detailbasket> basket = detailbasketRepository.basket(
                user.getEmail(), detailbasketDTO.getStock());
        if(basket.isPresent()){
            Long number = detailbasketRepository.numbercheck(detailbasketDTO.getStock());
            Stock stockcheck = stockRepository.findByStocknumber(number);
            int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
            int totalprice = alcoholprice * detailbasketDTO.getAmount();

            Detailbasket exbasket = basket.get();
            exbasket.setAmount(detailbasketDTO.getAmount());
            exbasket.setPrice(totalprice);
            detailbasketRepository.save(exbasket);

        }
        else {
            throw new BizException(ErrorCode.NOTFOUNDSHPPING);
        }
        List<Shopping> list = new ArrayList<>();
        List<Detailbasket> shoppingbaskets = detailbasketRepository.baskets(user.getEmail());
        List<String> alcoholname = detailbasketRepository.alcohol(user.getEmail());
        List<String> marketname = detailbasketRepository.market(user.getEmail());
        List<String> Picture = detailbasketRepository.Picture(user.getEmail());

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Detailbasket check = shoppingbaskets.get(i);
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
                        .delivery(detailbasketRepository.delivery(check.getStock().getStocknumber()))
                        .build();

                list.add(shopping);
            }
        }
        return list;

    }

    public List<StockNumber> Stock(StockDTO stockDTO) {

        Optional<Stock> number = stockRepository.findByAlcohol_CodeAndMarket_Marketcode(
                stockDTO.getAlcohol(), stockDTO.getMarket());

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

