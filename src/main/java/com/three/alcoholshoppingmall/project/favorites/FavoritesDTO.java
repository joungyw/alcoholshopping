package com.three.alcoholshoppingmall.project.favorites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@Schema(description = "favorites 테이블에 대한 내용입니다.")
public class FavoritesDTO {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private User user;

    @JsonIgnore
    private Alcohol alcohol;



}

