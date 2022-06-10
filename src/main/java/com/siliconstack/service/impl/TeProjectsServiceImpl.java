package com.siliconstack.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siliconstack.exception.ResourceNotFoundException;
import com.siliconstack.model.TeProjects;
import com.siliconstack.repository.TeProjectsRepository;
import com.siliconstack.service.TeProjectsService;

@Service
public class TeProjectsServiceImpl implements TeProjectsService{
	
	private TeProjectsRepository teProjectsRepository;
	
	public TeProjectsServiceImpl(TeProjectsRepository teProjectsRepository) {
		super();
		this.teProjectsRepository = teProjectsRepository;
	}

	@Override
	public TeProjects saveTeProjects(TeProjects teProjects) {
		return teProjectsRepository.save(teProjects);
	}

	@Override
	public List<TeProjects> getAllTeProjects() {
		return teProjectsRepository.findAll();
	}

	@Override
	public TeProjects getProjectById(long projectID) {
		return teProjectsRepository.findById(projectID).orElseThrow(() -> 
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
	}
	
	@Override
	public TeProjects updateProject(TeProjects teProjects, long projectID) {
		// we need to check whether employee with given projectID is exist in DB or not
		TeProjects existingEmployee = teProjectsRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		existingEmployee.setProjectName(teProjects.getProjectName());
		existingEmployee.setProjectDescription(teProjects.getProjectDescription());
		existingEmployee.setIsDeleted(teProjects.getIsDeleted());
		existingEmployee.setCreatedBy(teProjects.getCreatedBy());
		existingEmployee.setCreatedOn(teProjects.getCreatedOn());
		existingEmployee.setUpdatedBy(teProjects.getUpdatedBy());
		existingEmployee.setUpdatedOn(teProjects.getUpdatedOn());
		// save existing Project to DB
		teProjectsRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteProject(long projectID) {
		// check whether Project with given projectID is exist in DB or not 
		teProjectsRepository.findById(projectID).orElseThrow(() -> 
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		teProjectsRepository.deleteById(projectID);

	}
}
