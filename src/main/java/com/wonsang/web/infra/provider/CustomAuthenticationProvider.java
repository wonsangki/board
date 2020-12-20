package com.wonsang.web.infra.provider;

import com.wonsang.web.modules.account.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Resource(name = "accountService")
  private AccountService accountService;

  @NonNull
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication var1) throws AuthenticationException {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) var1;

    String userEmail = token.getName();
    String userPw = (String) token.getCredentials();
    UserDetails userDetails = accountService.loadUserByUsername(userEmail); //

    if(!passwordEncoder.matches(userPw, userDetails.getPassword())){
      throw new BadCredentialsException(userDetails.getUsername() + "Invaild password");
    }
    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
  }
  @Override
  public boolean supports(Class<?> var1){
    return var1.equals(UsernamePasswordAuthenticationToken.class);
  }


}
