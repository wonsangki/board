package com.wonsang.web.infra.handler;

import com.wonsang.web.infra.util.TokenUtils;
import com.wonsang.web.modules.account.AccountDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  @Autowired
  private TokenUtils tokenUtils;
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;


  @Override public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    AccountDetails user = (AccountDetails) authentication.getPrincipal();
    ValueOperations<String, Object> vop = redisTemplate.opsForValue();

    String accessToken = tokenUtils.generateJwtToken(user.getAccount(),60000*30);
    String refreshToken = tokenUtils.generateJwtToken(user.getAccount(), 60000*60*24*7);

    vop.set("token:" + accessToken, user.getAccount(), 30, TimeUnit.MINUTES);

    String username = user.getUsername();

    response.addHeader("accessToken","Bearer "+ accessToken);
    response.addHeader("refreshToken","Bearer "+ refreshToken);
  }

}
