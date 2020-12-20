package com.wonsang.web.modules.account;


import com.wonsang.web.infra.util.TokenUtils;
import com.wonsang.web.modules.account.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class AccountController {
  @Autowired
  AccountService accountService;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  private TokenUtils tokenUtils;
}
