package com.tts.ecommercepage.configurations;

import com.tts.ecommercepage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
@Autowired
private UserService userService;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
 
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
}
 
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//  http
//      .authorizeRequests()
//          .antMatchers("/cart").authenticated()
//      .and().formLogin()
//          .loginPage("/signin")
//          .loginProcessingUrl("/login")
//      .and().logout()
//          .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
//          .logoutSuccessUrl("/");
//}
@Override
protected void configure(HttpSecurity http) throws Exception { 
	http
        .authorizeRequests()
            .antMatchers("/cart").authenticated()
            .antMatchers("/signin").permitAll()
            .anyRequest().permitAll()
        .and().formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/", true)
        .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
            .logoutSuccessUrl("/");
}
}
