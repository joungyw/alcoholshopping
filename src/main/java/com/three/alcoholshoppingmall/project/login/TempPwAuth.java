package com.three.alcoholshoppingmall.project.login;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Tag(name = "임시비밀번호 인증",description = "임시비밀번호 인증용 클래스입니다.")
public class TempPwAuth {
    @Pattern(regexp = "(^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)",
            message = "이메일 형식을 확인해 주세요")
    private String email;

    private String tempPw;
}
