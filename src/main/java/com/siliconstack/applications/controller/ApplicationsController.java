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
        setHeaders();
    }

    private void setHeaders() {
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
        log.info("in create project method");
        try {
            TEApplications newApplications = teApplicationsService.saveTeApplications(teApplicationsDTO);
            if (newApplications != null) {
                log.info("new project created");
                return ResponseEntity.status(HttpStatus.CREATED).body(newApplications);
            }
            log.info("Project already exists");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
        } catch (Exception e) {
            log.error("Error in addProject: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
        }
    }
}