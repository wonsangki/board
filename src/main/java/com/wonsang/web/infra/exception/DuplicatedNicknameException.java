package com.wonsang.web.infra.exception;

import com.wonsang.web.infra.enums.ErrorCode;

public class DuplicatedNicknameException extends BusinessException {
  public DuplicatedNicknameException(String msg){
    super(msg, ErrorCode.NICKNAME_DUPLICATION);
  }
}
