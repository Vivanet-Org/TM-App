package com.siliconstack.applications.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siliconstack.applications.model.TEApplications;


public interface TEApplicationsRepository extends JpaRepository<TEApplications, Long>{
	
	public List<TEApplications> findByAppName(String appName);
	
	@Query(value = "SELECT * FROM te_applications WHERE projectid = :projectId", nativeQuery = true)
	public List<TEApplications> searchApplicationsByProjectId(int projectId);

	
     @Query(value = "SELECT appid, appname FROM te_applications WHERE isdeleted = false ORDER BY appname ASC", nativeQuery = true)
	 public List<Map<Integer, String>> getAppIdAndAppName();
}
