package com.project.shop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@Override
	@RequestMapping("/error")
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "redirect:/";
	}

}
