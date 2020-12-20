package com.wonsang.web.infra.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutHandler implements LogoutSuccessHandler {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  @Override
  public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    String token = httpServletRequest.getHeader("Authorization");
    SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
    if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
      token = token.substring(7, token.length());
      setOperations.add("blacklist", token);
    }

  }
}
