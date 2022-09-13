package com.siliconstack.applications.controller;

import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.service.TEApplicationsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;

import java.util.List;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping(path = "/application")
public class ApplicationsController {

    private final Logger log = LoggerFactory.getLogger(ApplicationsController.class);
    @Autowired
    private TEApplicationsService teApplicationsService;

    private HttpHeaders headers;

    @PostConstruct
    public void initialize() {
        headers = new HttpHeaders();
        headers.add("X-Requested-With", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");

    }

//    public ApplicationsController(TEApplicationsService teApplicationsService) {
//        super();
//        this.teApplicationsService = teApplicationsService;
//    }

    // build get all applications REST API
    @GetMapping(path="/getAllApplications")
    public ResponseEntity<Iterable<TEApplications>> getAllTeApplications(){
        log.info("In getAllApplications method");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(teApplicationsService.getAllTeApplications());
    }

    // build create application REST API
    @PostMapping(path="/createApplication")
    public ResponseEntity<TEApplications> saveApplication(@RequestBody TEApplicationsDTO teApplicationsDTO) {
        log.info("in create application method");
        try {
            TEApplications newApplications = teApplicationsService.saveTeApplications(teApplicationsDTO);
            if (newApplications != null) {
                log.info("new application created");
                return ResponseEntity.status(HttpStatus.CREATED).body(newApplications);
            }
            log.info("application already exists");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
        } catch (Exception e) {
            log.error("Error in addProject: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
        }
    }
    

    @GetMapping(path = "searchApplications/{id}")
    public ResponseEntity<Iterable<TEApplications>> serachApplicationByProject(@PathVariable("id") int projectId) {
    	List<TEApplications> applicationList = teApplicationsService.searchApplicationsByProjectId(projectId);
    	
    	return ResponseEntity.status(HttpStatus.OK).headers(headers).body(applicationList);
    }

    // build update Application REST API
 	// http://localhost:8090/application/updateApplication/1
 	@PutMapping(path="/updateApplication/{id}")
 	public ResponseEntity<TEApplications> updateApplication(@PathVariable("id") long appid, @RequestBody TEApplicationsDTO teApplicationsDTO){
 		try {
 			return new ResponseEntity<TEApplications>(teApplicationsService.updateApplication(teApplicationsDTO, appid), HttpStatus.OK);
 		}catch(Exception e) {
 	        return new ResponseEntity("Please provide a valid appid", HttpStatus.NOT_FOUND);
 	    }
 	}
 	
 	// build delete Application REST API
 	// http://localhost:8090/application/deleteApplication/1
 	@DeleteMapping(path="/deleteApplication/{id}")
 	public ResponseEntity<String> deleteApplication(@PathVariable("id") long appid){
 		try {
 			// delete Application from DB
 			teApplicationsService.deleteApplication(appid);
 			return new ResponseEntity<String>("Application Deleted Successfully!.", HttpStatus.OK);
 		}catch(Exception e) {
 	        return new ResponseEntity<String>("Please provide a valid appID", HttpStatus.NOT_FOUND);
 	    }
 	}
}