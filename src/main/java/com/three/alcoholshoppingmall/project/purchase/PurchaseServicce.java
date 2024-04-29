package com.three.alcoholshoppingmall.project.purchase;

import com.three.alcoholshoppingmall.project.shoppingbasket.Shoppingbasket;
import com.three.alcoholshoppingmall.project.shoppingbasket.ShoppingbasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServicce {
    private final PurchaseRepository purchaseRepository;
    private final ShoppingbasketRepository shoppingbasketRepository;

    public List<Purchaseshow> PICKUPlist(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Pickuplist(purchaseDTO.getUser().getEmail());

        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholspick(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketspick(purchase.getUser().getEmail());
            List<Integer> amounts = shoppingbasketRepository.amount(purchase.getShoppingbasket().getShoppingnumber());
            List<Integer> prices = shoppingbasketRepository.price(purchase.getShoppingbasket().getShoppingnumber());

            int size = Math.min(alcoholList.size(), Math.min(marketList.size(), Math.min(amounts.size(), prices.size())));

            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);
                int amount = amounts.get(i);
                int price = prices.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(amount)
                        .price(price)
                        .delivery(purchase.getDelivery())
                        .division(purchase.getDivision())
                        .address(purchase.getAddress())
                        .purchaseday(purchase.getPurchaseday())
                        .build();

                list.add(purchaseshow);
            }
        }

        return list;
    }

    public List<Purchaseshow> DELIVERYlist(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylist(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdelivery(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketsdelivery(purchase.getUser().getEmail());
            List<Integer> amounts = shoppingbasketRepository.amount(purchase.getShoppingbasket().getShoppingnumber());
            List<Integer> prices = shoppingbasketRepository.price(purchase.getShoppingbasket().getShoppingnumber());

            int size = Math.min(alcoholList.size(), Math.min(marketList.size(), Math.min(amounts.size(), prices.size())));

            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);
                int amount = amounts.get(i);
                int price = prices.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(amount)
                        .price(price)
                        .delivery(purchase.getDelivery())
                        .division(purchase.getDivision())
                        .address(purchase.getAddress())
                        .purchaseday(purchase.getPurchaseday())
                        .build();

                list.add(purchaseshow);
            }
        }

        return list;
    }

    public List<Purchaseshow> PICKUPlimt(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Pickuplimt(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholspicklimt(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketspicklimt(purchase.getUser().getEmail());
            List<Integer> amounts = shoppingbasketRepository.amount(purchase.getShoppingbasket().getShoppingnumber());
            List<Integer> prices = shoppingbasketRepository.price(purchase.getShoppingbasket().getShoppingnumber());

            int size = Math.min(alcoholList.size(), Math.min(marketList.size(), Math.min(amounts.size(), prices.size())));

            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);
                int amount = amounts.get(i);
                int price = prices.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(amount)
                        .price(price)
                        .delivery(purchase.getDelivery())
                        .division(purchase.getDivision())
                        .address(purchase.getAddress())
                        .purchaseday(purchase.getPurchaseday())
                        .build();

                list.add(purchaseshow);
            }
        }

        return list;
    }

    public List<Purchaseshow> DELIVERYLIMTlimt(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylimt(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdeliverylimt(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketsdeliverylimt(purchase.getUser().getEmail());
            List<Integer> amounts = shoppingbasketRepository.amount(purchase.getShoppingbasket().getShoppingnumber());
            List<Integer> prices = shoppingbasketRepository.price(purchase.getShoppingbasket().getShoppingnumber());

            int size = Math.min(alcoholList.size(), Math.min(marketList.size(), Math.min(amounts.size(), prices.size())));

            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);
                int amount = amounts.get(i);
                int price = prices.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(amount)
                        .price(price)
                        .delivery(purchase.getDelivery())
                        .division(purchase.getDivision())
                        .address(purchase.getAddress())
                        .purchaseday(purchase.getPurchaseday())
                        .build();

                list.add(purchaseshow);
            }
        }

        return list;
    }
}