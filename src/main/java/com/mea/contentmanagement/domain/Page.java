package com.mea.contentmanagement.domain;

import java.util.List;

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
@Table(name="PAGE",schema = "CONTENTMANAGEMENT")
public class Page {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAGE_ID")
	private int pageId;
	
	@Column(name="PROJECT_ID")
	private int projectId;
	
	@Column(name="PAGE_CODE")
	private String pageCode;
	
	@Column(name="PAGE_NAME")
	private String pageName;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="CREATED_DATETIME")
	private String createdDatetime;
	
	@Column(name="UPDATED_DATETIME")
	private String updatedDatetime;
	
	
//	@OneToMany(mappedBy = "page", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Control> pages;
	
	@OneToMany(mappedBy="page")
    private List<Control> controls;
	
//	@ManyToOne
//	@JoinColumn(name = "CONTENTVERSIONID")
//	private ContentVersionTracking contentVersion;
	
	@Column(name="STATUS")
	private int status;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
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

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public List<Control> getControls() {
		return controls;
	}

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//	public List<Control> getPages() {
//		return pages;
//	}
//
//	public void setPages(List<Control> pages) {
//		this.pages = pages;
//	}
	
	
	
	
	
	
	
	

}
