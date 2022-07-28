package com.siliconstack.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siliconstack.project.model.TEProjects;

public interface TEProjectRepository extends JpaRepository<TEProjects, Long>{
	
	public List<TEProjects> findByProjectName(String projectName);

}
