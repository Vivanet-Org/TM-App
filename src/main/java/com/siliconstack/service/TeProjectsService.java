package com.siliconstack.service;

import java.util.List;

import com.siliconstack.model.TeProjects;

public interface TeProjectsService {
	
	TeProjects saveTeProjects(TeProjects TeProjects);
	
	List<TeProjects> getAllTeProjects();
	
	TeProjects getProjectById(long projectID);
	
	TeProjects updateProject(TeProjects teProjects, long projectID);
	
	void deleteProject(long projectID);
}
