package com.three.alcoholshoppingmall.project.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
@Schema(description = "stock 테이블에 대한 내용입니다.")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "주류 ID", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Schema(title = "주류 명", description = "주류의 이름 입니다.")
    private String name;

    @Schema(title = "판매처 이름", description = "해당 주류를 판매하는 판매처의 이름 입니다.")
    private String marketname;

    @Schema(title = "주류 가격", description = "해당 주류의 각 판매처의 가격입니다.")
    private int price;

    @Schema(title = "재고", description = "해당 주류의 각 판매처의 재고입니다.")
    private int amount;

}
