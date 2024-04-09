package com.example.springrest.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("ERROR_STATUS_CODE", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		modelAndView.addObject("ERROR_EXCEPTION", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
		modelAndView.addObject("ERROR_EXCEPTION_TYPE", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
		modelAndView.addObject("ERROR_MESSAGE", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

		modelAndView.setViewName("error");
		return modelAndView;
	}
}
