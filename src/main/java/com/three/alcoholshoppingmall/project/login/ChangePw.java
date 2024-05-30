package com.three.alcoholshoppingmall.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePw {
    @Schema(title = "newPassword", description = "회원의 변경하고픈 password입니다.",example = "변경하고픈 비밀번호")
    @NotBlank(message = "변경하고픈 비밀번호를 입력하세요.")
    private String newPassword;

    @Schema(title = "passwordch", description = "회원의 passwordch입니다.", example = "비밀번호 확인")
    @NotBlank(message = "비밀번호를 확인해주세요.")
    private String passwordch;
}
