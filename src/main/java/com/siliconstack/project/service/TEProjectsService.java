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
		// we need to check whether project with given projectID is exist in DB or not
		TEProjects existingProject = TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		existingProject.setProjectName(teProjects.getProjectName());
		existingProject.setProjectDescription(teProjects.getProjectDescription());
		existingProject.setDeleted(teProjects.isDeleted());
		existingProject.setCreatedBy(teProjects.getCreatedBy());
		existingProject.setCreatedOn(teProjects.getCreatedOn());
		existingProject.setUpdatedBy(teProjects.getUpdatedBy());
		existingProject.setUpdatedOn(teProjects.getUpdatedOn());
		// save existing Project to DB
		TEProjectRepository.save(existingProject);
		return existingProject;
	}

	public void deleteProject(long projectID) {
		// check whether Project with given projectID is exist in DB or not 
		TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		TEProjectRepository.deleteById(projectID);

	}

	

	

	

	
}
