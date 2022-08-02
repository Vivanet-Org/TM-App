package com.siliconstack.project.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.project.dto.TEProjectDTO;
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
	
	public TEProjects saveTeProjects(TEProjectDTO teProjectsDTO){
        List<TEProjects> projectsList = TEProjectRepository.findByProjectName(teProjectsDTO.getProjectName());
        if (projectsList.isEmpty()) {
        	TEProjects entityProject = new TEProjects();
        	entityProject.setProjectName(teProjectsDTO.getProjectName());
        	entityProject.setProjectDescription(teProjectsDTO.getProjectDescription());
        	entityProject.setCreatedBy(teProjectsDTO.getCreatedBy());
        	entityProject.setCreatedOn(teProjectsDTO.getCreatedOn());
        	entityProject.setUpdatedBy(teProjectsDTO.getUpdatedBy());
        	entityProject.setUpdatedOn(teProjectsDTO.getUpdatedOn());
        	entityProject.setDeleted(teProjectsDTO.isDeleted());
            return TEProjectRepository.save(entityProject);
        }
        return null;
	}

	public List<TEProjects> getAllTeProjects() {
		return TEProjectRepository.findAll();
	}

	public TEProjects getProjectById(long projectID) {
		return TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
	}
	
	public TEProjects updateProject(TEProjectDTO teProjectDTO, long projectID) {
		// we need to check whether project with given projectID is exist in DB or not
		TEProjects existingProject = TEProjectRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		existingProject.setProjectName(teProjectDTO.getProjectName());
		existingProject.setProjectDescription(teProjectDTO.getProjectDescription());
		existingProject.setDeleted(teProjectDTO.isDeleted());
		existingProject.setCreatedBy(teProjectDTO.getCreatedBy());
		existingProject.setCreatedOn(teProjectDTO.getCreatedOn());
		existingProject.setUpdatedBy(teProjectDTO.getUpdatedBy());
		existingProject.setUpdatedOn(teProjectDTO.getUpdatedOn());
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

	public List<Map<Integer, String>> getProjectIdAndProjectName() {
		return TEProjectRepository.getProjectIdAndName();
	}
	
}
