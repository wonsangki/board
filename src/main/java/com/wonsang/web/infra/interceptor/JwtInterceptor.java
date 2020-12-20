package com.wonsang.web.infra.interceptor;

import com.wonsang.web.infra.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	private static final String HEADER_AUTH = "Authorization";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader(HEADER_AUTH);
		TokenUtils tokenUtils = new TokenUtils();
		if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			token = token.substring(7, token.length());
			SetOperations<String, Object> sop = redisTemplate.opsForSet();
			if(sop.isMember("blacklist", token)) {
				return false;
			}
			if(tokenUtils.isValidToken(token).equals("true")) {
				return true;
			} else if(tokenUtils.isValidToken(token).equals("expired")) {
				response.sendError(444, "expired");
			}
		}
		return false;
	}
	
}
