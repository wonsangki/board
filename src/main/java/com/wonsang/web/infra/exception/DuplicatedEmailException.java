package com.wonsang.web.infra.exception;

import com.wonsang.web.infra.enums.ErrorCode;

public class DuplicatedEmailException extends BusinessException {
  public DuplicatedEmailException(String msg){
    super(msg, ErrorCode.EMAIL_DUPLICATION);
  }
}
