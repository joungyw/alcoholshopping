package com.three.alcoholshoppingmall.project.search;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoneMemberSearch {


    @Schema(title = "검색내용", description = "검색 내용을 입력하는 칼럼입니다.", example = "검색내용")
    @Column(nullable = false)
    private String searchcontents;
}
