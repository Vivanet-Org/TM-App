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
	
	private TEApplicationsRepository teApplicationsRepository;
	
	public TEApplicationsService(TEApplicationsRepository teApplicationsRepository) {
		super();
		this.teApplicationsRepository = teApplicationsRepository;
	}
	
	public List<TEApplications> getAllTeApplications() {
		return teApplicationsRepository.findAll();
	}

	public List<TEApplications> getApplicationByAppName(String appName) {
		return teApplicationsRepository.findByAppName(appName);
	}
		
	public TEApplications saveTeApplications(TEApplicationsDTO teApplicationsDto) throws Exception {
        List<TEApplications> applicationsList = teApplicationsRepository.findByAppName(teApplicationsDto.getAppName());
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
            return teApplicationsRepository.save(entityApplication);
        }
        return null;
	}
	
	public TEApplications updateApplication(TEApplicationsDTO teApplicationsDTO, long appid) {
		// we need to check whether application with given appId is exist in DB or not
		TEApplications existingApplications = teApplicationsRepository.findById(appid).orElseThrow(() -> 
		new ResourceNotFoundException("TEApplications", "appid", appid));		
		existingApplications.setAppName(teApplicationsDTO.getAppName());
		existingApplications.setAppDescription(teApplicationsDTO.getAppDescription());
		existingApplications.setDeleted(teApplicationsDTO.isDeleted());
		existingApplications.setProjectID(teApplicationsDTO.getProjectID());
		existingApplications.setUpdatedBy(teApplicationsDTO.getUpdatedBy());
		existingApplications.setUpdatedOn(teApplicationsDTO.getUpdatedOn());
		// save existing Project to DB
		teApplicationsRepository.save(existingApplications);
		return existingApplications;
	}
	
	public void deleteApplication(long appid) {
		// check whether application with given appId is exist in DB or not 
		teApplicationsRepository.findById(appid).orElseThrow(() -> 
		new ResourceNotFoundException("TEApplications", "appid", appid));
		teApplicationsRepository.deleteById(appid);		
	}
	
}
