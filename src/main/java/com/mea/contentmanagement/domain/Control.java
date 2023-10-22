package com.mea.contentmanagement.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTROL",schema = "CONTENTMANAGEMENT")
public class Control {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONTROLID")
	private long controlId;
	
	@Column(name="PAGE_ID")
	private long pageId;
	
	@Column(name="CONTROLCODE")
	private String controlCode;
	
	@Column(name="CONTROLTYPE")
	private String  controlType ;
	
	@Column(name="SCREEN")
	private String  screen ;
	
	@Column(name="LOCALE_KEY")
	private String localeKey ;
	
	@Column(name="CREATEDBY")
	private String createdBy ;
	
	@Column(name="UPDATEDBY")
	private String updatedBy ;
	
	@Column(name="CREATEDDATETIME")
	private Timestamp createdDateTime ;
	
	@Column(name="UPDATEDDATETIME")
	private Timestamp updatedDateTime ;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="LOCALE_VALUE_EN")
	private String localeValueEn ;
	
	@Column(name="LOCALE_VALUE_AR")
	private String localeValueAr ;
	
	@Column(name="NEW_VALUE_EN")
	private String newValueEn ;
	
	@Column(name="NEW_VALUE_AR")
	private String newValueAr ;
	
//	@ManyToOne
//	@JoinColumn(name = "PAGEID",insertable=false, updatable=false)
//	private Page page;

	
	 @ManyToOne
	 @JoinColumn(name="PAGE_ID", nullable=false, insertable=false, updatable=false)
	 private Page page;
	 
	public long getControlId() {
		return controlId;
	}

	public void setControlId(long controlId) {
		this.controlId = controlId;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getLocaleKey() {
		return localeKey;
	}

	public void setLocaleKey(String localeKey) {
		this.localeKey = localeKey;
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

	public String getLocaleValueEn() {
		return localeValueEn;
	}

	public void setLocaleValueEn(String localeValueEn) {
		this.localeValueEn = localeValueEn;
	}

	public String getLocaleValueAr() {
		return localeValueAr;
	}

	public void setLocaleValueAr(String localeValueAr) {
		this.localeValueAr = localeValueAr;
	}

	public String getNewValueEn() {
		return newValueEn;
	}

	public void setNewValueEn(String newValueEn) {
		this.newValueEn = newValueEn;
	}

	public String getNewValueAr() {
		return newValueAr;
	}

	public void setNewValueAr(String newValueAr) {
		this.newValueAr = newValueAr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}

	

}
