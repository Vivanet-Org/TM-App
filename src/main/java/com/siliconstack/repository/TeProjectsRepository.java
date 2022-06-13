package com.siliconstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siliconstack.model.TeProjects;

public interface TeProjectsRepository extends JpaRepository<TeProjects, Long>{
	
	public List<TeProjects> findByProjectName(String projectName);

}
