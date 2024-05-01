package com.three.alcoholshoppingmall.project.purchase;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Purchaseshow {


    private String alcoholname;

    private String marketname;

    private  int amount;

    private int price;

    private Delivery delivery;

    private Division division;

    private String address;

    private LocalDate purchaseday;
}