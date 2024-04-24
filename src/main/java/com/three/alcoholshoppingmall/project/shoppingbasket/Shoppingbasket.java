package com.three.alcoholshoppingmall.project.shoppingbasket;


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


}
