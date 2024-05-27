package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.purchase.Delivery;
import com.three.alcoholshoppingmall.project.stock.Stock;
import com.three.alcoholshoppingmall.project.stock.StockCode;
import com.three.alcoholshoppingmall.project.stock.StockRepository;
import com.three.alcoholshoppingmall.project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<Shopping> Shoppinglist(String email, Delivery delivery) {
        List<Shopping> list = new ArrayList<>();

        List<Detailbasket> shoppingbaskets;
        List<String> alcoholname;
        List<String> marketname;
        List<String> Picture;

        if (delivery.equals(Delivery.Delivery)) {
            shoppingbaskets = detailbasketRepository.DetailbasketDelivery(email);
            alcoholname = detailbasketRepository.alcoholDelivery(email);
            marketname = detailbasketRepository.marketDelivery(email);
            Picture = detailbasketRepository.PictureDelivery(email);
        } else {
            shoppingbaskets = detailbasketRepository.DetailbasketPickUp(email);
            alcoholname = detailbasketRepository.alcoholPickUp(email);
            marketname = detailbasketRepository.marketPickUp(email);
            Picture = detailbasketRepository.PicturePickUp(email);
        }

        int size = shoppingbaskets.size();
        for (int i = 0; i < size; i++) {
            Detailbasket check = shoppingbaskets.get(i);
            String alcoholName = (alcoholname.size() > i) ? alcoholname.get(i) : null;
            String marketName = (marketname.size() > i) ? marketname.get(i) : null;
            String picturecode = (Picture.size() > i) ? Picture.get(i) : null;

            if (alcoholName != null && marketName != null) {
                Shopping shopping = Shopping.builder()
                        .stock(check.getStock().getStocknumber())
                        .name(alcoholName)
                        .marketname(marketName)
                        .amount(check.getAmount())
                        .price(check.getPrice())
                        .picture(picturecode)
                        .delivery(check.getDelivery())
                        .build();

                list.add(shopping);
            }
        }
        return list;
    }

    public List<Shopping> Shopping(User user, DetailbasketDTO detailbasketDTO) {
        Detailbasket detailbasket;
        List<Shopping> list = new ArrayList<>();
        Optional<Long> stock = detailbasketRepository.stocknumber(detailbasketDTO.getAlcoholcode(), detailbasketDTO.getMarketname());
        if (stock.isPresent()) {
            Long stocknumver = stock.get();
            Optional<Detailbasket> basket = detailbasketRepository.basket(user.getEmail(), stocknumver);
            if (basket.isEmpty()) {
                Delivery Type = detailbasketRepository.type(stocknumver);
                if (Type.equals(detailbasketDTO.getDelivery()) || Type.equals(Delivery.Delivery)) {
                    Shoppingbasket shoppingbasket = shoppingbasketRepository.findByUser_Email(user.getEmail());
                    Stock stockcheck = stockRepository.findByStocknumber(stocknumver);
                    int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
                    int totalprice = alcoholprice * detailbasketDTO.getAmount();

                    detailbasket = Detailbasket.builder()
                            .shoppingbasket(shoppingbasket)
                            .stock(stockcheck)
                            .amount(detailbasketDTO.getAmount())
                            .price(totalprice)
                            .delivery(detailbasketDTO.getDelivery())
                            .build();

                    detailbasketRepository.save(detailbasket);

                    String alcohol = shoppingbasketRepository.alcoholname(detailbasket.getStock().getAlcohol().getCode());
                    String market = shoppingbasketRepository.marketname(detailbasket.getStock().getMarket().getMarketcode());
                    String Picture = shoppingbasketRepository.pictur(detailbasket.getStock().getAlcohol().getCode());

                    Shopping shopping = Shopping.builder()
                            .stock(detailbasket.getStock().getStocknumber())
                            .name(alcohol)
                            .marketname(market)
                            .amount(detailbasket.getAmount())
                            .price(detailbasket.getPrice())
                            .picture(Picture)
                            .delivery(detailbasket.getDelivery())
                            .build();

                    list.add(shopping);
                } else {
                    throw new BizException(ErrorCode.ERRORDELIVERYTYPE);
                }
            }
            if (basket.isPresent()) {
                Stock stockcheck = stockRepository.findByStocknumber(stocknumver);
                String alcohols = shoppingbasketRepository.alcoholname(stockcheck.getAlcohol().getCode());
                String market = shoppingbasketRepository.marketname(stockcheck.getMarket().getMarketcode());
                String Picture = shoppingbasketRepository.pictur(stockcheck.getAlcohol().getCode());

                int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
                int Beforeamount = detailbasketRepository.amount(user.getEmail(), stocknumver);
                int plusamount = detailbasketDTO.getAmount() + Beforeamount;
                int plusPrice = plusamount * alcoholprice;

                Detailbasket shoppingbasketcheck = basket.get();
                shoppingbasketcheck.setPrice(plusPrice);
                shoppingbasketcheck.setAmount(plusamount);
                detailbasketRepository.save(shoppingbasketcheck);

                Shopping shopping = Shopping.builder()
                        .stock(stockcheck.getStocknumber())
                        .name(alcohols)
                        .marketname(market)
                        .amount(plusamount)
                        .price(plusPrice)
                        .picture(Picture)
                        .delivery(shoppingbasketcheck.getDelivery())
                        .build();

                list.add(shopping);
            }
            return list;
        } else {
            throw new BizException(ErrorCode.ERRORDELIVERYTYPE);
        }
    }


    @Transactional
    public ResponseEntity<String> Delete(User user, StockCode stockCode) {
        List<Shopping> list = new ArrayList<>();
        Optional<Long> stock = detailbasketRepository.stocknumber(stockCode.getAlcoholcode(), stockCode.getMarketname());
        if (stock.isPresent()) {
            Long stocknumver = stock.get();
            Optional<Detailbasket> basket = detailbasketRepository.basket(user.getEmail(), stocknumver);
            if (basket.isPresent()) {
                detailbasketRepository.deldete(user.getEmail(), stocknumver);
                return ResponseEntity.status(HttpStatus.OK).body("해당이 제품이 장바구니에서 삭제되었습니다.");
            } else {
                throw new BizException(ErrorCode.NOTFOUNDSHPPING);
            }
        }
        else {
            throw new BizException(ErrorCode.ERRORDELIVERYTYPE);
        }
    }


    public List<Shopping> Put(User user, DetailbasketDTO detailbasketDTO) {
        Optional<Long> stock = detailbasketRepository.stocknumber(detailbasketDTO.getAlcoholcode(), detailbasketDTO.getMarketname());
        if(stock.isPresent()){
        Long stocknumber = stock.get();
        Optional<Detailbasket> basket = detailbasketRepository.basket(
                user.getEmail(), stocknumber);
        if (basket.isPresent()) {
            Delivery Type = detailbasketRepository.type(stocknumber);
            if (Type.equals(detailbasketDTO.getDelivery()) || Type.equals(Delivery.Delivery)) {
                Stock stockcheck = stockRepository.findByStocknumber(stocknumber);
                int alcoholprice = alcoholRepository.Price(stockcheck.getAlcohol().getCode());
                int totalprice = alcoholprice * detailbasketDTO.getAmount();
                Detailbasket exbasket = basket.get();
                exbasket.setAmount(detailbasketDTO.getAmount());
                exbasket.setPrice(totalprice);
                exbasket.setDelivery(detailbasketDTO.getDelivery());
                detailbasketRepository.save(exbasket);
            } else {
                throw new BizException(ErrorCode.ERRORDELIVERYTYPE);
            }
        } else {
            throw new BizException(ErrorCode.NOTFOUNDSHPPING);
        }
        List<Shopping> list = new ArrayList<>();
        String type = String.valueOf(detailbasketDTO.getDelivery());
        List<Detailbasket> shoppingbaskets = detailbasketRepository.baskets(user.getEmail(), type);
        List<String> alcoholname = detailbasketRepository.alcohol(user.getEmail(), type);
        List<String> marketname = detailbasketRepository.market(user.getEmail(), type);
        List<String> Picture = detailbasketRepository.Picture(user.getEmail(), type);

        for (int i = 0; i < shoppingbaskets.size(); i++) {
            Detailbasket check = shoppingbaskets.get(i);
            String alcoholName = alcoholname.isEmpty() ? null : alcoholname.get(i);
            String marketName = marketname.isEmpty() ? null : marketname.get(i);
            String picturecode = Picture.isEmpty() ? null : Picture.get(i);

            Shopping shopping = Shopping.builder()
                    .stock(check.getStock().getStocknumber())
                    .name(alcoholName)
                    .marketname(marketName)
                    .amount(check.getAmount())
                    .price(check.getPrice())
                    .picture(picturecode)
                    .delivery(check.getDelivery())
                    .build();
            list.add(shopping);
        }
        return list;
    }
        else {
            throw new BizException(ErrorCode.ERRORDELIVERYTYPE);
        }
    }
}

