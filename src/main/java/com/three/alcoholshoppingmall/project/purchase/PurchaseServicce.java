package com.three.alcoholshoppingmall.project.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServicce {
    private final PurchaseRepository purchaseRepository;
    public List<Purchase> PICKUPlist(String email, Ordertype ordertype) {
        List<Purchase>  list = purchaseRepository.Pickuplist(email);
        return list;
    }

    public List<Purchase> DELIVERYlist(String email, Ordertype ordertype) {
        List<Purchase>  list = purchaseRepository.Deliverylist(email);
        return list;
    }

    public List<Purchase> PICKUPlimt(String email, Ordertype ordertype) {
        List<Purchase> list = purchaseRepository.Pickuplimt(email);
        return list;
    }

    public List<Purchase> DELIVERYLIMTlimt(String email, Ordertype ordertype) {
        List<Purchase> list = purchaseRepository.Deliverylimt(email);
        return list;
    }
}
