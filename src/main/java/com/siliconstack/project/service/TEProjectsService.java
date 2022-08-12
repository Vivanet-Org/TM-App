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
	
	private TEProjectRepository teProjectRepository;
	
	public TEProjectsService(TEProjectRepository teProjectRepository) {
		super();
		this.teProjectRepository = teProjectRepository;
	}

	public List<TEProjects> getAllTeProjects() {
		return teProjectRepository.findAll();
	}
	
	public List<TEProjects> getProjectByProjectName(String projectName) {
		return teProjectRepository.findByProjectName(projectName);
	}
	
	public List<TEProjects> searchProjectByNameAndDescription(String searchString) {
		return teProjectRepository.searchProjectByNameAndDescription(searchString);
	}
	
	public TEProjects saveTeProjects(TEProjectDTO teProjectsDTO){
        List<TEProjects> projectsList = teProjectRepository.findByProjectName(teProjectsDTO.getProjectName());
        if (projectsList.isEmpty()) {
        	TEProjects entityProject = new TEProjects();
        	entityProject.setProjectName(teProjectsDTO.getProjectName());
        	entityProject.setProjectDescription(teProjectsDTO.getProjectDescription());
        	entityProject.setCreatedBy(teProjectsDTO.getCreatedBy());
        	entityProject.setCreatedOn(teProjectsDTO.getCreatedOn());
        	entityProject.setUpdatedBy(teProjectsDTO.getUpdatedBy());
        	entityProject.setUpdatedOn(teProjectsDTO.getUpdatedOn());
        	entityProject.setDeleted(teProjectsDTO.isDeleted());
            return teProjectRepository.save(entityProject);
        }
        return null;
	}

	public TEProjects updateProject(TEProjectDTO teProjectDTO, long projectid) {
		// we need to check whether project with given projectID is exist in DB or not
		TEProjects existingProject = teProjectRepository.findById(projectid).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectid", projectid));
		existingProject.setProjectName(teProjectDTO.getProjectName());
		existingProject.setProjectDescription(teProjectDTO.getProjectDescription());
		existingProject.setDeleted(teProjectDTO.isDeleted());
		existingProject.setCreatedBy(teProjectDTO.getCreatedBy());
		existingProject.setCreatedOn(teProjectDTO.getCreatedOn());
		existingProject.setUpdatedBy(teProjectDTO.getUpdatedBy());
		existingProject.setUpdatedOn(teProjectDTO.getUpdatedOn());
		// save existing Project to DB
		teProjectRepository.save(existingProject);
		return existingProject;
	}
	
	public void deleteProject(long projectid) {
		// check whether Project with given projectID is exist in DB or not 
		teProjectRepository.findById(projectid).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectid", projectid));
		teProjectRepository.deleteById(projectid);
	}

	public List<Map<Integer, String>> getProjectIdAndProjectName() {
		return teProjectRepository.getProjectIdAndName();
	}
	
}
