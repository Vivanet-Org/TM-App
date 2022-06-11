package com.siliconstack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		try {
			List<TeProjects> projectList = teProjectsService.getProjectByProjectName(teProjects.getProjectName());
			if(projectList.size() == 0 ) {
				return new ResponseEntity<TeProjects>(teProjectsService.saveTeProjects(teProjects), HttpStatus.CREATED);
			}		
			return new ResponseEntity("The project name you entered has already been reported", HttpStatus.ALREADY_REPORTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// build get all employees REST API
	@GetMapping(path="/getAllProjects")
	public List<TeProjects> getAllTeProjects(){
		return teProjectsService.getAllTeProjects();
	}
	
	// build get project by projectId REAT API
	// http://localhost:8080/projectId/1
	@GetMapping(path="/projectId/{id}")
	public ResponseEntity<TeProjects> getProjectById(@PathVariable("id") long projectID){
		try {
			return new ResponseEntity<TeProjects>(teProjectsService.getProjectById(projectID), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build update project REST API
	// http://localhost:8080/projects/updateproject/1
	@PutMapping(path="/updateproject/{id}")
	public ResponseEntity<TeProjects> updateProject(@PathVariable("id") long projectID, @RequestBody TeProjects teProjects){
		try {
			return new ResponseEntity<TeProjects>(teProjectsService.updateProject(teProjects, projectID), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build delete project REST API
	// http://localhost:8080/deleteProject/1
	@DeleteMapping(path="/deleteProject/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable("id") long projectID){
		try {
			// delete employee from DB
			teProjectsService.deleteProject(projectID);
			return new ResponseEntity<String>("Project Deleted Successfully!.", HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity<String>("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
}
