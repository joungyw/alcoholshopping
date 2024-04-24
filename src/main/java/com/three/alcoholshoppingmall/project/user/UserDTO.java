package com.three.alcoholshoppingmall.project.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

    @Getter
    @Setter
    @ToString
    @Schema(description = "user 테이블에 대한 내용입니다.")
    public class UserDTO {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(title = "생성 번호", description = "table에서 자동으로 생성되는 칼럼입니다.")
        private Long id;

        @Schema(title = "email", description = "회원의 email입니다.")
        private String email;

        @Schema(title = "nickname", description = "회원의 nickname입니다.")
        private String nickname;

        @Schema(title = "password", description = "회원의 password입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;

        @Schema(title = "address", description = "회원의 주소 입니다.")
        private String address;

        @Schema(title = "lastaddress", description = "회원의 마지막 배송 주소 입니다.")
        private String lastaddress;

        @Schema(title = "gender", description = "회원의 성별 입니다.")
        private Gender gender;

        @Schema(title = "birthdate", description = "회원의 생년월일 입니다.")
        @Pattern(regexp = "(?=.*[0-9]).{8,8}", message = "생년월일 8자리를 입력해 주세요.")
        private int birthdate;

        @Schema(title = "phone", description = "회원의 전화번호 입니다.")
        private String phone;

        @Schema(title = "withdraw", description = "회원의 탈퇴 입니다.")
        private WithdrawStatus withdrawStatus;


    }

