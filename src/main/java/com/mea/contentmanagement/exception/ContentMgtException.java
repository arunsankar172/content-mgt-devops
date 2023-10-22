package com.mea.contentmanagement.exception;

import java.util.List;

import com.mea.bpm.exception.util.ApplicationException;
import com.mea.bpm.response.AdditionalStatus;

public class ContentMgtException extends ApplicationException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The additional status list. */
	List<AdditionalStatus> additionalStatusList;

	public ContentMgtException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContentMgtException(String message) {
		super(message);
	}

	public ContentMgtException(List<AdditionalStatus> additionalStatus) {
		super(additionalStatus);
		this.additionalStatusList = additionalStatus;
	}

	public ContentMgtException(String message, Throwable cause, List<AdditionalStatus> additionalStatus) {
		super(message, cause);
		this.additionalStatusList = additionalStatus;
	}


	public ContentMgtException(Throwable cause) {
		super(cause);
	}

	public List<AdditionalStatus> getAdditionalStatusList() {
		return additionalStatusList;
	}


	public void setAdditionalStatusList(List<AdditionalStatus> additionalStatusList) {
		this.additionalStatusList = additionalStatusList;
	}

}
