package com.siliconstack.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siliconstack.project.model.TEProjects;

public interface TEProjectRepository extends JpaRepository<TEProjects, Long>{
	
	public List<TEProjects> findByProjectName(String projectName);
	
	@Query(value = "select projectid, projectname from te_projects where isdeleted = false", nativeQuery = true)
	public List<Map<Integer, String>> getProjectIdAndName();

}
