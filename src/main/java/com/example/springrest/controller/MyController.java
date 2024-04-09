package com.example.springrest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.RepoEnt;
import com.example.springrest.entity.SubEnt;
import com.example.springrest.method.DOMeeting;
import com.example.springrest.method.Validation;
import com.example.springrest.method.meetingMethod;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@Controller
public class MyController {
	@Autowired
	private RepoEnt repository;

	@Autowired
	private DOMeeting doMeeting;

	@Autowired
	meetingMethod meetingMethod;

	@GetMapping("/oldAPI")
	@ResponseBody
	public String[] homepage() {
		String[] arr = new String[] {"http://localhost:8080/oldAPI", "http://localhost:8080/register?name=",
				"http://localhost:8080/registerHibernate?name=", "http://localhost:8080/registerJPA?name=",
				"http://localhost:8080/bookMeeting?yourName=&meeetingWith=&meetingDate=&meetingTime", "http://localhost:8080/allMeetings",
				"http://localhost:8080/byName?name=", "http://localhost:8080/bookMeetingJPA?yourName=&meeetingWith=&meetingDate=&meetingTime=" };
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
		repository.save(entity);
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
		List<Ent> ls = repository.findAll();
		System.out.println("LIST = " + ls);
		return ls.toString();
	}

	@GetMapping("/byName")
	@ResponseBody
	public String singleStuddent(@RequestParam(required = true, value = "name") String name) {
		List<Ent> ls = repository.findByName(name);
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

//		String meetingTime = Date + "|" + Time;

//		List<Ent> metaDataFrom = Repository.findByName(from);
//		String meetingValFrom = metaDataFrom.get(0).getMeeting();
//		long meetingIdFrom = metaDataFrom.get(0).getId();
//		String[] meetingArrFrom = meetingValFrom.split("\\,");
//
//		List<Ent> metaDataTo = Repository.findByName(to);
//		String meetingValTo = metaDataTo.get(0).getMeeting();
//		long meetingIdTo = metaDataTo.get(0).getId();
//		String[] meetingArrTo = meetingValTo.split("\\,");

//		if (meetingArrFrom == null) {
//			return "User does not exist , Please register : http://localhost:8080/registerJPA?name=";
//		} else if (meetingArrTo == null) {
//			return "User does not exist";
//		} else {
//
//			for (int i = 0; i < meetingArrTo.length; i++) {
//				if (meetingArrTo[i].contains(meetingTime)) {
//					return to + " has a meeting already booked with : " + meetingArrTo[i].split("@")[0];
//				}
//			}
//			for (int i = 0; i < meetingArrFrom.length; i++) {
//				if (meetingArrFrom[i].contains(meetingTime)) {
//					return "you have a meeting already booked with : " + meetingArrFrom[i].split("@")[0];
//				}
//			}
//			boolean check1 = saver(meetingIdTo, to, meetingValTo, from + "@" + meetingTime + ",");
//			boolean check2 = saver(meetingIdFrom, from, meetingValFrom, to + "@" + meetingTime + ",");
//
//			if (check1 && check2)
//				return "meeting booked successfully";
//		}

		return "someError";
	}

	public boolean saver(long id, String name, String meetingTime, String meetingData) {
		Ent entity = new Ent();
		entity.setId(id);
		entity.setName(name);
//		entity.setMeeting(meetingData + meetingTime);
		repository.save(entity);

		return true;
	}

	/////////////////////////////////////////// UI BASED
	/////////////////////////////////////////// //////////////////////////////

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

	@Transactional
	@RequestMapping(value = "/output")
	public ModelAndView output(@ModelAttribute Ent Ent) {
		// Repository.save(Ent);
		String status = "Already Present";
		boolean doesUserExist = repository.existsByName(Ent.getName());
		if (!doesUserExist) {
			// status = meetingMethod.registerHibernateEntity(Ent);
//			status = DOMeeting.doRegisterEntity(Ent);
			repository.save(Ent);
			status = "Created Successfully";
		} else {
			Ent = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("output");
		modelAndView.addObject("Status", status);
		modelAndView.addObject("Ent", Ent);
		return modelAndView;
	}

	@RequestMapping("/search")
	public ModelAndView search(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("search");
		Ent ent = new Ent();
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("userDetails ::: " + userDetails);
		System.out.println("principal.getName ::: " + principal.getName());// this can also be used
		ent.setName(userDetails.getUsername());
		ent.setPassword(userDetails.getPassword());
		modelAndView.addObject("Ent", ent);
		return modelAndView;
	}

	@RequestMapping(value = "/listUser")
	public ModelAndView singleStuddent(@ModelAttribute Ent Ent) {
		ModelAndView modelAndView = new ModelAndView();
		Ent checkPassword = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());

		if (checkPassword != null && checkPassword.getPassword().equals(Ent.getPassword())) {
			modelAndView.addObject("list", checkPassword.getSubEnts());
			modelAndView.addObject("status", "found");
			modelAndView.setViewName("listUser");
		} else {
			modelAndView.addObject("list", new ArrayList<>());
			modelAndView.addObject("status", "Wrong Password");
			modelAndView.setViewName("search");
		}
		modelAndView.addObject("Ent", Ent);
		return modelAndView;
	}

	@RequestMapping(value = "/listAllUser")
	public ModelAndView allStuddent() {
		ModelAndView modelAndView = new ModelAndView();
		List<Ent> list = repository.findAll();
		// List list = new DOMeeting().getEnts();
		modelAndView.addObject("list", list);
		modelAndView.addObject("status", "found");
		modelAndView.setViewName("listAllUser");
		return modelAndView;
	}

	@RequestMapping("/bookMeetingUI")
	public ModelAndView bookMeeting() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bookMeeting");
		modelAndView.addObject("Ent", new Ent());
		return modelAndView;
	}

	@Transactional
	@RequestMapping(value = "/saveBookMeeting")
	public ModelAndView saveBookMeeting(@ModelAttribute Ent Ent) throws Exception {
		String status = "Already Present";
		Ent checkPassword = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());
		Ent meetingWithEnt = repository.findTopByName(Ent.getSubEnts().get(0).getMeetingWith()).orElse(null);

		if (checkPassword == null || checkPassword.equals(new Ent())) {
			status = "Please Register at http://localhost:8080/input";
		} else if (meetingWithEnt == null) {
			status = "MeetingWith entity does not exist";
		} else if (!checkPassword.getPassword().equals(Ent.getPassword())) {
			status = "Invalid Password";
		} else {

			try {
				SubEnt main = new SubEnt();
				main.setEnt(checkPassword);
				main.setMeetingDate(Ent.getSubEnts().get(0).getMeetingDate());
				main.setMeetingEndDate(Ent.getSubEnts().get(0).getMeetingEndDate());
				main.setMeetingWith(meetingWithEnt.getName());
				main.setMeetingWithId(meetingWithEnt.getId());

				SubEnt meetingWith = new SubEnt();
				meetingWith.setEnt(meetingWithEnt);
				meetingWith.setMeetingDate(Ent.getSubEnts().get(0).getMeetingDate());
				meetingWith.setMeetingEndDate(Ent.getSubEnts().get(0).getMeetingEndDate());
				meetingWith.setMeetingWith(checkPassword.getName());
				meetingWith.setMeetingWithId(checkPassword.getId());

				System.out.println("main : " + main);
				System.out.println("");
				System.out.println("meetingWith : " + meetingWith);
				System.out.println("");
				System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

//			status = DOMeeting.bookMeeting(Ent , meetingWithEnt);
				status = doMeeting.bookMeetingSubEnt(main, meetingWith);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			if (!status.equalsIgnoreCase("Meeting Booked"))
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("saveBookMeeting");
		modelAndView.addObject("Status", status);
		modelAndView.addObject("Ent", Ent);
		modelAndView.addObject("meetingWith", meetingWithEnt.getName());
		return modelAndView;
	}

	///////////////// REST API BY BODY

	@GetMapping("/")
	@ResponseBody
	public String[] APIHome() {
		String[] arr = new String[] {"http://localhost:8080", "http://localhost:8080/registerAPI?name=&password=",
				"http://localhost:8080/byNameAPI?name=", "http://localhost:8080/bookMeetingAPI" };
		return arr;
	}

	@PostMapping("/registerAPI")
	@ResponseBody
	public String registerAPI(@RequestParam(required = true, value = "name") String name,
			@RequestParam(required = true, value = "password") String password) {
		String status = "Already Present";
		Ent Ent = new Ent();
		Ent.setName(name);
		Ent.setPassword(password);

		ArrayList<String> err = new Validation().validateRegister(Ent);
		if (err.isEmpty()) {
			boolean doesUserExist = repository.existsByName(name);
			if (!doesUserExist) {
				status = doMeeting.doRegisterEntity(Ent);
			} else {
				Ent = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());
			}
		} else {
			status = err.toString();
		}

		return status;
	}

	@GetMapping(path = "/byNameAPI")
	@ResponseBody
	public List byNameAPI(@RequestParam(required = true, value = "name") String name) {
		List list = new ArrayList<>();
		Ent checkPassword = repository.findTopByNameAndPassword(name, name);
		if (checkPassword.getPassword().equals(name)) {
			list = new DOMeeting().findEntByName(name);
		}
		return list;
	}

	@PostMapping(path = "/bookMeetingAPI", produces = {"application/json" })
	@ResponseBody
	public String bookMeetingAPI(@RequestBody(required = true) Ent Ent) {
		String status = "Already Present";
		Ent checkPassword = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());
		Ent meetingWithEnt = repository.findTopByName(Ent.getSubEnts().get(0).getMeetingWith()).orElse(null);
		if (checkPassword == null || checkPassword.equals(new Ent())) {
			status = "http://localhost:8080/registerAPI?name=";
		} else if (meetingWithEnt == null) {
			status = "MeetingWith entity does not exist";
		} else if (!checkPassword.getPassword().equals(Ent.getPassword())) {
			status = "Invalid Password";
		} else {
			status = doMeeting.bookMeeting(Ent, meetingWithEnt);
		}
		return status;
	}

	@GetMapping("/listJSP") // the thymeleaf dependency needs to be commented out to run normal jsps
	public String listJSP(Model model) {
		List<Ent> list = repository.findByName("awwab");
		model.addAttribute("books", list);
		return "lister";
	}

//	@RequestMapping(method = RequestMethod.GET, path = "/FMhome")
//	public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//		model.addAttribute("name", name);
//		return "FMhome";
//	}

	@Value("${msg.title}")
	public String title;

	@RequestMapping(method = RequestMethod.GET, path = "/homeFM")
	public ModelAndView homeFM() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homeFM");
		return modelAndView;
	}

	@RequestMapping("/searchFM")
	public ModelAndView searchFM(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("searchFM");
		Ent ent = new Ent();
//		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		ent.setName(userDetails.getUsername());
//		ent.setPassword(userDetails.getPassword());
		modelAndView.addObject("Ent", ent);
		modelAndView.addObject("status", "statusss");
		return modelAndView;
	}

	@PostMapping(value = "/listUserFM")
	public ModelAndView listUserFM(@ModelAttribute("Ent") Ent Ent, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Ent checkPassword = repository.findTopByNameAndPassword(Ent.getName(), Ent.getPassword());
		if (checkPassword != null && checkPassword.getPassword().equals(Ent.getPassword())) {
			modelAndView.addObject("list", checkPassword.getSubEnts());
			modelAndView.addObject("status", "found");
			modelAndView.setViewName("listUserFM");
		} else {
			modelAndView.addObject("list", new ArrayList<>());
			modelAndView.addObject("status", "Wrong Password");
			modelAndView.setViewName("searchFM");
		}
		HashMap<String, String> hmTest = new HashMap<>();
		hmTest.put("1", "one");
		hmTest.put("2", "two");
		hmTest.put("3", "tthree");
		modelAndView.addObject("hmTest", hmTest);
		modelAndView.addObject("Ent", Ent);
		modelAndView.addObject("lastChar", new LastCharMethod());
		return modelAndView;
	}

	public class LastCharMethod implements TemplateMethodModelEx {
		public Object exec(List arguments) throws TemplateModelException {
			if (arguments.size() != 1 || StringUtils.isEmpty(arguments.get(0)))
				throw new TemplateModelException("Wrong arguments!");
			String argument = arguments.get(0).toString();
			return argument.charAt(argument.length() - 1);
		}
	}
}
