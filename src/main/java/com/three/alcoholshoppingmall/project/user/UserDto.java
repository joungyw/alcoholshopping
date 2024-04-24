package com.three.alcoholshoppingmall.project.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
@Schema(description = "user 테이블에 대한 내용입니다.")
public class UserDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "생성 번호", description = "table에서 자동으로 생성되는 칼럼입니다.")
    private Long id;

    @Pattern(regexp = "(^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)",message = "이메일 형식을 맞춰주세요")
    @Schema(title = "email", description = "회원의 email입니다.")
    private String email;

    @Schema(title = "nickname", description = "회원의 nickname입니다.")
    private String nickname;

    @Schema(title = "password", description = "회원의 password입니다.")
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @Schema(title = "passwordch", description = "회원의 passwordch입니다.")
    private String passwordch;

    @Schema(title = "address", description = "회원의 주소 입니다.")
    private String address;

    @Schema(title = "lastaddress", description = "회원의 마지막 배송 주소 입니다.")
    private String lastaddress;

    @Schema(title = "gender", description = "회원의 성별 입니다.")
    private Gender gender;

    @Schema(title = "birthdate", description = "회원의 생년월일 입니다.")
    @Pattern(regexp = "(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])", message = "생년월일 8자리를 입력해 주세요.")
    private String birthdate;

    @Schema(title = "phone", description = "회원의 전화번호 입니다.")
    @Pattern(regexp="^(010)([0-9]{4})([0-9]{4})", message="올바른 휴대폰 번호를 입력하세요.")
    private String phone;

    @Schema(title = "withdraw", description = "회원의 탈퇴 입니다.")
    private WithdrawStatus withdrawStatus;
}