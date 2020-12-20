package com.wonsang.web.infra.exception;

import com.wonsang.web.infra.enums.ErrorCode;

public class InvalidRefreshTokenException extends BusinessException {


  public InvalidRefreshTokenException(String message, ErrorCode errorCode) {
    super(message, errorCode);
  }

  public InvalidRefreshTokenException(ErrorCode errorCode) {
    super(errorCode);
  }
}
