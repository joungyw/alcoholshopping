package com.three.alcoholshoppingmall.project.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
@Schema(description = "review 테이블에 대한 내용입니다.")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "주류 ID", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Schema(title = "주류 명", description = "주류의 이름 입니다.")
    private String name;

    @Schema(title = "판매처 이름", description = "해당 주류를 판매하는 판매처의 이름 입니다.")
    private String marketname;

    @Schema(title = "email", description = "사용자의 이메일 주소 입니다.")
    private String email;

    @Schema(title = "리뷰 글", description = "사용자가 작성한 리뷰 글입니다.")
    private String writing;

    @Schema(title = "평점", description = "사용자가 매긴 평점 입니다.")
    @Min(value = 0, message = "최소 숫자는 0 입니다.")
    @Max(value = 10, message = "최대 숫자는 10 입니다.")
    private int grade;

    @Schema(title = "사진", description = "리뷰시 올린 사진 입니다.")
    private String picture;

}
