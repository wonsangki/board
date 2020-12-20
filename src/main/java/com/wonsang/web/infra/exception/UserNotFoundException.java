package com.wonsang.web.infra.exception;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String userEmail){
    super(userEmail + " NotFoundException");
  }

}