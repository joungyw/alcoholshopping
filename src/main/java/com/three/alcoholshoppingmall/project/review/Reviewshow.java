package com.three.alcoholshoppingmall.project.review;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reviewshow {

    private Long id;

    private Long alcoholcode;

    private String name;

    private String writing;

    private int grade;

    private String picture;

}
