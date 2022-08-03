package com.siliconstack.applications.dao;

import java.util.Date;

public class TEApplicationsDTO {
	
	private int appid;
    private String appname;
    private String appdescription;
    private int projectid;
    private int platformid;
    private int createdby;
    private Date createdon;
    private int updatedby;
    private Date updatedon;
    private boolean isdeleted;
        
	public TEApplicationsDTO() {
	}

	public TEApplicationsDTO(int appid, String appname, String appdescription, int projectid, int platformid,
			int createdby, Date createdon, int updatedby, Date updatedon, boolean isdeleted) {
		super();
		this.appid = appid;
		this.appname = appname;
		this.appdescription = appdescription;
		this.projectid = projectid;
		this.platformid = platformid;
		this.createdby = createdby;
		this.createdon = createdon;
		this.updatedby = updatedby;
		this.updatedon = updatedon;
		this.isdeleted = isdeleted;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppdescription() {
		return appdescription;
	}

	public void setAppdescription(String appdescription) {
		this.appdescription = appdescription;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public int getPlatformid() {
		return platformid;
	}

	public void setPlatformid(int platformid) {
		this.platformid = platformid;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
  
}
