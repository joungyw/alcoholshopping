package com.three.alcoholshoppingmall.project.favorites;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorites")
@Schema(description = "favorites 테이블에 대한 내용입니다.")
public class Favorites { //즐겨찾기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "ID", description = "자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Schema(title = "email", description = "사용자의 이메일 주소 입니다.")
    private String email;

    @Schema(title = "주류 이름", description = "주류 이름을 넣어주시면 됩니다.")
    private String name;



}

