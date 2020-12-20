package com.wonsang.web.infra.exception;


import com.wonsang.web.infra.enums.ErrorCode;

public class BusinessException extends RuntimeException {

  private ErrorCode errorCode;

  public BusinessException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
