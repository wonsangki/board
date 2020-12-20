package com.wonsang.web.infra.exception;

import com.wonsang.web.infra.enums.ErrorCode;

public class PasswordNotMatchedException extends RuntimeException {
    private ErrorCode errorCode;

    public PasswordNotMatchedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
