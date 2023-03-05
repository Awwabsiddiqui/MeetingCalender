package com.example.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.Repository;
import com.example.springrest.method.meetingMethod;

@RestController
public class MyController {
	@Autowired
	private Repository Repository;

	@GetMapping("/")
	public String[] homepage() {
		String[] arr = new String[] { "http://localhost:8080",
				"http://localhost:8080/register?name=",
				"http://localhost:8080/registerJPA?name=",
				"http://localhost:8080/bookMeeting?yourName=&meeetingWith=&meetingDate=&meetingTime",
				"http://localhost:8080/allMeetings",
				"http://localhost:8080/byName?name=",
				"http://localhost:8080/bookMeetingJPA?yourName=&meeetingWith=&meetingDate=&meetingTime=" };
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

	@GetMapping("/allMeetings")
	public String allMeetings() {
		List<Ent> ls = Repository.findAll();
		System.out.println("LIST = " + ls);
		return ls.toString();
	}

	@GetMapping("/byName")
	public String singleStuddent(@RequestParam(required = true, value = "name") String name) {
		List<Ent> ls = Repository.findByName(name);
		System.out.println("LIST = " + ls);
		return ls.toString();
	}

	@GetMapping("/bookMeetingJPA")
	public String bookMeetingJPA(@RequestParam(required = true, value = "yourName") String yourName,
			@RequestParam(required = true, value = "meeetingWith") String meeetingWith,
			@RequestParam(required = true, value = "meetingDate") String meetingDate,
			@RequestParam(required = true, value = "meetingTime") String meetingTime) throws Exception {

		return bookMeetingJPAService(yourName, meeetingWith, meetingDate, meetingTime);

	}

	public String bookMeetingJPAService(String from, String to, String Date, String Time) {

		if (from.contentEquals(to))
			return "meeting with self not allowed";

		String meetingTime = Date + "|" + Time;

		List<Ent> metaDataFrom = Repository.findByName(from);
		String meetingValFrom = metaDataFrom.get(0).getMeeting();
		long meetingIdFrom = metaDataFrom.get(0).getId();
		String[] meetingArrFrom = meetingValFrom.split("\\,");

		List<Ent> metaDataTo = Repository.findByName(to);
		String meetingValTo = metaDataTo.get(0).getMeeting();
		long meetingIdTo = metaDataTo.get(0).getId();
		String[] meetingArrTo = meetingValTo.split("\\,");

		if (meetingArrFrom == null) {
			return "User does not exist , Please register : http://localhost:8080/registerJPA?name=";
		} else if (meetingArrTo == null) {
			return "User does not exist";
		} else {

			for (int i = 0; i < meetingArrTo.length; i++) {
				if (meetingArrTo[i].contains(meetingTime)) {
					return to + " has a meeting already booked with : " + meetingArrTo[i].split("@")[0];
				}
			}
			for (int i = 0; i < meetingArrFrom.length; i++) {
				if (meetingArrFrom[i].contains(meetingTime)) {
					return "you have a meeting already booked with : " + meetingArrFrom[i].split("@")[0];
				}
			}
			boolean check1 = saver(meetingIdTo, to, meetingValTo, from + "@" + meetingTime + ",");
			boolean check2 = saver(meetingIdFrom, from, meetingValFrom, to + "@" + meetingTime + ",");

			if (check1 && check2)
				return "meeting booked successfully";
		}

		return "someError";
	}

	public boolean saver(long id, String name, String meetingTime, String meetingData) {
		Ent entity = new Ent();
		entity.setId(id);
		entity.setName(name);
		entity.setMeeting(meetingData + meetingTime);
		Repository.save(entity);

		return true;
	}

}
