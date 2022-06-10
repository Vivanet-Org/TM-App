package com.siliconstack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="te_projects")
public class TeProjects {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="projectID", columnDefinition = "INT")
	private long projectID;
	
	@Column(name="projectName", nullable = false, columnDefinition = "varchar(50)")
	private String projectName;
	
	@Column(name="projectDescription", columnDefinition = "varchar(250) default NULL")
	private String projectDescription;
	
	@Column(name="isDeleted", columnDefinition = "TINYINT(1) default '0'")
	private int isDeleted;
	
	@Column(name="createdBy", nullable = false, columnDefinition = "INT")
	private int createdBy;
	
	@Column(name="createdOn", nullable = false, columnDefinition = "DATETIME")
	private Date createdOn;
	
	@Column(name="updatedBy", nullable = false, columnDefinition = "INT")
	private int updatedBy;
	
	@Column(name="updatedOn", nullable = false, columnDefinition = "DATETIME")
	private Date updatedOn;

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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
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
	
}