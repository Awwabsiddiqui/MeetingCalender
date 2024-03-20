package com.example.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AJAX")
public class AJAXRestController {
	
	@GetMapping("/getId")
	public ResponseEntity<String> getId(){
		ResponseEntity<String> re = new ResponseEntity<>("ajaxer" , HttpStatus.OK);
		return re;
	}

}
