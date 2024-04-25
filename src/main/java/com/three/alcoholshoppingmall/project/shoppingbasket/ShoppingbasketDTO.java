package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.purchase.Delivery;
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
    @Schema(title = "주류 ID", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Schema(title = "email", description = "사용자의 이메일 주소 입니다.")
    private String email;

    @Schema(title = "주류 명", description = "주류의 이름 입니다.")
    private String name;

    @Schema(title = "판매처 이름", description = "해당 주류를 판매하는 판매처의 이름 입니다.")
    private String marketname;


    @Schema(title = "amount", description = "해당 물품의 구매 수량입니다.")
    private  int amount;

    @Schema(title = "price", description = "해당 물품의 판매가 입니다.")
    private int price;

    @Schema(title = "배달 여부", description = "해당 매장의 배달여부 입니다")
    private Delivery delivery;


}
