package com.three.alcoholshoppingmall.project.shoppingbasket;

import com.three.alcoholshoppingmall.project.stock.Stock;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Builder
public class Shopping {

    private Long stock;

    private String name;

    private String marketname;

    private  int amount;

    private int price;

    private String picture;

}
