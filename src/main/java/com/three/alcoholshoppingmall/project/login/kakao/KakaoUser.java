package com.three.alcoholshoppingmall.project.login.kakao;


import com.three.alcoholshoppingmall.project.user.Gender;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoUser {

    private String nickname;
    private String birthdate;
    private Gender gender;
    private String phone;
    private String address;
    private String address2;
}
