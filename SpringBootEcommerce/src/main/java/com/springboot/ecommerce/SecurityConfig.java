package com.springboot.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.springboot.ecommerce.service.UserService;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure...called");
		auth.authenticationProvider(getProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/user/getall", "/customer/signup", "/executive/signup", "/executive/login/{id}",
						"/product/add/{sid}/{cid}", "category/getone/{id}", "/category/delete/{cid}","/category/all/{cid}",
						"/category/update/{id}", "product/all/{sid}", "/product/category/{cid}", "/seller/update/{id}",
						"/customer/update/{id}", "/product/getall", "/product/category/{cid}", "/review/{cid}/{pid}",
						"/customer/all", "/customer/getone/{id}", "/product/seller/{sid}",
						"/product/getall/{page}/{size}", "/review/getone/{pid}", "/customer/delete/{id}",
						"/customer/update/{id}", "/seller/signup", "/seller/view/all", "/seller/delete/{id}",
						"/review/delete/{id}", "/review/getall/{page}/{size}", "/product/getall", "/review/getall",
						"/order/{cid}/{pid}", "/order/delete/{id}", "/category/add", "/category/delete/{id}",
						"/orders/{cid}", "/orders/{pid}", "/order/getall", "/seller/getone/{id}","/hello").permitAll()
				 .antMatchers(HttpMethod.POST,"/auth/login").authenticated()
				 .anyRequest().permitAll()
				 .and().httpBasic()
				 .and().cors().disable()
				 .csrf().disable();
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