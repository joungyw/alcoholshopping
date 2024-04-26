package com.three.alcoholshoppingmall.project.purchase;


import com.three.alcoholshoppingmall.project.market.Ordertype;
import com.three.alcoholshoppingmall.project.user.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@DynamicInsert
@Schema(title = "DB 유효성 검사하는 회원의 구매 정보")
public class PurchaseDTO {


    private Long id;


    private String email;

    private String name;

    private String marketname;

    private Ordertype ordertype;

    private int price;

    private Division division;

    private Gender gender;

    private String address;

    private LocalDate purchaseday;

    private int age;

    private String OrderNumber;

    private String nickname;
}

