package com.three.alcoholshoppingmall.project.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.market.Market;
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
    private Long stocknumber;

    @Schema(title = "매장의 코드", description = "매장의 코드와 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Alcohol alcohol;


    @Schema(title = "술의 코드", description = "술의 코드를 넣는 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "marketcode", referencedColumnName = "marketcode")
    private Market market;

    @Schema(title = "재고", description = "해당 주류의 각 판매처의 재고입니다.")
    private int amount;

}
