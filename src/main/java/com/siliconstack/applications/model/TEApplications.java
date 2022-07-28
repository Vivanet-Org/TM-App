package com.siliconstack.applications.model;

import com.siliconstack.project.model.TEProjects;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "te_applications")
public class TEApplications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appId")
    private int appid;

    @Column(name = "appName")
    private String appName;

    @Column(name = "appDescription")
    private String appDescription;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name= "projectid")
    private TEProjects teProjects;

    @Column(name = "platformID")
    private int platformID;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdOn", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updatedBy")
    private int updatedBy;

    @Column(name = "updatedOn", nullable = false, updatable = true)
    @CreationTimestamp
    private Date updatedOn;

}
