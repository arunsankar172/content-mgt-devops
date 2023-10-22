/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mea.bpm.constants.GeneralResponseCode;
import com.mea.bpm.constants.GeneralResponseDescription;
import com.mea.bpm.enums.MessageType;
import com.mea.bpm.response.BaseResponse;
import com.mea.bpm.response.Status;
import com.mea.contentmanagement.constants.ContentMgtConstant;
import com.mea.contentmanagement.constants.ContentMgtThreadContext;

/**
 * The Class RefDataMgmtUtil.
 *
 * @author TCS
 */
public class ContentMgmtUtil {

	/**
	 * Instantiates a new ref data mgmt util.
	 */
	private ContentMgmtUtil() {
		super();
	}

	/**
	 * Map response to baseresponse.
	 *
	 * @param input
	 *            the input
	 * @return the object
	 */
	public static Object mapResponseToBaseresponse(Object input)  {
		((BaseResponse) input).setRequestId(ContentMgtThreadContext.getRequestId());
		((BaseResponse) input).setSessionId(ContentMgtThreadContext.getSessionId());
		Status status = new Status();
		status.setStatusCode(GeneralResponseCode.SUCCES_STATUS_CODE);
		status.setStatusDescription(GeneralResponseDescription.SUCCES_STATUS_DESC);
		status.setSeverity(MessageType.INFO.toString());
		status.setSystem(ContentMgtConstant.CM_SYSTEM);
		((BaseResponse) input).setStatus(status);
		return input;
	}

	/**
	 * Convert string to date.
	 *
	 * @param dateString
	 *            the date string
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date convertStringToDate(String dateString) throws ParseException {
		if (dateString != null) {
			DateFormat sdfDate = new SimpleDateFormat(ContentMgtConstant.DATE_FORMAT);
			return sdfDate.parse(dateString);
		} else {
			return null;
		}
	}
	
	public static String convertTimeStampToString(Timestamp time) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		 Date date = new Date(time.getTime());
	     return  dateFormat.format(date);
	}

}
