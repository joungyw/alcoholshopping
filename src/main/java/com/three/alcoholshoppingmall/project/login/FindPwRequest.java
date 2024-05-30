package com.three.alcoholshoppingmall.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindPwRequest {
    @Schema(title = "이메일", description = "이메일을 입력하세요.")
    private String email;
}
