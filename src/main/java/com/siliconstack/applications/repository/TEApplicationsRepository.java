package com.siliconstack.applications.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.siliconstack.applications.model.TEApplications;


public interface TEApplicationsRepository extends JpaRepository<TEApplications, Long>{
	
	public List<TEApplications> findByAppName(String appName);

}
