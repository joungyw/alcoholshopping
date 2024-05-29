package com.three.alcoholshoppingmall.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindEmailDTO {
    @Schema(title = "생년월일", description = "생년월일을 입력하는 칼럼입니다.", example = "생년월일")
    private String birthdate;
    @Schema(title = "휴대전화번호", description = "휴대전화번호를 입력하는 칼럼입니다.", example = "휴대전화번호")
    private String phone;

}
