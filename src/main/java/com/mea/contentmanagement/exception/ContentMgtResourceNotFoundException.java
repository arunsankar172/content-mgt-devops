/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.exception;

import java.util.List;

import com.mea.bpm.exception.util.ApplicationException;
import com.mea.bpm.response.AdditionalStatus;


public class ContentMgtResourceNotFoundException extends ApplicationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The additional status list. */
	List<AdditionalStatus> additionalStatusList;

	/**
	 * Instantiates a new ref data resource not found exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ContentMgtResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new ref data resource not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public ContentMgtResourceNotFoundException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new ref data resource not found exception.
	 *
	 * @param additionalStatus
	 *            the additional status
	 */
	public ContentMgtResourceNotFoundException(List<AdditionalStatus> additionalStatus) {
		super(additionalStatus);
		this.additionalStatusList = additionalStatus;
	}

	/**
	 * Instantiates a new ref data resource not found exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param additionalStatus
	 *            the additional status
	 */
	public ContentMgtResourceNotFoundException(String message, Throwable cause, List<AdditionalStatus> additionalStatus) {
		super(message, cause);
		this.additionalStatusList = additionalStatus;
	}

	/**
	 * Instantiates a new ref data resource not found exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ContentMgtResourceNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the additional status list.
	 *
	 * @return the additional status list
	 */
	public List<AdditionalStatus> getAdditionalStatusList() {
		return additionalStatusList;
	}

	/**
	 * Sets the additional status list.
	 *
	 * @param additionalStatusList
	 *            the new additional status list
	 */
	public void setAdditionalStatusList(List<AdditionalStatus> additionalStatusList) {
		this.additionalStatusList = additionalStatusList;
	}

}
