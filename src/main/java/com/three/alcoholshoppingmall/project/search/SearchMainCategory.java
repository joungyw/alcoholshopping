package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "대분류 검색 페이지입니다.")
public class SearchMainCategory {

    @Schema(description = "대분류", example = "대분류")
    private String maincategory;

}
