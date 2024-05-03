package com.three.alcoholshoppingmall.project.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "대분류 검색 페이지입니다.")
public class SearchMainCategory {

    @Schema(description = "대분류", example = "대분류")
    private String maincategory;
    @Schema(description = "주류 번호", example = "주류 번호")
    private Long code;
    @Schema(description = "주류 이름", example = "주류 이름")
    private String name;
    @Schema(description = "주류 가격", example = "주류 가격")
    private int price;
    @Schema(description = "평균평점", example = "평균평점")
    private double ratingaverage;
    @Schema(description = "주류 이미지", example = "주류 이미지")
    private String picture;

}
