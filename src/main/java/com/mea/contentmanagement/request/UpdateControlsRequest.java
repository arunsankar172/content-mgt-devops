package com.mea.contentmanagement.request;

import java.util.List;

import com.mea.contentmanagement.model.Control;

public class UpdateControlsRequest {
	
	private List<Control> control;

	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<Control> getControl() {
		return control;
	}

	public void setControl(List<Control> control) {
		this.control = control;
	}
}
