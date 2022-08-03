package com.siliconstack.applications.controller;

import java.util.List;

import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.service.TEApplicationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping(path = "/application")
public class ApplicationController {

    private TEApplicationsService teApplicationsService;

	public ApplicationController(TEApplicationsService teApplicationsService) {
		super();
		this.teApplicationsService = teApplicationsService;
	}
	
//	 build create application REST API
	@PostMapping(path="/createApplication")
	public ResponseEntity<TEApplications> saveApplication(@RequestBody TEApplicationsDTO teApplicationsDTO){
		try {
			List<TEApplications> applicationList = teApplicationsService.getApplicationByAppName(teApplicationsDTO.getAppName());
			if(applicationList.size() == 0 ) {
				return new ResponseEntity<TEApplications>(teApplicationsService.saveTeApplications(teApplicationsDTO), HttpStatus.CREATED);
			}		
			return new ResponseEntity("The application name you entered has already been reported", HttpStatus.ALREADY_REPORTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// build get all applications REST API
	@GetMapping(path="/getAllApplications")
	public List<TEApplications> getAllTeApplications(){
		return teApplicationsService.getAllTeApplications();
	}
	
	// build get application by appId REAT API
	@GetMapping(path="/appId/{id}")
	public ResponseEntity<TEApplications> getApplicationById(@PathVariable("id") long appid){
		try {
			return new ResponseEntity<TEApplications>(teApplicationsService.getApplicationById(appid), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid appId", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build update application REST API
	@PutMapping(path="/updateApplication/{id}")
	public ResponseEntity<TEApplications> updateApplication(@PathVariable("id") long appid, @RequestBody TEApplicationsDTO teApplicationsDTO){
		try {
			return new ResponseEntity<TEApplications>(teApplicationsService.updateApplication(teApplicationsDTO, appid), HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity("Please provide a valid appId", HttpStatus.NOT_FOUND);
	    }
	}
	
	// build delete application REST API
	@DeleteMapping(path="/deleteApplication/{id}")
	public ResponseEntity<String> deleteApplication(@PathVariable("id") long appid){
		try {
			// delete application from DB
			teApplicationsService.deleteApplication(appid);
			return new ResponseEntity<String>("Application Deleted Successfully!.", HttpStatus.OK);
		}catch(Exception e) {
	        return new ResponseEntity<String>("Please provide a valid appId", HttpStatus.NOT_FOUND);
	    }
	}
	
}
