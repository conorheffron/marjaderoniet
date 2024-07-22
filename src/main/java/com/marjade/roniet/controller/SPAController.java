package com.marjade.roniet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SPAController {

    private static Logger LOGGER = LoggerFactory.getLogger(SPAController.class);

    @RequestMapping({ "/" })
    public String index(HttpServletRequest httpServletRequest) {
        LOGGER.info(httpServletRequest.getRequestURI() + " page request");
        return "index.html";
    }

    @RequestMapping({ "/contact", "/graduate", "/designer", "/brand", "editorials" })
    public String viewPathResolver(HttpServletRequest httpServletRequest) {
        LOGGER.info(httpServletRequest.getRequestURI() + " page request");
        return "index.html";
    }

}
