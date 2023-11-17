package com.springboot.ecommerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
		.antMatchers("/user/getall","/customer/signup","/executive/signup","/executive/login/{id}","/seller/update/{id}","/mcategory/delete/{cid}","/customer/all","/customer/login/{id}","/mcategory/all","/mcategory/add/{eid}","/customer/delete/{id}","/customer/update/{id}","/seller/signup","/seller/view/all","/seller/delete/{id}","/category/add","/category/getall","/category/delete/{id}").permitAll()
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