package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.contentmanagement.model.Control;

public class ControlsListResponse {
	
	private List<Control> control;
	
	private String comments;

	public List<Control> getControl() {
		return control;
	}

	public void setControl(List<Control> control) {
		this.control = control;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
