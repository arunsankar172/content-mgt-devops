/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.constants;

import org.apache.logging.log4j.ThreadContext;

/**
 * The Class RefDataThreadContext.
 *
 * @author TCS
 */
public class ContentMgtThreadContext {

	/**
	 * Instantiates a new ref data thread context.
	 */
	private ContentMgtThreadContext() {

	}

	/** The Constant REQUEST_ID_KEY. */
	public static final String REQUEST_ID_KEY = "requestId";

	/** The Constant SESSION_ID_KEY. */
	public static final String SESSION_ID_KEY = "sessionId";

	/** The Constant CHANNEL_CODE_KEY. */
	public static final String CHANNEL_CODE_KEY = "channelCode";

	/** The Constant USER_NAME_KEY. */
	public static final String USER_NAME_KEY = "userName";

	/** The Constant INSTANCE_ID_KEY. */
	public static final String INSTANCE_ID_KEY = "instanceId";

	/** The Constant MODULE_KEY. */
	public static final String MODULE_KEY = "module";

	/**
	 * Gets the module.
	 *
	 * @return the module
	 */
	public static String getModule() {
		return ThreadContext.get(MODULE_KEY);
	}

	/**
	 * Sets the module.
	 *
	 * @param module
	 *            the new module
	 */
	public static void setModule(String module) {
		ThreadContext.put(MODULE_KEY, module);
	}

	/**
	 * Gets the request id.
	 *
	 * @return the request id
	 */
	public static String getRequestId() {
		return ThreadContext.get(REQUEST_ID_KEY);
	}

	/**
	 * Sets the request id.
	 *
	 * @param requestId
	 *            the new request id
	 */
	public static void setRequestId(String requestId) {
		ThreadContext.put(REQUEST_ID_KEY, requestId);
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public static String getSessionId() {
		return ThreadContext.get(SESSION_ID_KEY);
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId
	 *            the new session id
	 */
	public static void setSessionId(String sessionId) {
		ThreadContext.put(SESSION_ID_KEY, sessionId);
	}

	/**
	 * Gets the channel code.
	 *
	 * @return the channel code
	 */
	public static String getChannelCode() {
		return ThreadContext.get(CHANNEL_CODE_KEY);
	}

	/**
	 * Sets the channel code.
	 *
	 * @param channelCode
	 *            the new channel code
	 */
	public static void setChannelCode(String channelCode) {
		ThreadContext.put(CHANNEL_CODE_KEY, channelCode);
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public static String getUserName() {
		return ThreadContext.get(USER_NAME_KEY);
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public static void setUserName(String userName) {
		ThreadContext.put(USER_NAME_KEY, userName);
	}

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 */
	public static String getInstanceId() {
		return ThreadContext.get(INSTANCE_ID_KEY);
	}

	/**
	 * Sets the instance id.
	 *
	 * @param instanceId
	 *            the new instance id
	 */
	public static void setInstanceId(String instanceId) {
		ThreadContext.put(INSTANCE_ID_KEY, instanceId);
	}

}