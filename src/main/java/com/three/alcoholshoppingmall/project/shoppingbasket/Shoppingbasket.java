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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shoppingbasket")
@Schema(description = "shoppingbasket 테이블에 대한 내용입니다.")
public class Shoppingbasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "shoppingnumber", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long shoppingnumber;

    @Schema(title = "회원 이메일", description = "회원의 이메일을 넣는 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @Schema(title = "재고", description = "재고 테이블과 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stocknumber", referencedColumnName = "stocknumber")
    private Stock stock;


    @Schema(title = "amount", description = "해당 물품의 구매 수량입니다.")
    private  int amount;

    @Schema(title = "price", description = "해당 물품들의 총 가격입니다.")
    private int price;

}
