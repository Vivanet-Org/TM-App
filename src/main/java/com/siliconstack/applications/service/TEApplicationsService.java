package com.siliconstack.applications.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siliconstack.applications.dto.TEApplicationsDTO;
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
		
	public TEApplications saveTeApplications(TEApplicationsDTO teApplicationsDto) throws Exception {
        List<TEApplications> applicationsList = TEApplicationsRepository.findByAppName(teApplicationsDto.getAppName());
        if (applicationsList.isEmpty()) {
        	TEApplications entityApplication = new TEApplications();
        	entityApplication.setAppName(teApplicationsDto.getAppName());
        	entityApplication.setAppDescription(teApplicationsDto.getAppDescription());
        	entityApplication.setProjectID(teApplicationsDto.getProjectID());
        	entityApplication.setPlatformID(teApplicationsDto.getPlatformID());
        	entityApplication.setCreatedBy(teApplicationsDto.getCreatedBy());
        	entityApplication.setCreatedOn(teApplicationsDto.getCreatedOn());
        	entityApplication.setUpdatedBy(teApplicationsDto.getUpdatedBy());
        	entityApplication.setUpdatedOn(teApplicationsDto.getUpdatedOn());
        	entityApplication.setDeleted(teApplicationsDto.isDeleted());
            return TEApplicationsRepository.save(entityApplication);
        }
        return null;
	}

	public List<TEApplications> getAllTeApplications() {
		return TEApplicationsRepository.findAll();
	}

	public TEApplications getApplicationById(long appid) {
		return TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("TeApplications", "appid", appid));
	}
	
	public TEApplications updateApplication(TEApplicationsDTO teApplicationsDTO, long appid) {
		// we need to check whether application with given projectID is exist in DB or not
		TEApplications existingApplication = TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("teApplicationsDTO", "appid", appid));
		existingApplication.setAppName(teApplicationsDTO.getAppName());
		existingApplication.setAppDescription(teApplicationsDTO.getAppDescription());
		existingApplication.setDeleted(teApplicationsDTO.isDeleted());
		existingApplication.setProjectID(teApplicationsDTO.getProjectID());
		existingApplication.setPlatformID(teApplicationsDTO.getPlatformID());
		existingApplication.setCreatedBy(teApplicationsDTO.getCreatedBy());
		existingApplication.setCreatedOn(teApplicationsDTO.getCreatedOn());
		existingApplication.setUpdatedBy(teApplicationsDTO.getUpdatedBy());
		existingApplication.setUpdatedOn(teApplicationsDTO.getUpdatedOn());
		// save existing application to DB
		TEApplicationsRepository.save(existingApplication);
		return existingApplication;
	}

	public void deleteApplication(long appid) {
		// check whether application with given appId is exist in DB or not 
		TEApplicationsRepository.findById(appid).orElseThrow(() ->
				new ResourceNotFoundException("TeApplications", "appid", appid));
		TEApplicationsRepository.deleteById(appid);

	}

	

	

	

	
}
