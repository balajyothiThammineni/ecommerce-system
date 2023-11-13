package com.springboot.ecommerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.ecommerce.service.UserService;



@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

 @Autowired
 private UserService userService;
	
 @Override
 protected void configure(AuthenticationManagerBuilder auth)throws Exception{
  System.out.println("configure...called");
  auth.authenticationProvider(getProvider());
 }
	
 @Override
 protected void configure(HttpSecurity http)throws Exception{
  
	 http
		.authorizeRequests()
		.antMatchers("/customer/add","/seller/signup","/executive/signup","/product/add/{sid}","/category/add","/category/getall").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and()
		.csrf().disable()
		.cors().disable();
 }
 @Bean
 public PasswordEncoder getEncoder() {
  return new BCryptPasswordEncoder();
 }
	
 public DaoAuthenticationProvider getProvider() {
  System.out.println("getprovider...called");
  DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
  dao.setPasswordEncoder(getEncoder());
  dao.setUserDetailsService(userService);
  
  return dao;
 }
}