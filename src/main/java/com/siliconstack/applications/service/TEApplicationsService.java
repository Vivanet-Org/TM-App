package com.siliconstack.applications.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siliconstack.applications.exception.ResourceNotFoundException;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.repository.TEApplicationsRepository;

import lombok.Data;

@Data
@Service
public class TEApplicationsService {
	
	private TEApplicationsRepository TEApplicationsRepository;
	
	public TEApplicationsService(TEApplicationsRepository TEApplicationsRepository) {
		super();
		this.TEApplicationsRepository = TEApplicationsRepository;
	}

	public List<TEApplications> getApplicationByAppName(String appName) {
		return TEApplicationsRepository.findByAppName(appName);
	}
	
	public TEApplications saveTeApplications(TEApplications teApplications) {
		return TEApplicationsRepository.save(teApplications);
	}

	public List<TEApplications> getAllTeApplications() {
		return TEApplicationsRepository.findAll();
	}

	public TEApplications getApplicationById(long appid) {
		return TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("TeApplications", "appid", appid));
	}
	
	public TEApplications updateApplication(TEApplications teApplications, long appid) {
		// we need to check whether application with given projectID is exist in DB or not
		TEApplications existingApplication = TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("TeApplications", "appid", appid));
		existingApplication.setAppName(teApplications.getAppName());
		existingApplication.setAppDescription(teApplications.getAppDescription());
		existingApplication.setDeleted(teApplications.isDeleted());
		existingApplication.setTeProjects(teApplications.getTeProjects());
		existingApplication.setPlatformID(teApplications.getPlatformID());
		existingApplication.setCreatedBy(teApplications.getCreatedBy());
		existingApplication.setCreatedOn(teApplications.getCreatedOn());
		existingApplication.setUpdatedBy(teApplications.getUpdatedBy());
		existingApplication.setUpdatedOn(teApplications.getUpdatedOn());
		// save existing application to DB
		TEApplicationsRepository.save(existingApplication);
		return existingApplication;
	}

	public void deleteApplication(long appid) {
		// check whether application with given appid is exist in DB or not 
		TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("TeApplications", "appid", appid));
		TEApplicationsRepository.deleteById(appid);

	}

	

	

	

	
}
