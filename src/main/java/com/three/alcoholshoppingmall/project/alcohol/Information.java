package com.three.alcoholshoppingmall.project.alcohol;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메인 페이지용 정보저장 클래스")
public class Information {


    @Schema(title = "주류 ID", description = "주류의 KEY값 입니다.")
    private Long id;

    @Schema(title = "주류 이름", description = "주류 이름을 넣어주시면 됩니다.")
    private String name;

    @Schema(title = "주류 대분류", description = "주류의 대분류를 넣어주시면 됩니다.")
    private String maincategory;

    @Schema(title = "주류 소분류", description = "주류의 소분류를 넣어주시면 됩니다.")
    private String subcategory;

    @Schema(title = "주류 도수", description = "주류의 도수을 넣어주시면 됩니다.")
    private String content;

    @Schema(title = "주류 향", description = "주류 향을 넣어주시면 됩니다.")
    private String aroma;

    @Schema(title = "주류 맛", description = "주류 맛을 넣어주시면 됩니다.")
    private String taste;

    @Schema(title = "주류 여운", description = "주류의 여운을 넣어주시면 됩니다.")
    private String finish;

    @Schema(title = "주류 국가", description = "주류의 국가를 넣어주시면 됩니다.")
    private String nation;

    @Schema(title = "주류 사진", description = "주류의 사진을 넣어주시면 됩니다.")
    private String picture;

    @Schema(title = "주류의 평점", description = "주류의 총평점 입니다.")
    private double grade;

    @Schema(title = "주류의 가격", description = "주류의 가격 입니다.")
    private int price;

    @Schema(title = "주류의 총 재고량", description = "주류의 총 재고량 입니다.")
    private int amount;










}
