package com.siliconstack.project.controller;

import java.util.List;
import java.util.Map;

import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.service.TEProjectsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping(path = "/project")
public class ProjectController {
	
    private TEProjectsService teProjectsService;

	public ProjectController(TEProjectsService teProjectsService) {
		super();
		this.teProjectsService = teProjectsService;
	}
	
	// build get all projects REST API
	@GetMapping(path="/getAllProjects")
	public List<TEProjects> getAllTeProjects(){
		return teProjectsService.getAllTeProjects();
	}
	
	// build create project REST API
	@PostMapping(path="/createProject")
	public ResponseEntity<TEProjects> saveProject(@RequestBody TEProjectDTO teProjectDTO){
		try {
			List<TEProjects> projectList = teProjectsService.getProjectByProjectName(teProjectDTO.getProjectName());
			if(projectList.size() == 0 ) {
				return new ResponseEntity<TEProjects>(teProjectsService.saveTeProjects(teProjectDTO), HttpStatus.CREATED);
			}		
			return new ResponseEntity("The project name you entered has already been reported", HttpStatus.ALREADY_REPORTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
		
	// build update project REST API
	// http://localhost:8080/project/updateproject/1
	@PutMapping(path="/updateProject/{id}")
	public ResponseEntity<TEProjects> updateProject(@PathVariable("id") long projectid, @RequestBody TEProjectDTO teProjectDTO){
		try {
			return new ResponseEntity<TEProjects>(teProjectsService.updateProject(teProjectDTO, projectid), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build delete project REST API
	// http://localhost:8080/project/deleteProject/1
	@DeleteMapping(path="/deleteProject/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable("id") long projectid){
		try {
			// delete employee from DB
			teProjectsService.deleteProject(projectid);
			return new ResponseEntity<String>("Project Deleted Successfully!.", HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity<String>("Please provide a valid projectID", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build get projectId projectName REST API
	@GetMapping(path="/getProjectIdProjectName")
	public List<Map<Integer, String>> getProjectIdProjectName(){
		return teProjectsService.getProjectIdAndProjectName();
	}
	
}
