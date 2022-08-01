package com.siliconstack.project.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.siliconstack.applications.model.TEApplications;

import lombok.Data;

@Data
@Entity
@Table(name="te_projects")
public class TEProjects {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="projectid", columnDefinition = "INT")
	@OneToMany(mappedBy = "teProjects")
	private Set<TEApplications> teApplications;	
	private long projectID;
	
	@Column(name="projectname", nullable = false, columnDefinition = "varchar(50)")
	private String projectName;
	
	@Column(name="projectdescription", columnDefinition = "varchar(250) default NULL")
	private String projectDescription;
	
	@Column(name="isdeleted", columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	@Column(name="createdby", nullable = false, columnDefinition = "INT")
	private int createdBy;
	
	@Column(name="createdon", nullable = false, columnDefinition = "DATETIME")
	private Date createdOn;
	
	@Column(name="updatedby", nullable = false, columnDefinition = "INT")
	private int updatedBy;
	
	@Column(name="updatedon", nullable = false, columnDefinition = "DATETIME")
	private Date updatedOn;

	public TEProjects() {
		
	}

	
	public TEProjects(long projectID, String projectName, String projectDescription, boolean isDeleted, int createdBy,
			Date createdOn, int updatedBy, Date updatedOn) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	public Set<TEApplications> getTeApplications() {
		return teApplications;
	}


	public void setTeApplications(Set<TEApplications> teApplications) {
		this.teApplications = teApplications;
	}

	

		
}