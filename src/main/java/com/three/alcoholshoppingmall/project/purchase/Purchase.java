package com.three.alcoholshoppingmall.project.purchase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.three.alcoholshoppingmall.project.shoppingbasket.Shoppingbasket;
import com.three.alcoholshoppingmall.project.user.User;
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
    private Long ordernumber;

    @Schema(title = "회원 이메일", description = "회원의 이메일을 넣는 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @Schema(title = "장바구니 코드", description = "장바구니 코드와 조인된 칼럼입니다.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shoppingnumber", referencedColumnName = "shoppingnumber")
    private Shoppingbasket shoppingbasket;

    @Schema(title = "수령형식", description = "물건을 수령할때 배달인지 픽업인지 구분합니다.")
    @Enumerated(EnumType.STRING)
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    @Schema(title = "구매, 환불, 교환 여부", description = "구매자의 상품의 상태를 표기 합니다")
    private Division division;

    @Schema(title = "주소", description = "구매자의 주소입니다.")
    private String address;

    @Schema(title = "구매일자", description = "물건을 구매한 날짜 입니다.")
    private LocalDate purchaseday;



}
