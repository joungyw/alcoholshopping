package com.three.alcoholshoppingmall.project.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class UserUpdate {
    @Schema(title = "nickname", description = "회원의 nickname입니다.", example = "닉네임")
    private String nickname;

    @Schema(title = "phone", description = "회원의 전화번호 입니다.", example = "전화번호")
    private String phone;

    @Schema(title = "address", description = "회원의 주소 입니다.", example = "주소")
    private String address;

    @Schema(title = "address2", description = "회원의 상세 주소 입니다.", example = "상세주소")
    private String address2;
}
