package com.example.springrest.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

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
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests();
//	}
}
