package com.mea.contentmanagement.request;

import com.mea.contentmanagement.model.Control;

public class UpdateControlRequest {
	
	private Control control;
	
	private boolean isDelete;

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
