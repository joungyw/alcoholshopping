package com.three.alcoholshoppingmall.project.purchase;

import com.three.alcoholshoppingmall.project.shoppingbasket.ShoppingbasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ShoppingbasketRepository shoppingbasketRepository;

    public List<Purchaseshow> PICKUPlist(String email) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Pickuplist(email);

        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholspick(email);
            List<String> marketList = purchaseRepository.marketspick(email);

            int size = Math.min(alcoholList.size(), marketList.size());
            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(purchase.getAmount())
                        .price(purchase.getPrice())
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

    public List<Purchaseshow> DELIVERYlist(String email) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylist(email);
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdelivery(email);
            List<String> marketList = purchaseRepository.marketsdelivery(email);

            int size = Math.min(alcoholList.size(), marketList.size());
            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(purchase.getAmount())
                        .price(purchase.getPrice())
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

    public List<Purchaseshow> PICKUPlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Pickuplimt(email);
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholspicklimt(email);
            List<String> marketList = purchaseRepository.marketspicklimt(email);

            int size = Math.min(alcoholList.size(), marketList.size());
            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(purchase.getAmount())
                        .price(purchase.getPrice())
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

    public List<Purchaseshow> DELIVERYLIMTlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylimt(email);
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdeliverylimt(email);
            List<String> marketList = purchaseRepository.marketsdeliverylimt(email);

            int size = Math.min(alcoholList.size(), marketList.size());
            for (int i = 0; i < size; i++) {
                String alcoholName = alcoholList.get(i);
                String marketName = marketList.get(i);

                Purchaseshow purchaseshow = Purchaseshow.builder()
                        .alcoholname(alcoholName)
                        .marketname(marketName)
                        .amount(purchase.getAmount())
                        .price(purchase.getPrice())
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