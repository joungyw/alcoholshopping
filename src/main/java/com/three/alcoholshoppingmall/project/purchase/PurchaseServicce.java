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

    public List<Purchaseshow> DELIVERYlist(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylist(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdelivery(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketsdelivery(purchase.getUser().getEmail());

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

    public List<Purchaseshow> PICKUPlimt(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Pickuplimt(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholspicklimt(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketspicklimt(purchase.getUser().getEmail());

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

    public List<Purchaseshow> DELIVERYLIMTlimt(PurchaseDTO purchaseDTO) {
        List<Purchaseshow> list = new ArrayList<>();

        List<Purchase> check = purchaseRepository.Deliverylimt(purchaseDTO.getUser().getEmail());
        for (Purchase purchase : check) {
            List<String> alcoholList = purchaseRepository.alcoholsdeliverylimt(purchase.getUser().getEmail());
            List<String> marketList = purchaseRepository.marketsdeliverylimt(purchase.getUser().getEmail());

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