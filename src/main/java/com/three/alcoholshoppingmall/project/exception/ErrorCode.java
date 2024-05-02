package com.three.alcoholshoppingmall.project.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {
    // 검색 에러코드
    NULLSEARCH(HttpStatus.NOT_FOUND, "NULLSEARCH","검색기록은 공백일 수 없습니다."),
    SEARCHCLENGTH(HttpStatus.BAD_REQUEST, "SEARCHLENGTH", "검색 기록은 두 글자 이상 입력해야합니다."),
    NULLRECENT(HttpStatus.NOT_FOUND,"NULLRECENT", "최근 검색기록이 존재하지 않습니다."),
    NULLMAINCATEGORY(HttpStatus.NOT_FOUND, "NULLMAINCATEGORY", "해당 대분류가 존재하지 않습니다."),
    NULLSUBCATEGORY(HttpStatus.NOT_FOUND, "NULLSUBCATEGORY", "해당 소분류가 존재하지 않습니다."),
    NOTFOUNDALCOHOL(HttpStatus.NOT_FOUND, "NOTFOUNDALCOHOL", "해당 이름의 주류를 찾을 수 없습니다."),
    NOTFOUNDRECENT(HttpStatus.NOT_FOUND, "NOTFOUNDRECENT", "해당 검색 기록을 찾을 수 없습니다."),

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
    CHECKEMAILPASSWORD(HttpStatus.NOT_FOUND,"CHECKEMAILPASSWORD","이메일과 비밀번호를 확인해 주세요."),



    //해당 정보를 가진 장바구니가 없을떄
    NOTFOUNDSHPPING(HttpStatus.NOT_FOUND, "NOTFOUNDRECENT", "해당 내용은 장바구니에 없습니다."),

    //즐겨 찾기 삭제시 없는 내용인경우
    NOTFOUNDFAVORITES(HttpStatus.NOT_FOUND, "NOTFOUNDRECENT", "해당 내용은 즐겨 찾기 목록에 없습니다."),

    //리뷰 삭제시 없는 내용인경우
    NOTFOUNDREVIEW(HttpStatus.NOT_FOUND, "NOTFOUNDRECENT", "해당 내용의 리뷰는 존재하지 않습니다."),

    //주류 정렬시 에러코드
    ERRORTYPE(HttpStatus.BAD_REQUEST,"EMAILDUPL","유효하지 않은 정렬 태그입니다."),
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