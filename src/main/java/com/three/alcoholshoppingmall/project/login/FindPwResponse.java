package com.three.alcoholshoppingmall.project.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPwResponse {
    @Schema(title = "회신받을 이메일",description = "회신받을 이메일을 입력하세요.")
    private String receiveAddress;

    @Schema(title = "메일 제목",description = "메일 제목을 입력하세요.")
    private String mailTitle;

    @Schema(title = "메일 내용",description = "메일 내용을 입력하세요.")
    private String mailContent;

}
