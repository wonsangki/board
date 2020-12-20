package com.wonsang.web.infra.filter;

import com.wonsang.web.infra.exception.InputNotFoundException;
import com.wonsang.web.modules.account.Account;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    UsernamePasswordAuthenticationToken authenticationToken;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Enumeration<String> em = request.getHeaderNames();
    String abc = "";
    while(em.hasMoreElements()){
      String name = em.nextElement();
      String val = request.getHeader(name);
    }

    try {
      abc =request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(abc);
      String email = (String) jsonObject.get("email");
      String password = (String) jsonObject.get("password");
      Account account = new Account();
      account.setEmail(email);
      account.setPassword(password);

      authenticationToken = new UsernamePasswordAuthenticationToken(account.getEmail(),account.getPassword());
    } catch (Exception e) {
      throw new InputNotFoundException();
    }
    setDetails(request,authenticationToken);
    return this.getAuthenticationManager().authenticate(authenticationToken);
  }
}
