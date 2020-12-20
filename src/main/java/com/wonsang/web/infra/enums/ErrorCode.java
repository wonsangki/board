package com.wonsang.web.infra.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
  ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
  INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
  INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
  HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),


  // Member
  EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
  NICKNAME_DUPLICATION(400, "M001", "Nickname is Duplication"),
  LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
  PASSWORD_NOT_MATCHED(400,"M003", "Entered Password is incorrect"),
  INCORRECT_USER_ACCESS(400,"M004", "Incorrect User access"),

  // Coupon
  COUPON_ALREADY_USE(400, "CO001", "Coupon was already used"),
  COUPON_EXPIRE(400, "CO002", "Coupon was already expired"),

  // Token
  TOKEN_EXPIRE(400, "CO002", "Token was already expired"),
  TOKEN_TAMPERED(400, "CO002", "Token is tampered"),
  TOKEN_NULL(400, "CO002", "Token doesn't exist")



  ;
  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCode() {
    return code;
  }

  public int getStatus() {
    return status;
  }
  }