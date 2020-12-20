package com.wonsang.web.infra.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountGrade {
  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN");

  private String value;

  public String getValue() {
    return value;
  }
}
