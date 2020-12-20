package com.wonsang.web.infra.config;


import com.wonsang.web.infra.filter.CustomAuthenticationFilter;
import com.wonsang.web.infra.handler.CustomLoginSuccessHandler;
import com.wonsang.web.infra.handler.CustomLogoutHandler;
import com.wonsang.web.infra.provider.CustomAuthenticationProvider;
import com.wonsang.web.modules.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class AccountConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  AccountService accountService;

  public SecurityExpressionHandler expressionHandler() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    return handler;
  }
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors()
            .and()
            .formLogin()
            .disable()
            .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .logout()
            .logoutUrl("/api/account/logout")
            .logoutSuccessHandler(customLogoutHandler())
    ;


  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }


  @Bean
  public CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
    customAuthenticationFilter.setFilterProcessesUrl("/user/login");
    customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
    customAuthenticationFilter.afterPropertiesSet();
    return customAuthenticationFilter;

  }

  @Bean
  public CustomLoginSuccessHandler customLoginSuccessHandler(){
    return new CustomLoginSuccessHandler();
  }

  @Bean
  public CustomLogoutHandler customLogoutHandler(){
    return new CustomLogoutHandler();
  }


  @Bean
  public CustomAuthenticationProvider customAuthenticationProvider(){
    return new CustomAuthenticationProvider(bCryptPasswordEncoder());
  }
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
    authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);

    // This allow us to expose the headers
    configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, accessToken, refreshToken"));


    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
