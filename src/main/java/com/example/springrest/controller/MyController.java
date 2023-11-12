package com.example.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.Repo;
import com.example.springrest.method.DOMeeting;
import com.example.springrest.method.meetingMethod;

@Controller
public class MyController {
	@Autowired
	private Repo Repository;
	
	@Autowired
	DOMeeting DOMeeting;
	
	@Autowired
	meetingMethod meetingMethod;

	@GetMapping("/")
	@ResponseBody
	public String[] homepage() {
		String[] arr = new String[] { "http://localhost:8080", "http://localhost:8080/register?name=",
				"http://localhost:8080/registerHibernate?name=", "http://localhost:8080/registerJPA?name=",
				"http://localhost:8080/bookMeeting?yourName=&meeetingWith=&meetingDate=&meetingTime",
				"http://localhost:8080/allMeetings", "http://localhost:8080/byName?name=",
				"http://localhost:8080/bookMeetingJPA?yourName=&meeetingWith=&meetingDate=&meetingTime=" };
		return arr;
	}

	@GetMapping("/register")
	@ResponseBody
	public String register(@RequestParam(required = true, value = "name") String name) throws Exception {
		return meetingMethod.register(name);
	}

	@GetMapping("/registerJPA")
	@ResponseBody
	public String registerJPA(@RequestParam(required = true, value = "name") String name) throws Exception {
		Ent entity = new Ent();
		entity.setName(name);
		entity.setMeeting("");
		Repository.save(entity);
		return "registered";
	}

	@GetMapping("/registerHibernate")
	@ResponseBody
	public String registerHibernate(@RequestParam(required = true, value = "name") String name) throws Exception {
		return meetingMethod.registerHibernate(name);
	}

	@GetMapping("/updateColumnByName")
	@ResponseBody
	public String updateColumnByName(@RequestParam(required = true, value = "name") String name) throws Exception {
		return meetingMethod.updateColumnByName(name);
	}

	@GetMapping("/bookMeeting")
	@ResponseBody
	public String bookMeeting(@RequestParam(required = true, value = "yourName") String yourName,
			@RequestParam(required = true, value = "meeetingWith") String meeetingWith,
			@RequestParam(required = true, value = "meetingDate") String meetingDate,
			@RequestParam(required = true, value = "meetingTime") String meetingTime) throws Exception {
		return meetingMethod.bookMeeting(yourName, meeetingWith, meetingDate, meetingTime);
	}

	@GetMapping("/allMeetings")
	@ResponseBody
	public String allMeetings() {
		List<Ent> ls = Repository.findAll();
		System.out.println("LIST = " + ls);
		return ls.toString();
	}

	@GetMapping("/byName")
	@ResponseBody
	public String singleStuddent(@RequestParam(required = true, value = "name") String name) {
		List<Ent> ls = Repository.findByName(name);
		System.out.println("LIST = " + ls);
		return ls.toString();
	}

	@GetMapping("/bookMeetingJPA")
	@ResponseBody
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

	///////////////////////////////////////////UI BASED //////////////////////////////
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/input")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("input");
		modelAndView.addObject("Ent", new Ent());
		return modelAndView;
	}

	@RequestMapping(value = "/output")
	public ModelAndView output(@ModelAttribute Ent Ent) {
		// Repository.save(Ent);
		System.out.println(Ent.getName());
		System.out.println(Ent.getMeetingDate());
		//System.out.println(Ent.getMeetingTime());
		String status = "Already Present";
		boolean doesUserExist = Repository.existsByName(Ent.getName());
		System.out.println("doesUserExist : " + doesUserExist);
		if (!doesUserExist) {
			//status = meetingMethod.registerHibernateEntity(Ent);
			status=DOMeeting.doRegisterEntity(Ent);
		} else {
			List<Ent> ls = Repository.findByName(Ent.getName());
			for (Ent ent2 : ls) {
				System.out.println(ent2.getMeetingDate());
				//System.out.println(ent2.getMeetingTime());
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("output");
		modelAndView.addObject("Status", status);
		modelAndView.addObject("Ent", Ent);
		return modelAndView;
	}

	@RequestMapping("/search")
	public String search() {
		return "search";
	}

	@RequestMapping(value = "/listUser")
	public ModelAndView singleStuddent(@ModelAttribute Ent Ent) {
		ModelAndView modelAndView = new ModelAndView();
//		List<Ent> ls = Repository.findAll();
//		List<Ent> ls = Repository.findByName(name);
		List list = new DOMeeting().findEntByName(Ent.getName());
		modelAndView.setViewName("listUser");
		modelAndView.addObject("Ent", Ent);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/bookMeetingUI")
	public ModelAndView bookMeeting() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bookMeeting");
		modelAndView.addObject("Ent", new Ent());
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveBookMeeting")
	public ModelAndView saveBookMeeting(@ModelAttribute Ent Ent) {
		String status = "Already Present";
		boolean doesUserExist = Repository.existsByName(Ent.getName());
		boolean doesMEetingWithExist = Repository.existsByName(Ent.getMeetingWith());
		if (!doesUserExist) {
			//status = meetingMethod.registerHibernateEntity(Ent);
			status="Please Register at http://localhost:8080/input";
		} else if (!doesMEetingWithExist) {
			//status = meetingMethod.registerHibernateEntity(Ent);
			status="MeetingWith entity does not exist";
		} else {
			status=DOMeeting.bookMeeting(Ent);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("saveBookMeeting");
		modelAndView.addObject("Status", status);
		modelAndView.addObject("Ent", Ent);
		return modelAndView;
	}
}
