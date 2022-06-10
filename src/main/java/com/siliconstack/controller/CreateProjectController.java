package com.siliconstack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siliconstack.model.TeProjects;
import com.siliconstack.service.TeProjectsService;

@RestController
public class CreateProjectController {

    @GetMapping("/welcome")
	public String createProject() {
		return "Greetings from Spring Boot!";
	}
    
    private TeProjectsService teProjectsService;

	public CreateProjectController(TeProjectsService teProjectsService) {
		super();
		this.teProjectsService = teProjectsService;
	}
	
	// build create project REST API
	@PostMapping(path="/createProject")
	public ResponseEntity<TeProjects> saveEmployee(@RequestBody TeProjects teProjects){
		return new ResponseEntity<TeProjects>(teProjectsService.saveTeProjects(teProjects), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	@GetMapping(path="/getAllProjects")
	public List<TeProjects> getAllTeProjects(){
		return teProjectsService.getAllTeProjects();
	}
	
}
