package com.wonsang.web.modules.account.model;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
  Account findByEmail(String email);
  Account findByNickname(String nickname);
  Account findById(Long id);
}
