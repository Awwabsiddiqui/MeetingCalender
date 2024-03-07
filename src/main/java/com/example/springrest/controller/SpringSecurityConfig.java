package com.example.springrest.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic(basic -> basic.disable());
		///http.authorizeRequests();
	}
	
	//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//    	DaoAuthenticationProvider DaoAP = new DaoAuthenticationProvider();
//    	DaoAP.setUserDetailsPasswordService(null);
//			return DaoAP;
//			
//		}
}
