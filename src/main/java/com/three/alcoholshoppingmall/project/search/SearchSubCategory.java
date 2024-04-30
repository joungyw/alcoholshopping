package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSubCategory {
    @Schema(description = "소분류", example = "소분류")
    private String subcategory;
}
