package com.example.springrest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.RepoEnt;

@Service
public  class MyUserDetailsService implements UserDetailsService{

	@Autowired
	RepoEnt RepoEnt;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<Ent> user = RepoEnt.findTopByName(username);
		 
		 user.orElseThrow(()-> new UsernameNotFoundException("User absent in Database ::: "+username));
		 System.out.println("user ::: "+user);
		 return user.map(MyUserDetails::new).get();
	}

	
	
}
