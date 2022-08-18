package com.siliconstack.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siliconstack.project.model.TEProjects;

public interface TEProjectRepository extends JpaRepository<TEProjects, Long>{
	
	public List<TEProjects> findByProjectName(String projectName);
	
	@Query(value = "SELECT projectid, projectname FROM te_projects WHERE isdeleted = false ORDER BY projectname ASC", nativeQuery = true)
	public List<Map<Integer, String>> getProjectIdAndName();
	
	@Query(value = "SELECT *" + " FROM te_projects WHERE (projectname LIKE %:searchString% OR projectdescription LIKE %:searchString%) ", nativeQuery = true)
	public List<TEProjects> searchProjectByNameAndDescription(String searchString);

}
