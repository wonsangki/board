package com.wonsang.web.infra.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "GET, POST");
    res.setHeader("Access-Control-Max-Age", "3600");
    res.setHeader(
            "Access-Control-Allow-Headers",
            "X-Requested-With, Content-Type, Authorization, X-XSRF-token"
    );
    res.setHeader("Access-Control-Allow-Credentials", "false");

    filterChain.doFilter(servletRequest, servletResponse);
  }

}
