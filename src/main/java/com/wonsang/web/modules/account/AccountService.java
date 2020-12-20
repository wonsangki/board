package com.wonsang.web.modules.account;

import com.wonsang.web.modules.account.model.Account;
import com.wonsang.web.modules.account.model.AccountDetails;
import com.wonsang.web.modules.account.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountService implements UserDetailsService {
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  BCryptPasswordEncoder passwordEncoder;
  @Override
  public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(useremail);
    if (account == null) {
      throw new UsernameNotFoundException(useremail);
    }
    AccountDetails accountDetails = new AccountDetails();
    accountDetails.setAccount(account);
    accountDetails.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(account.getGrade().getValue())));
    return accountDetails;
  }
}
