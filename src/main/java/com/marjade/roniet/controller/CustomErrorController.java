package com.marjade.roniet.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public ModelAndView error(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return new ModelAndView("index.html");
	}

	public String getErrorPath() {
		return PATH;
	}

}
