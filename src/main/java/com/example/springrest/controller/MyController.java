package com.example.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.method.meetingMethod;

//@RequestMapping(method = RequestMethod.X)  where X = {GET , PUT , POST DELETE ETC.} === @GetMapping , @PostMapping

@RestController
public class MyController {

	@GetMapping("/")
	public String[] homepage() {
		String[] arr = new String[] { "http://localhost:8080", "http://localhost:8080/register?name=",
				"http://localhost:8080/bookMeeting?yourName=&meeetingWith=&meetingDate=&meetingTime" };
		return arr;
	}

	@GetMapping("/register")
	public String register(@RequestParam(required = true, value = "name") String name) throws Exception {
		return meetingMethod.register(name);
	}

	@GetMapping("/bookMeeting")
	public String bookMeeting(@RequestParam(required = true, value = "yourName") String yourName,
			@RequestParam(required = true, value = "meeetingWith") String meeetingWith,
			@RequestParam(required = true, value = "meetingDate") String meetingDate,
			@RequestParam(required = true, value = "meetingTime") String meetingTime) throws Exception {
		return meetingMethod.bookMeeting(yourName, meeetingWith, meetingDate, meetingTime);
	}
}
