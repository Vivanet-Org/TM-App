package com.siliconstack.project.controller;

import java.util.List;

import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.service.TEProjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(path = "/project")
public class ProjectController {

    private TEProjectsService teProjectsService;

	public ProjectController(TEProjectsService teProjectsService) {
		super();
		this.teProjectsService = teProjectsService;
	}
	
	// build create project REST API
	@PostMapping(path="/createProject")
	public ResponseEntity<TEProjects> saveEmployee(@RequestBody TEProjects teProjects){
		try {
			List<TEProjects> projectList = teProjectsService.getProjectByProjectName(teProjects.getProjectName());
			if(projectList.size() == 0 ) {
				return new ResponseEntity<TEProjects>(teProjectsService.saveTeProjects(teProjects), HttpStatus.CREATED);
			}		
			return new ResponseEntity("The project name you entered has already been reported", HttpStatus.ALREADY_REPORTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// build get all employees REST API
	@GetMapping(path="/getAllProjects")
	public List<TEProjects> getAllTeProjects(){
		return teProjectsService.getAllTeProjects();
	}
	
	// build get project by projectId REAT API
	// http://localhost:8080/projectId/1
	@GetMapping(path="/projectId/{id}")
	public ResponseEntity<TEProjects> getProjectById(@PathVariable("id") long projectID){
		try {
			return new ResponseEntity<TEProjects>(teProjectsService.getProjectById(projectID), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build update project REST API
	// http://localhost:8080/projects/updateproject/1
	@PutMapping(path="/updateproject/{id}")
	public ResponseEntity<TEProjects> updateProject(@PathVariable("id") long projectID, @RequestBody TEProjects teProjects){
		try {
			return new ResponseEntity<TEProjects>(teProjectsService.updateProject(teProjects, projectID), HttpStatus.OK);
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
