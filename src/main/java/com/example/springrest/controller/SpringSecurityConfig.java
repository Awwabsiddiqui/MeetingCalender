package com.example.springrest.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	protected UserDetailsService userDetailsService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {		// skips spring security
//        http.httpBasic(basic -> basic.disable());
//		///http.authorizeRequests();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication()
//		.withUser("user").password("user").roles("USER")
//		.and()
//		.withUser("admin").password("admin").roles("ADMIN");

//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery("SELECT name , password FROM meetingcalenderhibernate WHERE name=?")
//		.authoritiesByUsernameQuery("SELECT name , authority FROM meetingcalenderhibernate WHERE name=?")
//		;
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		// return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/listAllUser").hasAuthority("ADMIN")
		.antMatchers("/home").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/**").permitAll()
		.and().formLogin();
	}

//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//    	DaoAuthenticationProvider DaoAP = new DaoAuthenticationProvider();
//    	DaoAP.setUserDetailsPasswordService(null);
//			return DaoAP;
//			
//		}
}
