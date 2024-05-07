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
        List<String> alcoholList = purchaseRepository.alcoholspick(email);
        List<String> marketList = purchaseRepository.marketspick(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());

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

        return list;
    }

    public List<Purchaseshow> DELIVERYlist(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Deliverylist(email);
        List<String> alcoholList = purchaseRepository.alcoholsdelivery(email);
        List<String> marketList = purchaseRepository.marketsdelivery(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());

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
        return list;
    }

    public List<Purchaseshow> PICKUPlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Pickuplimt(email);
        List<String> alcoholList = purchaseRepository.alcoholspicklimt(email);
        List<String> marketList = purchaseRepository.marketspicklimt(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());

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
        return list;
    }

    public List<Purchaseshow> DELIVERYLIMTlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Deliverylimt(email);
        List<String> alcoholList = purchaseRepository.alcoholsdeliverylimt(email);
        List<String> marketList = purchaseRepository.marketsdeliverylimt(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());

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
        return list;
    }
}