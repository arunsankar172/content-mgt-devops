package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.contentmanagement.model.Control;

public class UpdateControlsResponse {

	private List<Control> control;

	public List<Control> getControl() {
		return control;
	}

	public void setControl(List<Control> control) {
		this.control = control;
	}
}
