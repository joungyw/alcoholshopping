package com.three.alcoholshoppingmall.project.alcohol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainListDto {
    private double ratingaverage;
    private String name;
    private int price;
    private Long code;
}
