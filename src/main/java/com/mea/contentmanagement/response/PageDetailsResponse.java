package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.contentmanagement.model.Control;
import com.mea.contentmanagement.model.Page;

public class PageDetailsResponse {
	
	private Page page;
	
	private List<Control> control;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Control> getControl() {
		return control;
	}

	public void setControl(List<Control> control) {
		this.control = control;
	}

	

}
