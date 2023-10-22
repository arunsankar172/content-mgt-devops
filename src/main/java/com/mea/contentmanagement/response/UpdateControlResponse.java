package com.mea.contentmanagement.response;

import com.mea.bpm.response.BaseResponse;
import com.mea.contentmanagement.model.Control;

public class UpdateControlResponse extends BaseResponse{
	
	private Control control;

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}
	
}
