package com.three.alcoholshoppingmall.project.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException{
    private ErrorCode errorCode;
    public BizException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
