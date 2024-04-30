package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchSubCategroyDto {

    @Schema(description = "소분류", example = "소분류")
    private String subcategory;
}
