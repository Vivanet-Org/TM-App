package com.siliconstack.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siliconstack.project.exception.ResourceNotFoundException;
import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.repository.TEProjectRepository;

import lombok.Data;

@Data
@Service
public class TEProjectsService {
	
	private TEProjectRepository TEProjectRepository;
	
	public TEProjectsService(TEProjectRepository TEProjectRepository) {
		super();
		this.TEProjectRepository = TEProjectRepository;
	}

	public List<TEProjects> getProjectByProjectName(String projectName) {
		return TEProjectRepository.findByProjectName(projectName);
	}
	
	public TEProjects saveTeProjects(TEProjects teProjects) {
		return TEProjectRepository.save(teProjects);
	}

	public List<TEProjects> getAllTeProjects() {
		return TEProjectRepository.findAll();
	}

	public TEProjects getProjectById(long projectID) {
		return TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
	}
	
	public TEProjects updateProject(TEProjects teProjects, long projectID) {
		// we need to check whether employee with given projectID is exist in DB or not
		TEProjects existingEmployee = TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		existingEmployee.setProjectName(teProjects.getProjectName());
		existingEmployee.setProjectDescription(teProjects.getProjectDescription());
		existingEmployee.setIsDeleted(teProjects.getIsDeleted());
		existingEmployee.setCreatedBy(teProjects.getCreatedBy());
		existingEmployee.setCreatedOn(teProjects.getCreatedOn());
		existingEmployee.setUpdatedBy(teProjects.getUpdatedBy());
		existingEmployee.setUpdatedOn(teProjects.getUpdatedOn());
		// save existing Project to DB
		TEProjectRepository.save(existingEmployee);
		return existingEmployee;
	}

	public void deleteProject(long projectID) {
		// check whether Project with given projectID is exist in DB or not 
		TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		TEProjectRepository.deleteById(projectID);

	}

	

	

	

	
}
