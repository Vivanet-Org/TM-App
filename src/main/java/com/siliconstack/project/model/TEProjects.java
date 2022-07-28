package com.siliconstack.project.model;

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
public class TEProjects {
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

	
}