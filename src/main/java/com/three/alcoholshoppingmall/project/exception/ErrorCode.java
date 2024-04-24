package com.three.alcoholshoppingmall.project.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {
    // 검색 에러코드
    NOTFOUNDALCOHOL(HttpStatus.NOT_FOUND, "NOTSELECT", "조회한 술이 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
