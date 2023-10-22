package com.mea.contentmanagement.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT",schema = "CONTENTMANAGEMENT")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PROJECTID")
	private long projectId;
	
	@Column(name="PROJECTCODE")
	private String projectCode;
	
	@Column(name="PROJECTNAME")
	private String projectName;
	
	@Column(name="ISACTIVE")
	private boolean isActive;
	
	@Column(name="CREATEDBY")
	private String createdBy;
	
	@Column(name="UPDATEDBY")
	private String updatedBy;

	@Column(name="CREATEDDATETIME")
	private Timestamp createdDateTime;
	
	@Column(name="UPDTEDDATETIME")
	private Timestamp updatedDateTime;
	
//	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
//	@Basic(fetch = FetchType.EAGER)
//	private List<Page> pages;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Timestamp updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
}
