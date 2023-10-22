package com.mea.contentmanagement.model;

import java.sql.Timestamp;

public class Control {
	
	private long controlId;
	
	private long pageId;

	private String controlCode;

	private String  controlType ;

	private String  screen ;

	private String localeKey ;

	private String createdBy ;

	private String updatedBy ;

	private String createdDateTime ;

	private String updatedDateTime ;

	private int status ;

	private String localeValueEn ;

	private String localeValueAr ;

	private String newValueEn ;

	private String newValueAr ;

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

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
	

}
