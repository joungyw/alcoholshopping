package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "검색했을 때 받는 주류 값들 저장소입니다.")
public class SearchDetail {
    @Schema(title = "주류 ID", description = "주류의 KEY값 입니다.")
    private Long code;

    @Schema(title = "주류 이름", description = "주류 이름을 넣어주시면 됩니다.")
    private String name;

    @Schema(title = "주류의 평균 평점", description = "주류의 평균 평점 입니다.")
    private double ratingaverage;

    @Schema(title = "주류의 가격", description = "주류의 가격 입니다.")
    private int price;



}
