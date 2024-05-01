package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDelete {
    @Schema(title = "검색 ID", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;
}
