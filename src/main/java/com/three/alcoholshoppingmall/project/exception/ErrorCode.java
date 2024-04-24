package com.three.alcoholshoppingmall.project.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {
    // 검색 에러코드
    NOTFOUNDALCOHOL(HttpStatus.NOT_FOUND, "NOTSELECT", "조회한 술이 존재하지 않습니다."),

    // 회원가입 에러 코드
    DUPLEMAIL(HttpStatus.BAD_REQUEST,"EMAILDUPL","이미 가입된 이메일입니다."),
    DUPLPHONE(HttpStatus.BAD_REQUEST,"DUPLPHONE","이미 가입된 번호입니다."),
    PATTERNEMAIL(HttpStatus.BAD_REQUEST,"EMAILPATTERN","이메일 양식을 확인해 주세요."),
    PATTERNBIRTHDATE(HttpStatus.BAD_REQUEST,"PATTERNBIRTHDATE","생년월일 8자리를 입력해주세요."),
    PATTERNPASSWORD(HttpStatus.BAD_REQUEST,"PATTERNPASSWORD","비밀번호는 8~16자리 영어,숫자,특수문자 조합이어야 합니다."),
    NULLEMAIL(HttpStatus.NOT_FOUND,"NULLEMAIL","이메일을 확인해 주세요."),
    NULLNICKNAME(HttpStatus.NOT_FOUND,"NULLNICKNAME","닉네임을 확인해 주세요."),
    NULLPSSWORD(HttpStatus.NOT_FOUND,"NULLPSSWORD","패스워드를 확인해 주세요."),
    NULLADDRESS(HttpStatus.NOT_FOUND,"NULLADDRESS","주소를 확인해 주세요."),
    NULLLASTADDRESS(HttpStatus.NOT_FOUND,"NULLLASTADDRESS","상세주소를 확인해 주세요."),
    NULLGENDER(HttpStatus.NOT_FOUND,"NULLGENDER","성별을 확인해 주세요."),
    NULLBIRTHDATE(HttpStatus.NOT_FOUND,"NULLBIRTHDATE","생년월일을 확인해 주세요."),
    NULLPHONE(HttpStatus.NOT_FOUND,"NULLPHONE","번호를 확인해 주세요."),
    CHECKPASSWORD(HttpStatus.NOT_FOUND,"CHECKPASSWORD","입력한 비밀번호가 다릅니다."),

    // 로그인 에러 코드
    CHECKIDPASSWORD(HttpStatus.NOT_FOUND,"CHECKIDPASSWORD","아이디와 비밀번호를 확인해 주세요."),

    TEST(HttpStatus.NOT_FOUND,"TEST","다른에러")


;
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}