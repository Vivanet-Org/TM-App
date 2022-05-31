package com.siliconstack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateProjectController {

    @GetMapping("/createProject")
	public String createProject() {
		return "Greetings from Spring Boot!";
	}
}
