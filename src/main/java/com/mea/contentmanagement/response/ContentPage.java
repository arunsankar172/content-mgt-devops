package com.mea.contentmanagement.response;

import java.util.List;

public class ContentPage {
	
	private String pageCode;
	
	private String pageName;
	
	private String isActive;
	
	private List<ContentControl> controls;

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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<ContentControl> getControls() {
		return controls;
	}

	public void setControls(List<ContentControl> controls) {
		this.controls = controls;
	}
	
}
