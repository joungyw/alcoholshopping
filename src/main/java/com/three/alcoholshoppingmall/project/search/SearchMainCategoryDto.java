package com.three.alcoholshoppingmall.project.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchMainCategoryDto {

    @Schema(description = "대분류", example = "대분류")
    private String maincategory;
}
