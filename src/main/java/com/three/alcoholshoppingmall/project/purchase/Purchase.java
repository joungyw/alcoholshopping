package com.three.alcoholshoppingmall.project.purchase;

import com.three.alcoholshoppingmall.project.market.Ordertype;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase")
@Schema(name = "구매정보")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "기본키"  , description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Schema(title = "이메일", description = "구매자의 이에일 주소 입니다.")
    private String email;

    @Schema(title = "술 이름", description = "술의 이름 입니다.")
    private String name;

    @Schema(title = "매장 이름", description = "매장의 이름 입니다.")
    private String marketname;

    @Schema(title = "가격", description = "물건의 가격입니다")
    private int price;

    @Schema(title = "수령형식", description = "물건을 수령할때 배달인지 픽업인지 구분합니다.")
    @Enumerated(EnumType.STRING)
    private Ordertype ordertype;

    @Enumerated(EnumType.STRING)
    @Schema(title = "구매, 환불, 교환 여부", description = "구매자의 상품의 상태를 표기 합니다")
    private Division division;

    @Schema(title = "주소", description = "구매자의 주소입니다.")
    private String address;

    @Schema(title = "구매일자", description = "물건을 구매한 날짜 입니다.")
    private LocalDate purchaseday;

    @Schema(title = "OrderNumber", description = "구매자의 주문번호 입니다.")
    private String OrderNumber;

    @Schema(title = "amount", description = "해당 물품의 구매 수량입니다.")
    private  int amount;

}
