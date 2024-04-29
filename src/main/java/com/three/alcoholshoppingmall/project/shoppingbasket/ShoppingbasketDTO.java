package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.three.alcoholshoppingmall.project.purchase.Delivery;
import com.three.alcoholshoppingmall.project.stock.Stock;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingbasketDTO {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingnumber;

    private User user;

    private Long stock;

    private  int amount;

    private int price;


}
